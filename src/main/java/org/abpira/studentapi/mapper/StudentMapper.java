package org.abpira.studentapi.mapper;

import org.abpira.studentapi.dto.StudentDTO;
import org.abpira.studentapi.entity.Student;

import java.time.LocalDateTime;

public record StudentMapper() {

    public static Student mapToStudent(StudentDTO studentDTO) {
        return Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .age(studentDTO.getAge())
                .gradeLevel(studentDTO.getGradeLevel())
                .createdAt(LocalDateTime.now())
                .createdBy("Anonymous")
                .build();
    }

    public static StudentDTO mapToStudentDTO(Student student) {
        return StudentDTO.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .age(student.getAge())
                .gradeLevel(student.getGradeLevel())
                .build();
    }
}
