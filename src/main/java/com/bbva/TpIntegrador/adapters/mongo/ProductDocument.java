package com.bbva.TpIntegrador.adapters.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento MongoDB para la entidad Product.
 * Spring Data MongoDB usa anotaciones específicas para mapear a la colección.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producto")
public class ProductDocument {
    
    @Id
    private String id;
    private String nombre;
    private double precio;
    private int stock;
}
