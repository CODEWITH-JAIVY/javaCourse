public class ParkingSpot {

    private final int spotId;
    private final VehicleType vehicleType;
    private Vehicle vehicle;
    private boolean isfree;

    public ParkingSpot(int sportId, VehicleType vehicleType) {
        this.spotId = sportId;
        this.vehicleType = vehicleType;
        this.isfree = true;
    }


    public boolean assignVehicle(Vehicle vehicle) {
        System.out.println("Spot Type: " + vehicleType + " Free: " + isfree);
        if (isfree && vehicle.getVehicleType() == vehicleType) {  // agr free ho or agr vehical ki  type same ho
            isfree = false;
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    public void freeSpot() {
        isfree = true;
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

}