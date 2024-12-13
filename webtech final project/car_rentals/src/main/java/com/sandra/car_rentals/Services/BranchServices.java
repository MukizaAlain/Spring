package com.sandra.car_rentals.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandra.car_rentals.Model.Branch;
import com.sandra.car_rentals.Repository.BranchRepository;
import com.sandra.car_rentals.response.MessageResponse;

@Service
public class BranchServices {

    @Autowired
    private BranchRepository branchRepository;

    public MessageResponse saveBranch(Branch branch) {
        // Check if a branch with the same email already exists
        if (branchRepository.existsByEmail(branch.getEmail())) {
            return new MessageResponse("Email is already taken");
        }

        // Check if a branch with the same name already exists
        if (!branchRepository.existsByNameAndEmail(branch.getName(), branch.getEmail())) {
            branchRepository.save(branch);
            return new MessageResponse("Branch saved successfully");
        } else {
            return new MessageResponse("Branch already exists");
        }
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public MessageResponse updateBranch(Branch branch) {
        // Check if the branch exists based on name
        if (branchRepository.existsByNameAndEmail(branch.getName(), branch.getEmail())) {
            // Find the existing branch based on name
            Branch existingBranch = branchRepository.findByNameAndEmail(branch.getName(), branch.getEmail());

            // Update the existing branch with the new values
            existingBranch.setEmail(branch.getEmail());
            existingBranch.setCity(branch.getCity());
            existingBranch.setCountry(branch.getCountry());

            // Save the updated branch
            branchRepository.save(existingBranch);
            return new MessageResponse("Branch updated successfully");
        } else {
            return new MessageResponse("Branch not found");
        }
    }

    public MessageResponse deleteBranch(UUID Id) {
       
        Optional<Branch> branch = branchRepository.findById(Id);

        if (branch.isPresent()) {
            
            branchRepository.delete(branch.get());
            return new MessageResponse("Branch deleted successfully");
        } else {
            return new MessageResponse("Branch not found");
        }
    }

    // public MessageResponse deleteBranch(String name, String email) {
    // // Find the branch based on name
    // Branch branch = branchRepository.findByNameAndEmail(name, email);

    // if (branch != null) {
    // // Delete the branch
    // branchRepository.delete(branch);
    // return new MessageResponse("Branch deleted successfully");
    // } else {
    // return new MessageResponse("Branch not found");
    // }
    // }

}
