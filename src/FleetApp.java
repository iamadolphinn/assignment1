import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class FleetApp {
    private List<Vehicle> vehicles = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    printAllVehicles();
                    break;
                case 2:
                    addCar();
                    break;
                case 3:
                    addBus();
                    break;
                case 4:
                    showTotalInsurance();
                    break;
                case 5:
                    showOlderVehicles();
                    break;
                case 6:
                    performService();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Fleet Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-7.");
            }
            System.out.println();
        }
        scanner.close();
    }
    private void printMenu() {
        System.out.println("=== Fleet Management System ===");
        System.out.println("1. Print all vehicles");
        System.out.println("2. Add new car");
        System.out.println("3. Add new bus");
        System.out.println("4. Show total yearly insurance fees");
        System.out.println("5. Show vehicles older than N years");
        System.out.println("6. Perform service for all vehicles");
        System.out.println("7. Quit");
        System.out.println("================================");
    }
    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
        } else {
            System.out.println("=== All Vehicles ===");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
            System.out.println("Total vehicles: " + vehicles.size());
        }
    }
    private void addCar() {
        try {
            System.out.println("=== Add New Car ===");
            System.out.print("Model: ");
            String model = scanner.nextLine();
            int year = getIntInput("Year: ");
            double price = getDoubleInput("Base price: ");
            int doors = getIntInput("Number of doors: ");
            Car car = new Car(model, year, price, doors);
            vehicles.add(car);
            System.out.println("Car added successfully! ID: " + car.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void addBus() {
        try {
            System.out.println("=== Add New Bus ===");
            System.out.print("Model: ");
            String model = scanner.nextLine();
            int year = getIntInput("Year: ");
            double price = getDoubleInput("Base price: ");
            int capacity = getIntInput("Passenger capacity: ");
            Bus bus = new Bus(model, year, price, capacity);
            vehicles.add(bus);
            System.out.println("Bus added successfully! ID: " + bus.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void showTotalInsurance() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        double total = 0;
        for (Vehicle v : vehicles) {
            total += v.calculateInsuranceFee();
        }
        System.out.printf("Total yearly insurance fees: $%.2f%n", total);
    }
    private void showOlderVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        int currentYear = getIntInput("Enter current year: ");
        int n = getIntInput("Older than how many years? ");
        System.out.println("=== Vehicles older than " + n + " years ===");
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.getAge(currentYear) > n) {
                System.out.println(v + " (Age: " + v.getAge(currentYear) + " years)");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles older than " + n + " years.");
        }
    }
    private void performService() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        System.out.println("=== Performing Service for All Vehicles ===");
        for (Vehicle v : vehicles) {
            Serviceable serviceable = v;
            serviceable.performService();
        }
        System.out.println("All vehicles serviced!");
    }
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
    }
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    public static void main(String[] args) {
        new FleetApp().run();
    }
}