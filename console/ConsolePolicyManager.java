import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class Policy {
    private String policyId;
    private String policyHolderName;
    private String policyType;
    private double premiumAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdDateTime;

    public Policy(String policyId, String policyHolderName, String policyType, 
                  double premiumAmount, LocalDate startDate, LocalDate endDate, String status) {
        this.policyId = policyId;
        this.policyHolderName = policyHolderName;
        this.policyType = policyType;
        this.premiumAmount = premiumAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdDateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public String getPolicyId() { return policyId; }
    public void setPolicyId(String policyId) { this.policyId = policyId; }
    public String getPolicyHolderName() { return policyHolderName; }
    public void setPolicyHolderName(String name) { this.policyHolderName = name; }
    public String getPolicyType() { return policyType; }
    public void setPolicyType(String type) { this.policyType = type; }
    public double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(double amount) { this.premiumAmount = amount; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate date) { this.startDate = date; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate date) { this.endDate = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedDateTime() { return createdDateTime; }

    @Override
    public String toString() {
        return String.format("ID: %s | Holder: %s | Type: %s | Premium: %.2f | Status: %s | Start: %s | End: %s",
                policyId, policyHolderName, policyType, premiumAmount, status, startDate, endDate);
    }
}

public class ConsolePolicyManager {
    private HashMap<String, Policy> policies;
    private int policyCounter;
    private Scanner scanner;

    public ConsolePolicyManager() {
        policies = new HashMap<>();
        policyCounter = 1;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1: addPolicy(); break;
                case 2: viewAllPolicies(); break;
                case 3: updatePolicy(); break;
                case 4: deletePolicy(); break;
                case 5: searchByName(); break;
                case 6: filterByStatus(); break;
                case 7: 
                    System.out.println("Exiting...");
                    return;
                default: 
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Insurance Policy Management System ===");
        System.out.println("1. Add New Policy");
        System.out.println("2. View All Policies");
        System.out.println("3. Update Policy");
        System.out.println("4. Delete Policy");
        System.out.println("5. Search by Holder Name");
        System.out.println("6. Filter by Status");
        System.out.println("7. Exit");
    }

    private void addPolicy() {
        System.out.println("\n--- Add New Policy ---");
        String name = getStringInput("Policy Holder Name: ");
        String type = getStringInput("Policy Type (Life/Health/Vehicle/Property): ");
        double premium = getDoubleInput("Premium Amount: ");
        LocalDate startDate = getDateInput("Start Date (YYYY-MM-DD): ");
        LocalDate endDate = getDateInput("End Date (YYYY-MM-DD): ");
        String status = getStringInput("Status (Active/Expired/Cancelled): ");

        String policyId = "POL" + String.format("%04d", policyCounter++);
        Policy policy = new Policy(policyId, name, type, premium, startDate, endDate, status);
        policies.put(policyId, policy);
        System.out.println("Policy added successfully! ID: " + policyId);
    }

    private void viewAllPolicies() {
        System.out.println("\n--- All Policies ---");
        if (policies.isEmpty()) {
            System.out.println("No policies found.");
            return;
        }
        for (Policy policy : policies.values()) {
            System.out.println(policy);
        }
    }

    private void updatePolicy() {
        String id = getStringInput("Enter Policy ID to update: ");
        Policy policy = policies.get(id);
        if (policy == null) {
            System.out.println("Policy not found!");
            return;
        }
        
        System.out.println("Current: " + policy);
        String name = getStringInput("New Holder Name (or press Enter to skip): ");
        if (!name.isEmpty()) policy.setPolicyHolderName(name);
        
        String status = getStringInput("New Status (or press Enter to skip): ");
        if (!status.isEmpty()) policy.setStatus(status);
        
        System.out.println("Policy updated successfully!");
    }

    private void deletePolicy() {
        String id = getStringInput("Enter Policy ID to delete: ");
        if (policies.remove(id) != null) {
            System.out.println("Policy deleted successfully!");
        } else {
            System.out.println("Policy not found!");
        }
    }

    private void searchByName() {
        String name = getStringInput("Enter holder name to search: ").toLowerCase();
        System.out.println("\n--- Search Results ---");
        boolean found = false;
        for (Policy policy : policies.values()) {
            if (policy.getPolicyHolderName().toLowerCase().contains(name)) {
                System.out.println(policy);
                found = true;
            }
        }
        if (!found) System.out.println("No policies found.");
    }

    private void filterByStatus() {
        String status = getStringInput("Enter status to filter (Active/Expired/Cancelled): ");
        System.out.println("\n--- Filtered Policies ---");
        boolean found = false;
        for (Policy policy : policies.values()) {
            if (policy.getStatus().equalsIgnoreCase(status)) {
                System.out.println(policy);
                found = true;
            }
        }
        if (!found) System.out.println("No policies found with status: " + status);
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
            }
        }
    }

    public static void main(String[] args) {
        ConsolePolicyManager manager = new ConsolePolicyManager();
        manager.start();
    }
}
