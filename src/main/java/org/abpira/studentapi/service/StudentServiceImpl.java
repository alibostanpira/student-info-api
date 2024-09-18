package org.abpira.studentapi.service;

import lombok.RequiredArgsConstructor;
import org.abpira.studentapi.dto.StudentDTO;
import org.abpira.studentapi.entity.Student;
import org.abpira.studentapi.exception.DatabaseException;
import org.abpira.studentapi.exception.StudentAlreadyExistsException;
import org.abpira.studentapi.exception.StudentNotExistException;
import org.abpira.studentapi.exception.UpdateFailedException;
import org.abpira.studentapi.mapper.StudentMapper;
import org.abpira.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void createStudent(StudentDTO studentDTO) {
        if (studentDTO.getEmail() == null) {
            throw new IllegalArgumentException("Email must not be NULL or Empty");
        } else if (studentRepository.findByEmail(studentDTO.getEmail()).isPresent()) {
            throw new StudentAlreadyExistsException("Student with " + studentDTO.getEmail() + " email already exists");
        }
        studentRepository.save(StudentMapper.mapToStudent(studentDTO));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        try {
            return studentRepository.findAll()
                    .stream()
                    .map(StudentMapper::mapToStudentDTO)
                    .toList();
        } catch (DatabaseException e) {
            throw new DatabaseException("Database access error while fetching students", e);
        }
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email).orElseThrow(
                () -> new StudentNotExistException("Student with email " + email + " does not exist")
        );
        return StudentMapper.mapToStudentDTO(student);
    }

    @Override
    public void updateStudent(String email, StudentDTO studentDTO) {
        if (studentDTO != null) {
            Student student = studentRepository.findByEmail(email).orElseThrow(
                    () -> new StudentNotExistException("Student with email " + studentDTO.getEmail() +
                            " and name " + studentDTO.getFirstName() + " " +
                            studentDTO.getLastName() + " does not exist")
            );
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setEmail(studentDTO.getEmail());
            student.setAge(studentDTO.getAge());
            student.setGradeLevel(studentDTO.getGradeLevel());
            student.setUpdatedAt(LocalDateTime.now());
            student.setUpdatedBy("Anonymous");
            studentRepository.save(student);
        } else {
            throw new UpdateFailedException("Student info does not updated");
        }
    }

    @Override
    public void deleteStudent(String email) {
        if (studentRepository.findByEmail(email).isPresent()) {
            studentRepository.deleteByEmail(email);
        } else {
            throw new StudentNotExistException("Student with email " + email + " does not exist");
        }
    }
}
