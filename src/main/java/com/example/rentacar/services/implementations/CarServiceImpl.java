package com.example.rentacar.services.implementations;

import com.example.rentacar.entities.Car;
import com.example.rentacar.repositories.CarRepository;
import com.example.rentacar.requests.CreateCarRequest;
import com.example.rentacar.requests.CreateOfferRequest;
import com.example.rentacar.requests.UpdateCarRequest;
import com.example.rentacar.responses.Response;
import com.example.rentacar.services.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Response<List<Car>> getCars() {
        List<Car> cars = carRepository.findAll();

        return new Response<>(
                cars,
                true,
                200,
                "Retrieved cars successfully"
        );
    }

    @Override
    public Response<List<Car>> getCarsByLocation(String location) {
        List<Car> cars = carRepository.findByAddress(location);

        return new Response<>(
                cars,
                true,
                200,
                "Retrieved cars successfully"
        );
    }

    @Override
    public Response<Car> getCarById(int id) {
        Optional<Car> car = carRepository.findById(id);

        return car.map(value -> new Response<>(
                value,
                true,
                200,
                "Retrieved car successfully"
        )).orElseGet(() -> new Response<>(
                null,
                false,
                404,
                "Car not found"
        ));
    }

    @Override
    public Response<Car> createCar(CreateCarRequest request) {
        Car car = new Car();

        car.setModel(request.model());
        car.setHorsePower(request.horsePower());
        car.setAddress(request.address());
        car.setPlate(request.plate());
        car.setPrice(request.price());

        carRepository.save(car);

        return new Response<>(
                car,
                true,
                201,
                "Created car successfully"
        );

    }

    @Override
    public Response<Car> updateCar(UpdateCarRequest request) {
        Optional<Car> optionalCar = carRepository.findById(request.id());

        if (optionalCar.isEmpty()) {
            return new Response<>(
                    null,
                    false,
                    404,
                    "Car not found"
            );
        }

        Car car = optionalCar.get();

        car.setId(request.id());
        car.setModel(request.model());
        car.setHorsePower(request.horsePower());
        car.setAddress(request.address());
        car.setPlate(request.plate());
        car.setPrice(request.price());
        car.setRented(request.isRented());

        carRepository.save(car);

        return new Response<>(
                car,
                true,
                200,
                "Updated car successfully"
        );
    }

    @Override
    public Response<Boolean> deleteCarById(int id) {
        Optional<Car> car = carRepository.findById(id);

        if (car.isEmpty()) {
            return new Response<>(
                    false,
                    false,
                    404,
                    "Car not found"
            );
        }

        carRepository.delete(car.get());

        return new Response<>(
                true,
                true,
                200,
                "Deleted car successfully"
        );
    }
}
