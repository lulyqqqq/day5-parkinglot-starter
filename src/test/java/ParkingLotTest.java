import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_when_park_given_a_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_car_when_fetch_given_a_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchedCar =  parkingLot.fetch(ticket);
        //Then
        assertEquals(car,fetchedCar);
    }

    @Test
    public void should_return_right_car_and_ticket_when_fetch_given_two_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car carFirst= new Car();
        Car carSecond= new Car();
        Ticket ticketFirst = parkingLot.park(carFirst);
        Ticket ticketSecond = parkingLot.park(carSecond);
        //When
        Car fetchedCarFirst =  parkingLot.fetch(ticketFirst);
        Car fetchedCarSecond =  parkingLot.fetch(ticketSecond);
        //Then
        assertEquals(carFirst,fetchedCarFirst);
        assertEquals(carSecond,fetchedCarSecond);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_wrong_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car= new Car();
        Ticket wrongTicket= new Ticket();
        //When
        Car fetchedCar = parkingLot.fetch(wrongTicket);
        //Then
        assertNull(fetchedCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_used_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car= new Car();
        Ticket usedTicket= new Ticket();
        //When
        parkingLot.fetch(usedTicket);
        Car fetchedCarUsed = parkingLot.fetch(usedTicket);
        //Then
        assertNull(fetchedCarUsed);
    }

}
