package com.example.AdvocateLink.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
@AllArgsConstructor
@Getter
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String errorMessage;
    private String path;
}
