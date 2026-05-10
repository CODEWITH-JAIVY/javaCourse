import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<ParkingSpot> createSpots() {
        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new ParkingSpot(1, VehicleType.CAR));
        spots.add(new ParkingSpot(2, VehicleType.BUS));
        spots.add(new ParkingSpot(3, VehicleType.TRUCK));
        return spots;
    }

    public static void main(String[] args) {

        // Create Parking Lot
        ParkingLot parkingLot = new ParkingLot();
//        ParkingSpot parkingSpot = new ParkingSpot() ;

        // Create Level
        List<ParkingSpot> spots = new ArrayList<>();
        Level level1 = new Level(1, createSpots());
        Level level2 = new Level(2, createSpots());
        Level level3 = new Level(3, createSpots());
        Level level4 = new Level(4, createSpots());

        parkingLot.addLevel(level1);
        parkingLot.addLevel(level2);
        parkingLot.addLevel(level3);
        parkingLot.addLevel(level4);


        // Create Vehicle
        Vehicle car = new Car("UP32AB1234");
        Vehicle bus = new bus("UP4390875");

        // Park Vehicle
//        boolean ticket = parkingLot.parkVehicle(car);
        boolean ticket = parkingLot.parkVehicle(bus);

        if (ticket) {
            System.out.println("Vehicle Parked Successfully");
        } else {
            System.out.println("No Space Available");
        }

        // Simulate Exit
        parkingLot.exitVehicle(car);
    }
}