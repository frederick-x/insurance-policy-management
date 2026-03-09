package com.insurance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "policies")
public class Policy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Policy holder name is required")
    @Column(nullable = false)
    private String policyHolderName;
    
    @NotBlank(message = "Policy type is required")
    @Column(nullable = false)
    private String policyType;
    
    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    @Column(nullable = false)
    private Double premiumAmount;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    
    @NotBlank(message = "Policy status is required")
    private String status;
    
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    
    @PrePersist
    protected void onCreate() {
        createdDateTime = LocalDateTime.now();
    }
    
    // Constructors
    public Policy() {}
    
    public Policy(String policyHolderName, String policyType, Double premiumAmount, 
                  LocalDate startDate, LocalDate endDate, String status) {
        this.policyHolderName = policyHolderName;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPolicyHolderName() { return policyHolderName; }
    public void setPolicyHolderName(String policyHolderName) { this.policyHolderName = policyHolderName; }
    
    public String getPolicyType() { return policyType; }
    public void setPolicyType(String policyType) { this.policyType = policyType; }
    
    public Double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(Double premiumAmount) { this.premiumAmount = premiumAmount; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedDateTime() { return createdDateTime; }
    public void setCreatedDateTime(LocalDateTime createdDateTime) { this.createdDateTime = createdDateTime; }
}
