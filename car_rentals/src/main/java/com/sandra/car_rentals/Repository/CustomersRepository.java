package com.sandra.car_rentals.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandra.car_rentals.Model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, UUID> {
    boolean existsByNameAndEmail(String name, String email);

    Customers findByNameAndEmail(String name, String email);

    boolean existsByEmail(String email);

}
