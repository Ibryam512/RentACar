package com.example.rentacar.requests;

public record CreateCarRequest(
        String model,
        int horsePower,
        String address,
        String plate,
        double price
) { }
