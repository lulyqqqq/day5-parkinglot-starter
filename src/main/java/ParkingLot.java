import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    public static final int Max_Capacity = 10;
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    public static final String NO_AVAILABLE_POSITION = "No available position";
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
            throw new ParkingException(NO_AVAILABLE_POSITION) ;
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
            throw new ParkingException(UNRECOGNIZED_PARKING_TICKET);
        }

        return parkingRecords.remove(ticket);
    }
}
