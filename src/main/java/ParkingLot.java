import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public int maxCapacity = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    public int capacity;
    public int parkingLotId;

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot() {
        capacity = maxCapacity;
    }

    public ParkingLot(int parkingLotId){
        capacity = maxCapacity;
        this.parkingLotId = parkingLotId;
    }

    public ParkingLot(int parkingLotId, int capacity) {
        this.capacity = capacity;
        this.maxCapacity = capacity;
        this.parkingLotId = parkingLotId;
    }

    public double getAvailabilityRate() {
        return (double) (maxCapacity - capacity) / maxCapacity;
    }

    public boolean isMatchParkingLot(Ticket ticket){
        return ticket.ParkingLotId == this.parkingLotId;
    }

    public boolean isCapacityFull() {
        return capacity == 0;
    }

    public Ticket park(Car car) {
        if (isCapacityFull()) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket();
        ticket.setParkingLotId(parkingLotId);
        parkingRecords.put(ticket, car);
        capacity--;
        return ticket;
    }

    public Car fetch(Ticket ticket){
        capacity++;
        if (!parkingRecords.containsKey(ticket)) {
            throw new UnrecognizedParkingTicketException();
        }

        return parkingRecords.remove(ticket);
    }
}
