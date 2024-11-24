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

    @Test
    public void should_return_ticket_when_using_default_parking_boy_strategy() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        PackingBoy packingBoy = new PackingBoy(firstParkingLot);
        packingBoy.addParkingLots(secondParkingLot);
        Car car=  new Car();
        //When
        for (int i = 0; i < 10; i++) {
            packingBoy.parkInSpecificParkingLot(new Car(),1);
        }
        Ticket ticket = packingBoy.park(car);

        Car fetchedCar = packingBoy.fetch(ticket);

        //Then
        assertEquals(car,fetchedCar);
        assertEquals(secondParkingLot.parkingLotId,ticket.getParkingLotId());
    }

    @Test
    public void should_return_parking_lot_one_when_parking_car_and_parking_lot_parking_rate_different_given_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot(1,5);
        ParkingLot secondParkingLot = new ParkingLot(2,7);
        PackingBoy superParkingBoy = new PackingBoy(firstParkingLot,new SuperParkingBoyStrategy());
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
}
