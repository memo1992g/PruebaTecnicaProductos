
        package com.producto.pruebatecnica.service;

import com.producto.pruebatecnica.model.Product;
import com.producto.pruebatecnica.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para el servicio ProductService.
 */
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Configura el entorno de pruebas antes de cada método de prueba.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Prueba para verificar que se pueda guardar un producto.
     */
    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals("Test Product", savedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    /**
     * Prueba para verificar que se pueda actualizar un producto.
     */
    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product productDetails = new Product();
        productDetails.setName("Updated Product");

        Product updatedProduct = productService.updateProduct(1L, productDetails);

        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    /**
     * Prueba para verificar que se lance una excepción cuando se intenta eliminar un producto que no existe.
     */
    @Test
    void testDeleteProduct() {
        // Simula que el producto no existe en el repositorio
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Captura la excepción lanzada
        Exception exception = null;
        try {
            productService.deleteProduct(1L);
        } catch (Exception e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(anyLong());
    }

    /**
     * Prueba para verificar que se puedan obtener todos los productos.
     */
    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setName("Product 2");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    /**
     * Prueba para verificar que se pueda obtener un producto por su ID.
     */
    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        verify(productRepository, times(1)).findById(anyLong());
    }

    /**
     * Prueba para verificar que se puedan buscar productos por nombre.
     */
    @Test
    void testSearchProductsByName() {
        Product product1 = new Product();
        product1.setName("Product 1");
        Product product2 = new Product();
        product2.setName("Product 2");

        when(productRepository.findByNameContaining(anyString())).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.searchProductsByName("Product");

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findByNameContaining(anyString());
    }
}
