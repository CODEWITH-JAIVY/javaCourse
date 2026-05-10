import java.util.ArrayList;
import java.util.List;

class ParkingLot {
    private final List<Level> listOfLevels;

    ParkingLot() {
        listOfLevels = new ArrayList<>();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : listOfLevels) {
            System.out.println(level);
            if (level.ParkVehicle(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public void addLevel(Level level1) {

        listOfLevels.add(level1);
    }

    public void exitVehicle(Vehicle vehicle) {
        for (Level level : listOfLevels) {
            if (level.removeVehicle(vehicle)) {
                System.out.println("Vehicle exited successfully");
                return;
            }
        }
        System.out.println("Vehicle not found at the time of exiting of vehicle ");
    }
}