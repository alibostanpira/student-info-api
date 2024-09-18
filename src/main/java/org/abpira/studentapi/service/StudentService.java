package org.abpira.studentapi.service;

import org.abpira.studentapi.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    void createStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(String email);
    void updateStudent(StudentDTO studentDTO);
    void deleteStudent(String email);
}
