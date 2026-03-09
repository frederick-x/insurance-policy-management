package com.insurance.controller;

import com.insurance.model.Policy;
import com.insurance.service.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = "*")
public class PolicyController {
    
    @Autowired
    private PolicyService policyService;
    
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@Valid @RequestBody Policy policy) {
        Policy createdPolicy = policyService.createPolicy(policy);
        return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        List<Policy> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @Valid @RequestBody Policy policy) {
        try {
            Policy updatedPolicy = policyService.updatePolicy(id, policy);
            return ResponseEntity.ok(updatedPolicy);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        try {
            policyService.deletePolicy(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Policy>> searchByName(@RequestParam String name) {
        List<Policy> policies = policyService.searchByName(name);
        return ResponseEntity.ok(policies);
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Policy>> filterByStatus(@RequestParam String status) {
        List<Policy> policies = policyService.filterByStatus(status);
        return ResponseEntity.ok(policies);
    }
}
