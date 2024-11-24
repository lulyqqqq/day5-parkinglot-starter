import java.util.Comparator;
import java.util.Optional;

public class SuperParkingBoyStrategy implements ParkingStrategy {

    @Override
    public Ticket park(Car car, PackingBoy packingBoy) {
        Optional<ParkingLot> bestParkingLot = packingBoy.getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull())
                .max(Comparator.comparingDouble(ParkingLot::getAvailabilityRate));

        if (bestParkingLot.isPresent()) {
            return bestParkingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
