package com.bbva.TpIntegrador.controller;

import com.bbva.TpIntegrador.domain.Product;
import com.bbva.TpIntegrador.service.ProductService;
import com.bbva.TpIntegrador.web.ProductRequest;
import com.bbva.TpIntegrador.web.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Controlador REST para operaciones con tareas.
 * Demuestra endpoints para guardar y recuperar tareas en MongoDB y Firebase Firestore.
 */
@RestController
@Validated
public class ProductController {
    
    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /**
     * Crea una nueva tarea y la guarda en MongoDB Atlas.
     * Spring Data MongoDB maneja automáticamente la generación de IDs.
     */
    @PostMapping("/product")
    public ResponseEntity<ProductResponse> createProductInMongo(@RequestBody ProductRequest request) {
        // Convertir DTO a entidad de dominio
        Product product = new Product(request.getNombre(), request.getPrecio(), request.getStock());
        
        // Guardar en MongoDB
        Product savedProduct = productService.saveToMongo(product);
        
        // Convertir a DTO de respuesta
        ProductResponse response = new ProductResponse(savedProduct);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Busca todas las tareas en MongoDB Atlas.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsMongo() {
        List<Product> product = productService.finAllProducts().join();

        if (product.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    /**
     * Busca una tarea por ID en MongoDB Atlas.
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductFromMongo(@PathVariable String id) {
        Product product = productService.findByIdFromMongo(id);

        if (product != null) {
            ProductResponse response = new ProductResponse(product);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> updateInMongo(@PathVariable Product producto) {
        productService.updateInMongo(producto);
        return ResponseEntity.ok().build();
    }


}