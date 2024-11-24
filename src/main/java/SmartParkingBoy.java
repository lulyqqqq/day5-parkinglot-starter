import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends PackingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public Ticket park(Car car) {
        Optional<ParkingLot> hasPositionParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull()).min(Comparator.comparingInt(ParkingLot::getCapacity)
                        .reversed()
                        .thenComparingInt(parkingLots::indexOf));

        if (hasPositionParkingLot.isPresent()) {
            return hasPositionParkingLot.get().park(car);
        } else {
            throw new NoAvailablePositionException();
        }
    }

    public Car fetch(Ticket ticket) {
        Optional<ParkingLot> matchParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.isMatchParkingLot(ticket))
                .findFirst();
        if (matchParkingLot.isPresent()) {
            return matchParkingLot.get().fetch(ticket);
        } else {
            throw new UnrecognizedParkingTicketException();
        }
    }

}
