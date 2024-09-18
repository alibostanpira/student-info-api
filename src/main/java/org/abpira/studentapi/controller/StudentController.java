package org.abpira.studentapi.controller;


import lombok.RequiredArgsConstructor;
import org.abpira.studentapi.constant.SREnum;
import org.abpira.studentapi.dto.ResponseDTO;
import org.abpira.studentapi.dto.StudentDTO;
import org.abpira.studentapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/students", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.builder()
                        .status(SREnum.CREATED.getStatus())
                        .message(SREnum.CREATED.getMessage())
                        .build());
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentByEmail(email));
    }

    @PutMapping(path = "/{email}")
    public ResponseEntity<ResponseDTO> updateStudent(@PathVariable String email, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(email, studentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .status(SREnum.UPDATED.getStatus())
                .message(SREnum.UPDATED.getMessage())
                .build());
    }

    @DeleteMapping(path = "/{email}")
    public ResponseEntity<ResponseDTO> deleteStudent(@PathVariable String email) {
        studentService.deleteStudent(email);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .status(SREnum.DELETED.getStatus())
                .message(SREnum.DELETED.getMessage())
                .build());
    }
}
