import java.util.Comparator;
import java.util.Optional;

public class SuperParkingBoy extends PackingBoy {

    public SuperParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Ticket park(Car car) {
        Optional<ParkingLot> bestParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailabilityRate));

        if (bestParkingLot.isPresent()) {
            return bestParkingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }

}
