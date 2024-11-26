package com.example.rentacar.requests;

public record CreateOfferRequest(
        String clientName,
        String clientAddress,
        String clientPhone,
        int clientAge,
        boolean hasCrashed,
        String carModel,
        int days
) { }
