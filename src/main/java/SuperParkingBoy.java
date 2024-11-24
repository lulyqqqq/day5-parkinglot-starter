public class SuperParkingBoy extends PackingBoy {

    public SuperParkingBoy(ParkingLot parkingLot) {
        super(parkingLot,new SuperParkingBoyStrategy());
    }
}
