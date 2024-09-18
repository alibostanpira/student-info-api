package org.abpira.studentapi.exception;

import org.abpira.studentapi.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class StudentResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex,
                                                                                WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex,
                                                                           WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponseDTO> handleDatabaseException(DatabaseException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(StudentNotExistException.class)
    public ResponseEntity<ErrorResponseDTO> handleStudentNotExistException(StudentNotExistException ex,
                                                                           WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<ErrorResponseDTO> handleUpdateFailedException(UpdateFailedException ex,
                                                                        WebRequest request) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ErrorResponseDTO.builder()
                .apiPath(request.getDescription(false))
                .httpStatus(HttpStatus.EXPECTATION_FAILED)
                .errorMessage(ex.getMessage())
                .errorTime(LocalDateTime.now())
                .build());
    }
}
