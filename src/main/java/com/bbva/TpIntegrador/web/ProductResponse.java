package com.bbva.TpIntegrador.web;

import com.bbva.TpIntegrador.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para las respuestas de tareas.
 * Incluye el ID generado y los datos de la tarea.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    
    private String nombre;
    private double precio;
    private int stock;

    /**
     * Constructor para crear respuesta desde una entidad Product
     */
    public ProductResponse(Product product) {
        this.nombre = product.getNombre();
        this.precio = product.getPrecio();
        this.stock = product.getStock();
    }
}
