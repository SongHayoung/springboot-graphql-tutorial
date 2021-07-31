package com.sumfi.graphql.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WebToonErrorCode {
    NO_TITLE(1001, "No Title Exsist"),
    NO_IMAGE(1002, "No Image Exsist"),
    UNKNOWN(9999, "Unknown Exception")
    ;

    private int errorCode;
    private String errorMessage;
}
