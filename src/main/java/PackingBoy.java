public class PackingBoy {
    private ParkingLot parkingLot;

    public PackingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public Ticket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }
}