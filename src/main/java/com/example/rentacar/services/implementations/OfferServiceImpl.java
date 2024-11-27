package com.example.rentacar.services.implementations;

import com.example.rentacar.entities.Car;
import com.example.rentacar.entities.Client;
import com.example.rentacar.entities.Offer;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.repositories.ClientRepository;
import com.example.rentacar.repositories.OfferRepository;
import com.example.rentacar.requests.AcceptOfferRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    private final String[] allowedLocations = {"Sofia", "Plovdiv", "Burgas", "Varna"};

    public OfferServiceImpl(
            OfferRepository offerRepository,
            ClientRepository clientRepository,
            CarRepository carRepository
    ) {
        this.offerRepository = offerRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Response<List<Offer>> getOffers() {
        List<Offer> offers = offerRepository.findAll();

        return new Response<>(
                offers,
                true,
                HttpStatus.OK,
                "Retrieved offers successfully"
        );
    }

    @Override
    public Response<Offer> getOfferById(int id) {
        Optional<Offer> offer = offerRepository.findById(id);

        return offer.map(value -> new Response<>(
                value,
                true,
                HttpStatus.OK,
                "Retrieved offer successfully"
        )).orElseGet(() -> new Response<>(
                null,
                false,
                HttpStatus.NOT_FOUND,
                "Offer not found"
        ));
    }

    @Override
    public Response<Offer> createOffer(CreateOfferRequest request) {
        if (Arrays.stream(allowedLocations).noneMatch(s -> s.equals(request.clientAddress()))) {
            return new Response<>(
                    null,
                    false,
                    HttpStatus.FORBIDDEN,
                    "The address cannot be outside the preferred regions"
            );
        }

        List<Car> cars = carRepository
                            .findByModel(request.carModel())
                            .stream()
                            .filter(c -> !c.isRented())
                            .toList();

        if (cars.isEmpty()) {
            return new Response<>(
                    null,
                    false,
                    HttpStatus.NOT_FOUND,
                    "Free car of brand " + request.carModel() + " not found"
            );
        }

        var car = cars.getFirst();
        var client = new Client();

        client.setName(request.clientName());
        client.setAddress(request.clientAddress());
        client.setPhone(request.clientPhone());
        client.setAge(request.clientAge());
        client.setHasCrashed(request.hasCrashed());

        clientRepository.save(client);

        Offer offer = new Offer();

        offer.setClient(client);
        offer.setDays(request.days());
        offer.setCar(car);
        offer.setPrice(calculatePrice(request.days(), car.getPrice()));

        offerRepository.save(offer);

        return new Response<>(
                offer,
                true,
                HttpStatus.CREATED,
                "Offer created successfully"
        );
    }

    @Override
    public Response<Offer> acceptOffer(AcceptOfferRequest request) {
        Optional<Offer> optionalOffer = offerRepository.findById(request.id());

        if (optionalOffer.isEmpty()) {
            return new Response<>(
                    null,
                    false,
                    HttpStatus.NOT_FOUND,
                    "Offer not found"
            );
        }

        Offer offer = optionalOffer.get();
        offer.setAccepted(request.isAccepted());

        Optional<Car> optionalCar = carRepository.findById(offer.getCar().getId());

        if (optionalCar.isEmpty()) {
            return new Response<>(
                    null,
                    false,
                    HttpStatus.NOT_FOUND,
                    "Car not found"
            );
        }

        Car car = optionalCar.get();
        car.setRented(true);
        carRepository.save(car);

        offerRepository.save(offer);

        return new Response<>(
                offer,
                true,
                HttpStatus.OK,
                "Offer accepted successfully"
        );
    }

    @Override
    public Response<Boolean> deleteOffer(int id) {
        Optional<Offer> offer = offerRepository.findById(id);

        if (offer.isEmpty()) {
            return new Response<>(
                    false,
                    false,
                    HttpStatus.NOT_FOUND,
                    "Offer not found"
            );
        }

        offerRepository.delete(offer.get());

        return new Response<>(
                true,
                true,
                HttpStatus.OK,
                "Deleted offer successfully"
        );
    }

    private double calculatePrice(int days, double carPrice) {
        return days * carPrice;
    }
}
