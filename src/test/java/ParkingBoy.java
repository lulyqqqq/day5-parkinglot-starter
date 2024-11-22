import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoy {

    @Test
    public void should_return_ticket_when_parking_given_a_car_and_a_parking_lot(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Car car=  new Car();
        //When
        Ticket ticket = packingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = packingBoy.park(car);
        //When
        Car fetchedCar = packingBoy.fetch(ticket);
        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_right_car_and_ticket_when_fetch_given_two_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Car carFirst = new Car();
        Car carSecond = new Car();
        Ticket ticketFirst = packingBoy.park(carFirst);
        Ticket ticketSecond = packingBoy.park(carSecond);
        //When
        Car fetchedCarFirst = packingBoy.fetch(ticketFirst);
        Car fetchedCarSecond = packingBoy.fetch(ticketSecond);
        //Then
        assertEquals(carFirst, fetchedCarFirst);
        assertEquals(carSecond, fetchedCarSecond);
    }

    @Test
    public void should_return_nothing_catch_exception_when_fetch_car_given_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Ticket wrongTicket = new Ticket();
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> packingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_nothing_catch_exception_when_fetch_car_given_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Car car = new Car();
        Ticket usedTicket = packingBoy.park(car);
        //When
        parkingLot.fetch(usedTicket);
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> packingBoy.fetch(usedTicket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_nothing_catch_parking_exception_when_parking_car_given_parking_lot() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        PackingBoy packingBoy = new PackingBoy(parkingLot);
        Car car = new Car();
        //When
        for (int i = 0; i < 10; i++) {
            packingBoy.park(new Car());
        }
        //Then
        assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car), NoAvailablePositionException.NO_AVAILABLE_POSITION);
    }

}
