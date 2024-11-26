package com.example.rentacar.requests;

public record AcceptOfferRequest(
        int id,
        boolean isAccepted
) { }
