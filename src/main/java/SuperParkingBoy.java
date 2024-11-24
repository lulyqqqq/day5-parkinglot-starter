import java.util.Comparator;
import java.util.Optional;

public class SuperParkingBoy extends PackingBoy {

    public SuperParkingBoy(ParkingLot parkingLot) {
        super(parkingLot,new SuperParkingBoyStrategy());
    }
}
