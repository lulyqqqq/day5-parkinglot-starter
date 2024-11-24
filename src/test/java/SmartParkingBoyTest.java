import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    public void should_return_parking_lot_one_when_parking_car_and_parking_lot_parking_same_car__given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 5; i++) {
            smartParkingBoy.parkInSpecificParkingLot(new Car(),1);
        }

        for (int i = 0; i < 5; i++) {
            smartParkingBoy.parkInSpecificParkingLot(new Car(),2);
        }

        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
        assertEquals(firstParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_parking_lot_two_id_when_parking_car_and_parking_lot_one_parking_more_car_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 5; i++) {
            smartParkingBoy.parkInSpecificParkingLot(new Car(),1);
        }

        for (int i = 0; i < 3; i++) {
            smartParkingBoy.parkInSpecificParkingLot(new Car(),2);
        }

        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = smartParkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
        assertEquals(secondParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_right_car_ticket_when_parking_two_car_and_parking_not_full_given_two_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        Car fisrtCar =  new Car();
        //When
        Ticket ticketFirst = smartParkingBoy.parkInSpecificParkingLot(fisrtCar,1);
        Car fetchedCarFirst = smartParkingBoy.fetch(ticketFirst);

        //Then
        assertEquals(fisrtCar,fetchedCarFirst);
        assertEquals(firstParkingLot.parkingLotId,ticketFirst.getParkingLotId());

        Car secondCar = new Car();
        Ticket ticketSecond = smartParkingBoy.parkInSpecificParkingLot(secondCar,2);
        Car fetchedCarSecond = smartParkingBoy.fetch(ticketSecond);

        assertEquals(secondCar,fetchedCarSecond);
        assertEquals(secondParkingLot.parkingLotId,ticketSecond.getParkingLotId());
    }

    @Test
    public void should_return_error_message_when_no_parking_two_car_and_parking_not_full_given_wrong_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        //When
        Ticket wrongTicket = new Ticket();

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_error_message_and_throw_unrecognized_parking_ticket_exception_when_parking_two_car_and_parking_not_full_given_used_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        Car car =  new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_error_message_and_throw_no_available_position_exception_when_parking_two_car_and_parking_all_full(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot);
        smartParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 20; i++) {
            smartParkingBoy.park(new Car());
        }
        Car car =  new Car();

        //Then
        assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(car), NoAvailablePositionException.NO_AVAILABLE_POSITION);
    }
}
