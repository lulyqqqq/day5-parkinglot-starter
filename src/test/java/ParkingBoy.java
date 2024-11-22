import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
