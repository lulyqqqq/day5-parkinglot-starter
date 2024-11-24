import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyPatternTest {
    @Test
    public void should_return_ticket_when_using_smart_parking_boy_strategy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);

        PackingBoy smartParkingBoy = new PackingBoy(firstParkingLot, new SmartParkingBoyStrategy());
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
