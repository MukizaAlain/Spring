package com.sandra.car_rentals.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandra.car_rentals.Model.Rentals;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, UUID> {

}
