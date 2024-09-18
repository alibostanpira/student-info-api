package org.abpira.studentapi.controller;


import lombok.RequiredArgsConstructor;
import org.abpira.studentapi.constant.SREnum;
import org.abpira.studentapi.dto.ResponseDTO;
import org.abpira.studentapi.dto.StudentDTO;
import org.abpira.studentapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
