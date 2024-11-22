import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int Max_Capacity = 10;


    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    public int capacity;
    public int parkingLotId;

    public ParkingLot() {
        capacity = Max_Capacity;
    }


    public ParkingLot(int parkingLotId){
        capacity = Max_Capacity;
        this.parkingLotId = parkingLotId;
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
