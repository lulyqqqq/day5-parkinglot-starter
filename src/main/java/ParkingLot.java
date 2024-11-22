import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int Max_Capacity = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();
    public int capacity;

    public ParkingLot() {
        capacity = Max_Capacity;
    }

    private boolean checkCapacity(){
        return capacity == 0;
    }
    public Ticket park(Car car) {
        if (checkCapacity()) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        capacity--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        capacity++;
        return parkingRecords.remove(ticket);
    }
}
