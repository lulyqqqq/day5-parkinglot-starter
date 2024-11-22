import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotMultipleTest {

    @Test
    public void should_return_parking_lot_one_when_parking_car_and_parking_not_full_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car=  new Car();
        //When
        Ticket ticket = packingBoy.park(car);
        Car fetchedCar = packingBoy.fetch(ticket);

        //Then
        assertEquals(car,fetchedCar);
        assertEquals(firstParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_parking_lot_first_full_and_parking_second_when_parking_car_and_parking_not_full_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car=  new Car();
        //When
        for (int i = 0; i < 10; i++) {
            packingBoy.park(new Car());
        }
        Ticket ticket = packingBoy.park(car);

        Car fetchedCar = packingBoy.fetch(ticket);

        //Then
        assertEquals(car,fetchedCar);
        assertEquals(secondParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_right_car_ticket_when_parking_two_car_and_parking_not_full_given_two_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car =  new Car();
        //When
        Ticket ticketFirst = packingBoy.park(car);
        Car fetchedCarFirst = packingBoy.fetch(ticketFirst);

        for (int i = 0; i < 10; i++) {
            packingBoy.park(new Car());
        }

        //Then
        assertEquals(car,fetchedCarFirst);
        assertEquals(firstParkingLot.parkingLotId,ticketFirst.getParkingLotId());

        Ticket ticketSecond = packingBoy.park(car);
        Car fetchedCarSecond = packingBoy.fetch(ticketSecond);

        assertEquals(car,fetchedCarSecond);
        assertEquals(secondParkingLot.parkingLotId,ticketSecond.getParkingLotId());
    }

    @Test
    public void should_return_error_message_when_parking_two_car_and_parking_not_full_given_wrong_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car =  new Car();
        //When
        Ticket wrongTicket = new Ticket();

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> packingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_error_message_and_throw_unrecognized_parking_ticket_exception_when_parking_two_car_and_parking_not_full_given_used_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car =  new Car();
        //When
        Ticket ticket = packingBoy.park(car);
        packingBoy.fetch(ticket);

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> packingBoy.fetch(ticket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }





}
