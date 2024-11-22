import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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
    public void should_return_nothing_systom_message_when_fetch_car_given_wrong_ticket(){
        try {
            //Given
            ParkingLot parkingLot = new ParkingLot();
            Car car= new Car();
            Ticket wrongTicket= new Ticket();
            //When
            parkingLot.fetch(wrongTicket);
        }catch (ParkingException e){
            System.out.println(e.getMessage());
        }
        //Then
        assertThat(systemOut()).contains("Unrecognized parking ticket");
    }

    @Test
    public void should_return_nothing_catch_exception_when_fetch_car_given_used_ticket(){
        try {
            //Given
            ParkingLot parkingLot = new ParkingLot();
            Car car= new Car();
            Ticket usedTicket= parkingLot.park(car);
            //When
            parkingLot.fetch(usedTicket);
            parkingLot.fetch(usedTicket);
        } catch (ParkingException e){
            System.out.println(e.getMessage());
        }
        //Then
        assertThat(systemOut()).contains("Unrecognized parking ticket");
    }

    @Test
    public void should_return_nothing_catch_parking_exception_when_parking_car_given_parking_lot(){
        try {
            //Given
            ParkingLot parkingLot = new ParkingLot();
            for (int i = 0; i < 10; i++) {
                parkingLot.park(new Car());
            }
            Car car = new Car();
            //When
            Ticket ticket = parkingLot.park(car);
        }catch (ParkingException e){
            System.out.println(e.getMessage());
        }
        //Then
        assertThat(systemOut()).contains("No available position");
    }

    private String systemOut() {
        return outContent.toString();
    }
}
