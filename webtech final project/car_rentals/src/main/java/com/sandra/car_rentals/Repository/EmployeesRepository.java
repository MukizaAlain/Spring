package com.sandra.car_rentals.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandra.car_rentals.Model.Employees;

import java.util.UUID;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, UUID> {

    boolean existsByNameAndEmail(String name, String email);

    Employees findByNameAndEmail(String name, String email);

    boolean existsByEmail(String email);

}
