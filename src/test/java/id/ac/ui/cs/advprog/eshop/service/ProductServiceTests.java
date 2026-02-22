package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123");
        product.setProductName("Laptop");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertNotNull(result);
        assertEquals(product, result);

        verify(productRepository).create(product);
    }

    @Test
    void testFindAllProducts() {

        List<Product> products = Arrays.asList(product);
        Iterator<Product> iterator = products.iterator();

        when(productRepository.findAll())
                .thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));

        verify(productRepository).findAll();
    }

    @Test
    void testFindProductById() {

        when(productRepository.findById("123"))
                .thenReturn(product);

        Product result = productService.findById("123");

        assertNotNull(result);
        assertEquals("123", result.getProductId());

        verify(productRepository).findById("123");
    }

    @Test
    void testUpdateProduct() {

        when(productRepository.update(product))
                .thenReturn(product);

        Product result = productService.update(product);

        assertNotNull(result);
        assertEquals(product, result);

        verify(productRepository).update(product);
    }

    @Test
    void testDeleteProduct() {

        doNothing().when(productRepository).delete("123");

        productService.delete("123");

        verify(productRepository).delete("123");
    }
}