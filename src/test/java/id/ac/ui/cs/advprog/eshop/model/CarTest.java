package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    @Test
    void testCarConstructorAndGetters() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Tesla");
        car.setCarColor("White");
        car.setCarQuantity(5);

        assertEquals("123", car.getCarId());
        assertEquals("Tesla", car.getCarName());
        assertEquals("White", car.getCarColor());
        assertEquals(5, car.getCarQuantity());
    }
}