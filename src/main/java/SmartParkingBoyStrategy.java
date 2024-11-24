import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoyStrategy implements ParkingStrategy {
    @Override
    public Ticket park(Car car, PackingBoy packingBoy) {
        Optional<ParkingLot> hasPositionParkingLot = packingBoy.getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull()).min(Comparator.comparingInt(ParkingLot::getCapacity)
                        .reversed()
                        .thenComparingInt(packingBoy.getParkingLots()::indexOf));

        if (hasPositionParkingLot.isPresent()) {
            return hasPositionParkingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
