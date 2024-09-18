package org.abpira.studentapi.service;

import org.abpira.studentapi.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    void createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentByEmail(String email);

    void updateStudent(String email, StudentDTO studentDTO);

    void deleteStudent(String email);
}
