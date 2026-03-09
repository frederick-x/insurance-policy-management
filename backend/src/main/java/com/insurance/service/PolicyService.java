package com.insurance.service;

import com.insurance.model.Policy;
import com.insurance.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    
    @Autowired
    private PolicyRepository policyRepository;
    
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
    
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
    
    public Optional<Policy> getPolicyById(Long id) {
        return policyRepository.findById(id);
    }
    
    public Policy updatePolicy(Long id, Policy policyDetails) {
        Policy policy = policyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));
        
        policy.setPolicyHolderName(policyDetails.getPolicyHolderName());
        policy.setPolicyType(policyDetails.getPolicyType());
        policy.setPremiumAmount(policyDetails.getPremiumAmount());
        policy.setStartDate(policyDetails.getStartDate());
        policy.setEndDate(policyDetails.getEndDate());
        policy.setStatus(policyDetails.getStatus());
        
        return policyRepository.save(policy);
    }
    
    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new RuntimeException("Policy not found with id: " + id);
        }
        policyRepository.deleteById(id);
    }
    
    public List<Policy> searchByName(String name) {
        return policyRepository.findByPolicyHolderNameContainingIgnoreCase(name);
    }
    
    public List<Policy> filterByStatus(String status) {
        return policyRepository.findByStatus(status);
    }
}
