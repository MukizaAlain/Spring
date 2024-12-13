package com.sandra.car_rentals.Controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.sandra.car_rentals.Model.Branch;
import com.sandra.car_rentals.Services.BranchServices;
import com.sandra.car_rentals.response.MessageResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    @Autowired
    private BranchServices branchService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> saveBranch(@RequestBody Branch branch) {
        MessageResponse messageResponse = branchService.saveBranch(branch);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branchesList = branchService.getAllBranches();
        return ResponseEntity.ok(branchesList);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> updateBranch(@RequestBody Branch branch) {
        MessageResponse messageResponse = branchService.updateBranch(branch);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> deleteBranch(@RequestBody Map<String, String> request) {
        UUID Id = UUID.fromString(request.get("Id"));
        MessageResponse messageResponse = branchService.deleteBranch(Id);
        return ResponseEntity.ok(messageResponse);
    }

}
