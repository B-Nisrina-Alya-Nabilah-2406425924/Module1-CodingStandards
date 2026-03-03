package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-123");
        car.setCarName("Honda Civic");
    }

    @Test
    void testCreate() {
        Mockito.when(carRepository.create(car)).thenReturn(car);
        Car result = carService.create(car);
        assertEquals(car.getCarId(), result.getCarId());
    }

    @Test
    void testFindAll() {
        List<Car> list = new ArrayList<>();
        list.add(car);
        Mockito.when(carRepository.findAll()).thenReturn(list.iterator());

        List<Car> result = carService.findAll();
        assertEquals(1, result.size());
        assertEquals(car.getCarId(), result.get(0).getCarId());
    }

    @Test
    void testFindById() {
        Mockito.when(carRepository.findById("car-123")).thenReturn(car);
        Car result = carService.findById("car-123");
        assertNotNull(result);
        assertEquals("car-123", result.getCarId());
    }

    @Test
    void testUpdate() {
        carService.update("car-123", car);
        Mockito.verify(carRepository).update("car-123", car);
    }

    @Test
    void testDelete() {
        carService.deleteCarById("car-123");
        Mockito.verify(carRepository).delete("car-123");
    }
}