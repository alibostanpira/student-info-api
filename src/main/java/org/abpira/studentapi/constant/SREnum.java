package org.abpira.studentapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SREnum {
    CREATED("201 Created", "Student Registered Successfully");

    private final String status;
    private final String message;
}
