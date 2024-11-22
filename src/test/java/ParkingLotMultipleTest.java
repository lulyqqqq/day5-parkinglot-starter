import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
