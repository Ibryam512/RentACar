package com.example.rentacar.responses;

import org.springframework.http.HttpStatusCode;

public record Response<T>(
        T data,
        boolean success,
        HttpStatusCode statusCode,
        String message
) { }
