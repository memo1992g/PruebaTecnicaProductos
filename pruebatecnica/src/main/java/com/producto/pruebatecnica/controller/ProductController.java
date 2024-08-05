package com.producto.pruebatecnica.controller;

import com.producto.pruebatecnica.model.Product;
import com.producto.pruebatecnica.model.Statistics;
import com.producto.pruebatecnica.service.ProductService;
import com.producto.pruebatecnica.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

/**
 * Controlador para manejar las operaciones CRUD de productos y estadísticas en la aplicación.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Constructor del ProductController.
     */
    public ProductController() {}

    /**
     * Crea un nuevo producto.
     *
     * @param product El objeto Product a crear.
     * @return ResponseEntity con el producto creado y un código de estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = this.productService.saveProduct(product);
        this.statisticsService.updateStatistics(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Type", "application/json")
                .body(createdProduct);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id El ID del producto a actualizar.
     * @param productDetails Los nuevos detalles del producto.
     * @return ResponseEntity con el producto actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = this.productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Elimina un producto.
     *
     * @param id El ID del producto a eliminar.
     * @return ResponseEntity con un código de estado HTTP 204 (NO CONTENT).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene todos los productos.
     *
     * @return ResponseEntity con la lista de todos los productos.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAllProducts());
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id El ID del producto a obtener.
     * @return ResponseEntity con el producto solicitado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getProductById(id));
    }

    /**
     * Busca productos por nombre.
     *
     * @param name El nombre del producto a buscar.
     * @return ResponseEntity con la lista de productos que coinciden con el nombre proporcionado.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(this.productService.searchProductsByName(name));
    }

    /**
     * Obtiene todas las estadísticas.
     *
     * @return ResponseEntity con la lista de todas las estadísticas.
     */
    @GetMapping("/statistics")
    public ResponseEntity<List<Statistics>> getAllStatistics() {
        return ResponseEntity.ok(this.statisticsService.getAllStatistics());
    }
}
