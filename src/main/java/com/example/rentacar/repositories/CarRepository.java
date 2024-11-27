package com.example.rentacar.repositories;

import com.example.rentacar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByAddress(String address);
    List<Car> findByModel(String model);
}
