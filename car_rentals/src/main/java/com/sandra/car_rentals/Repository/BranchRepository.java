package com.sandra.car_rentals.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandra.car_rentals.Model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, UUID> {

    boolean existsByNameAndEmail(String name, String email);

    Branch findByNameAndEmail(String name, String email);

    boolean existsByEmail(String email);

    Optional<Branch> findById(UUID branchId);

}
