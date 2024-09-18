package org.abpira.studentapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SREnum {

    UPDATED("200 Updated", "Student entity has been updated"),
    CREATED("201 Created", "Student Registered Successfully"),
    DELETED("200 DELETED", "Student info has been deleted from the database");
    private final String status;
    private final String message;
}
