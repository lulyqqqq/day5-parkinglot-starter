import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    @Test
    public void should_return_parking_lot_one_when_parking_car_and_parking_lot_parking_rate_different_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1,5);
        ParkingLot secondParkingLot = new ParkingLot(2,7);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 4; i++) {
            superParkingBoy.parkInSpecificParkingLot(new Car(),1);
        }

        for (int i = 0; i < 2; i++) {
            superParkingBoy.parkInSpecificParkingLot(new Car(),2);
        }

        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        Car fetchedCar = superParkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
        assertEquals(firstParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_parking_lot_second_id_when_parking_car_and_parking_lot_parking_rate_different_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1,5);
        ParkingLot secondParkingLot = new ParkingLot(2,7);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 2; i++) {
            superParkingBoy.parkInSpecificParkingLot(new Car(),1);
        }

        for (int i = 0; i < 6; i++) {
            superParkingBoy.parkInSpecificParkingLot(new Car(),2);
        }

        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        Car fetchedCar = superParkingBoy.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
        assertEquals(secondParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_right_car_ticket_when_parking_two_car_and_parking_not_full_given_two_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        Car fisrtCar =  new Car();
        //When
        Ticket ticketFirst = superParkingBoy.parkInSpecificParkingLot(fisrtCar,1);
        Car fetchedCarFirst = superParkingBoy.fetch(ticketFirst);

        //Then
        assertEquals(fisrtCar,fetchedCarFirst);
        assertEquals(firstParkingLot.parkingLotId,ticketFirst.getParkingLotId());

        Car secondCar = new Car();
        Ticket ticketSecond = superParkingBoy.parkInSpecificParkingLot(secondCar,2);
        Car fetchedCarSecond = superParkingBoy.fetch(ticketSecond);

        assertEquals(secondCar,fetchedCarSecond);
        assertEquals(secondParkingLot.parkingLotId,ticketSecond.getParkingLotId());
    }

    @Test
    public void should_return_error_message_when_no_parking_two_car_and_parking_not_full_given_wrong_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        //When
        Ticket wrongTicket = new Ticket();

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(wrongTicket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    public void should_return_error_message_and_throw_unrecognized_parking_ticket_exception_when_parking_two_car_and_parking_not_full_given_used_ticket(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        Car car =  new Car();
        //When
        Ticket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);

        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(ticket), UnrecognizedParkingTicketException.UNRECOGNIZED_PARKING_TICKET);

    }

    @Test
    public void should_return_error_message_and_throw_no_available_position_exception_when_parking_two_car_and_parking_all_full(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot);
        superParkingBoy.addParkingLots(secondParkingLot);
        //When
        for (int i = 0; i < 20; i++) {
            superParkingBoy.park(new Car());
        }
        Car car =  new Car();

        //Then
        assertThrows(NoAvailablePositionException.class, () -> superParkingBoy.park(car), NoAvailablePositionException.NO_AVAILABLE_POSITION);
    }

}
