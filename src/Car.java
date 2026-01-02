public class Car extends Vehicle {
    private int numberOfDoors;
    public Car(String model, int year, double basePrice, int numberOfDoors) {
        super(model, year, basePrice);
        setNumberOfDoors(numberOfDoors);
    }
    public int getNumberOfDoors() { return numberOfDoors; }
    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2 || numberOfDoors > 5) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 5");
        }
        this.numberOfDoors = numberOfDoors;
    }
    @Override
    public double calculateInsuranceFee() {
        int currentYear = java.time.Year.now().getValue();
        int age = getAge(currentYear);
        return getBasePrice() * 0.05 * (1 + age/10.0) * (1 + getNumberOfDoors()/10.0);
    }
    @Override
    public void performService() {
        System.out.println("Car Service: Oil change, tire rotation, brake check for " + getModel());
    }
    @Override
    public int getServiceIntervalKm() {
        return 10000;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(", Type: Car, Doors: %d", numberOfDoors);
    }
}