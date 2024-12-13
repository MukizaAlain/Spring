package com.sandra.car_rentals.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandra.car_rentals.Model.Cars;
import com.sandra.car_rentals.Repository.CarsRepository;
import com.sandra.car_rentals.response.MessageResponse;

@Service
public class CarsServices {

    @Autowired
    private CarsRepository carsRepository;

    public MessageResponse saveCars(Cars car) {
        // Check if a car with the same name, model, and plate number already exists
        if (!carsRepository.existsByNameAndModelAndPlateNumber(car.getName(), car.getModel(), car.getPlateNumber())) {
            carsRepository.save(car);
            return new MessageResponse("saved successfully");
        } else {
            return new MessageResponse("car already exists");
        }
    }

    public List<Cars> getAllCars() {
        return carsRepository.findAll();
    }

    public MessageResponse updateCars(Cars car) {
        // Check if the car exists based on model and plate number
        if (carsRepository.existsByNameAndModelAndPlateNumber(car.getName(), car.getModel(), car.getPlateNumber())) {
            // Find the existing car based on model and plate number
            Cars existingCar = carsRepository.findByNameAndModelAndPlateNumber(
                    car.getName(), car.getModel(), car.getPlateNumber());

            // Update the existing car with the new values
            existingCar.setName(car.getName());
            existingCar.setModel(car.getModel());
            existingCar.setPlateNumber(car.getPlateNumber());
            existingCar.setRentingPrice(car.getRentingPrice());

            // Save the updated car
            carsRepository.save(existingCar);
            return new MessageResponse("Updated successfully");
        } else {
            return new MessageResponse("Car not found");
        }
    }

    public MessageResponse deleteCars(UUID carId) {
        // Find the car based on name, model, and plate number
        Optional<Cars> car = carsRepository.findById(carId);

        if (car.isPresent()) {
            // Delete the car
            carsRepository.delete(car.get());
            return new MessageResponse("Car deleted successfully");
        } else {
            return new MessageResponse("Car not found");
        }
    }
    // public MessageResponse deleteCars(String name, String model, String
    // plateNumber) {
    // // Find the car based on name, model, and plate number
    // Cars car = carsRepository.findByNameAndModelAndPlateNumber(name, model,
    // plateNumber);

    // if (car != null) {
    // // Delete the car
    // carsRepository.delete(car);
    // return new MessageResponse("Car deleted successfully");
    // } else {
    // return new MessageResponse("Car not found");
    // }
    // }
}
