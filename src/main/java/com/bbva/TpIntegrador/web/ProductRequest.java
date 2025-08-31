package com.bbva.TpIntegrador.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para las peticiones de creación de tareas.
 * Incluye validaciones básicas.
 */
@Data
public class ProductRequest {
    
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotBlank(message = "El precio del producto es obligatorio")
    private double precio;

    @NotBlank(message = "El stock del procuto es obligatorio")
    private int stock;

}
