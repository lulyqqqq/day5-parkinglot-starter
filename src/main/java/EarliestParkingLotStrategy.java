import java.util.Optional;

public class EarliestParkingLotStrategy implements ParkingStrategy {

    @Override
    public Ticket park(Car car, PackingBoy packingBoy) {
        Optional<ParkingLot> firstAvailableParkingLot = packingBoy.getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull())
                .findFirst();

        if (firstAvailableParkingLot.isPresent()) {
            return firstAvailableParkingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }
}
