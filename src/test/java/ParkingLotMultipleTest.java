import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotMultipleTest {
    @Test
    public void should_return_parking_lot_one_when_parking_car_and_parking_not_fill_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
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
}
