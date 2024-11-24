import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PackingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy = new EarliestParkingLotStrategy();

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public PackingBoy(ParkingLot parkingLot){
        if (parkingLot!=null){
            parkingLots.add(parkingLot);
        }
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public PackingBoy(ParkingLot parkingLot, ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
        if (parkingLot != null) {
            parkingLots.add(parkingLot);
        }
    }

    public Ticket park(Car car) {
        return parkingStrategy.park(car, this);
    }

    public Ticket parkInSpecificParkingLot(Car car, int parkingLotId) {
        Optional<ParkingLot> targetParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.parkingLotId == parkingLotId)
                .findFirst();

        if (targetParkingLot.isPresent()) {
            ParkingLot parkingLot = targetParkingLot.get();
            if (!parkingLot.isCapacityFull()) {
                return parkingLot.park(car);
            } else {
                throw new NoAvailablePositionException();
            }
        } else {
            throw new IllegalArgumentException("Invalid ParkingLot ID: " + parkingLotId);
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
