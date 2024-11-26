package com.example.rentacar.services.implementations;

import com.example.rentacar.entities.Offer;
import com.example.rentacar.requests.AcceptOfferRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    @Override
    public Response<List<Offer>> getOffers() {
        return null;
    }

    @Override
    public Response<Offer> getOfferById(int id) {
        return null;
    }

    @Override
    public Response<Offer> createOffer(CreateOfferRequest request) {
        return null;
    }

    @Override
    public Response<Offer> acceptOffer(AcceptOfferRequest request) {
        return null;
    }

    @Override
    public Response<Boolean> deleteOffer(int id) {
        return null;
    }
}
