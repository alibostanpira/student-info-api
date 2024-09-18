package org.abpira.studentapi.service;

import lombok.RequiredArgsConstructor;
import org.abpira.studentapi.dto.StudentDTO;
import org.abpira.studentapi.entity.Student;
import org.abpira.studentapi.exception.StudentAlreadyExistsException;
import org.abpira.studentapi.mapper.StudentMapper;
import org.abpira.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

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
        return List.of();
    }

    @Override
    public StudentDTO getStudentById(String email) {
        return null;
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {

    }

    @Override
    public void deleteStudent(String email) {

    }
}
