import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
