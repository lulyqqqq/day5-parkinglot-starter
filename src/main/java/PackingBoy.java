public class PackingBoy {
    private ParkingLot parkingLot;

    public PackingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public Ticket park(Car car) throws ParkingException{
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) throws ParkingException{
        return parkingLot.fetch(ticket);
    }
}
