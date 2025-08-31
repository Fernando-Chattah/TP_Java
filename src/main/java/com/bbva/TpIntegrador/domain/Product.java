package com.bbva.TpIntegrador.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de dominio Product que representa una tarea.
 * Esta clase es independiente de la tecnología de persistencia.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    private String id;
    private String nombre;
    private double precio;
    private int stock;

    /**
     * Constructor para crear una tarea sin ID (se generará automáticamente)
     */
    public Product(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}
