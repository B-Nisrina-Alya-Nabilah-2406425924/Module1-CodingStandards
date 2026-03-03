package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {

    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateWithoutId() {
        Car car = new Car();
        car.setCarName("Tesla");
        Car result = carRepository.create(car);
        assertNotNull(result.getCarId());
    }

    @Test
    void testUpdatePositive() {
        Car car = new Car();
        car.setCarId("1");
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Updated Name");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update("1", updatedCar);
        assertNotNull(result);
        assertEquals("Updated Name", result.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        Car result = carRepository.findById("tidak-ada");
        assertNull(result);
    }

    @Test
    void testCreateWithExistingId() {
        Car car = new Car();
        car.setCarId("id-sudah-ada");
        Car saved = carRepository.create(car);
        assertEquals("id-sudah-ada", saved.getCarId());
    }

    @Test
    void testUpdateNegative() {
        Car updatedCar = new Car();
        Car result = carRepository.update("non-existent", updatedCar);
        assertNull(result);
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarId("1");
        carRepository.create(car);
        carRepository.delete("1");

        Car result = carRepository.findById("1");
        assertNull(result);
    }
    @Test
    void testFindAllWithMultipleCars() {
        Car car1 = new Car();
        car1.setCarId("1");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("2");
        carRepository.create(car2);

        Iterator<Car> iterator = carRepository.findAll();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testUpdateExistingCar() {
        Car car = new Car();
        car.setCarId("1");
        car.setCarName("Old Name");
        carRepository.create(car);

        Car newCarData = new Car();
        newCarData.setCarName("New Name");
        newCarData.setCarColor("Red");
        newCarData.setCarQuantity(10);

        Car updated = carRepository.update("1", newCarData);

        assertNotNull(updated);
        assertEquals("New Name", updated.getCarName());
    }

    @Test
    void testUpdateNonExistentCar() {
        Car newCarData = new Car();
        Car result = carRepository.update("non-existent", newCarData);
        assertNull(result);
    }

    @Test
    void testDeleteNonExistentCar() {
        assertDoesNotThrow(() -> carRepository.delete("non-existent-id"));
    }

    @Test
    void testUpdateCarNotFound() {
        Car car = new Car();
        car.setCarId("ngawur");
        Car result = carRepository.update("non-existent-id", car);
        assertNull(result);
    }

    @Test
    void testDeleteCarThatDoesNotExist() {
        Car car = new Car();
        car.setCarId("ril");
        carRepository.create(car);

        carRepository.delete("palsyu");
        assertNotNull(carRepository.findById("ril"));
    }

    @Test
    void testFindByIdIfEmpty() {
        Car result = carRepository.findById("some-id");
        assertNull(result);
    }
}