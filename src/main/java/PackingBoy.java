public class PackingBoy {
    private ParkingLot parkingLot;

    public PackingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public Ticket park(Car car) {
        return parkingLot.park(car);
    }
}
