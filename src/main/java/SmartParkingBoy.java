import java.util.Comparator;
import java.util.Optional;

public class SmartParkingBoy extends PackingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot, new SmartParkingBoyStrategy());
    }
}
