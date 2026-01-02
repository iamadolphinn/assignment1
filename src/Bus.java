public class Bus extends Vehicle {
    private int passengerCapacity;
    public Bus(String model, int year, double basePrice, int passengerCapacity) {
        super(model, year, basePrice);
        setPassengerCapacity(passengerCapacity);
    }
    public int getPassengerCapacity() { return passengerCapacity; }
    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be greater than 0");
        }
        this.passengerCapacity = passengerCapacity;
    }
    @Override
    public double calculateInsuranceFee() {
        int currentYear = java.time.Year.now().getValue();
        int age = getAge(currentYear);
        return getBasePrice() * 0.03 * (1 + age/5.0) * (1 + passengerCapacity/50.0);
    }
    @Override
    public void performService() {
        System.out.println("Bus Service: Safety inspection, brake system check, emergency exit test for " + getModel());
    }
    @Override
    public int getServiceIntervalKm() {
        return 5000;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(", Type: Bus, Capacity: %d passengers", passengerCapacity);
    }
}