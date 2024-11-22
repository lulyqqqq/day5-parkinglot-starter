import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int Max_Capacity = 10;
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    public static final String NO_AVAILABLE_POSITION = "No available position";
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    public int capacity;

    public ParkingLot() {
        capacity = Max_Capacity;
    }

    private boolean checkCapacity() {
        return capacity == 0;
    }

    public Ticket park(Car car) {
        if (checkCapacity()) {
            throw new ParkingException(NO_AVAILABLE_POSITION) ;
        }
        Ticket ticket = new Ticket();
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
