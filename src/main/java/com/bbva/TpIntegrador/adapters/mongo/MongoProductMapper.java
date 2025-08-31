package com.bbva.TpIntegrador.adapters.mongo;

import com.bbva.TpIntegrador.domain.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre la entidad de dominio Product y el documento MongoDB ProductDocument.
 * Spring Data MongoDB maneja automáticamente la conversión de tipos básicos.
 */
@Component
public class MongoProductMapper {
    
    /**
     * Convierte una entidad Product a un documento MongoDB
     */
    public ProductDocument toDocument(Product product) {
        return new ProductDocument(
            product.getId(),
            product.getNombre(),
            product.getPrecio(),
            product.getStock()
        );
    }
    
    /**
     * Convierte un documento MongoDB a una entidad Product
     */
    public Product toDomain(ProductDocument document) {
        return new Product(
            document.getId(),
            document.getNombre(),
            document.getPrecio(),
            document.getStock()
        );
    }
}
