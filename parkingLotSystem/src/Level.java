import java.util.List;

public class Level {
    private final int levelnumber;
    private final List<ParkingSpot> spots;

    Level(int levelnumber, List<ParkingSpot> spots) {
        this.levelnumber = levelnumber;
        this.spots = spots;
    }

    public boolean ParkVehicle(Vehicle vehicle) {
        System.out.println("Checking spot for vehicle: " + vehicle.getVehicleType());
        for (ParkingSpot spot : spots) {
            if (spot.assignVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getVehicle() == vehicle) {
                spot.freeSpot();
                return true;
            }
        }
        return false;
    }

}