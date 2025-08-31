package com.bbva.TpIntegrador.adapters.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio MongoDB para ProductDocument.
 * Spring Data MongoDB proporciona métodos CRUD automáticamente.
 * Extendemos MongoRepository para obtener funcionalidad básica.
 */
@Repository
public interface MongoProductRepository extends MongoRepository<ProductDocument, String> {
    // Spring Data MongoDB genera automáticamente métodos como:
    // save(), findById(), findAll(), deleteById(), etc.
}
