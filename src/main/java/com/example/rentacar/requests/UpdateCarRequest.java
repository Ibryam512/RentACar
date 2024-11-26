package com.example.rentacar.requests;

public record UpdateCarRequest(
        int id,
        String model,
        int horsePower,
        String address,
        String plate,
        double price,
        boolean isRented
) { }
