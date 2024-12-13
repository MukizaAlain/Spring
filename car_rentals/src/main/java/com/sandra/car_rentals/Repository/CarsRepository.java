package com.sandra.car_rentals.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

import com.sandra.car_rentals.Model.Cars;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Cars, UUID> {
    boolean existsByNameAndModelAndPlateNumber(String name, String model, String plateNumber);

    Cars findByNameAndModelAndPlateNumber(String name, String model, String plateNumber);

    Optional<Cars> findById(UUID id);

}
