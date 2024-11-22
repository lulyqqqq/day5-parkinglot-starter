import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PackingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    public PackingBoy(ParkingLot parkingLot){
        if (parkingLot!=null){
            parkingLots.add(parkingLot);
        }
    }

    public Ticket park(Car car){
        Optional<ParkingLot> hasPositionParkingLot = parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isCapacityFull())
                .findFirst();

        if (hasPositionParkingLot.isPresent()){
            return hasPositionParkingLot.get().park(car);
        }else {
            throw new NoAvailablePositionException();
        }
    }

    public Car fetch(Ticket ticket) {
        Optional<ParkingLot> matchParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.isMatchParkingLot(ticket))
                .findFirst();
        if (matchParkingLot.isPresent()){
            return matchParkingLot.get().fetch(ticket);
        }else {
            throw new UnrecognizedParkingTicketException();
        }
    }

    public void addParkingLots(ParkingLot parkingLot){
        if (!parkingLots.contains(parkingLot)){
            parkingLots.add(parkingLot);
        }
    }
}
