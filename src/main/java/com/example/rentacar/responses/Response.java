package com.example.rentacar.responses;

public record Response<T>(
        T data,
        boolean success,
        int statusCode,
        String message
) { }
