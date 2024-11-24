public class SmartParkingBoy extends PackingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot, new SmartParkingBoyStrategy());
    }
}
