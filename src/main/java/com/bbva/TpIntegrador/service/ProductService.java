package com.bbva.TpIntegrador.service;

import com.bbva.TpIntegrador.adapters.mongo.MongoProductMapper;
import com.bbva.TpIntegrador.adapters.mongo.MongoProductRepository;
import com.bbva.TpIntegrador.adapters.mongo.ProductDocument;
import com.bbva.TpIntegrador.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Component
public class ProductService {
    
    private final MongoProductRepository mongoRepository;
    private final MongoProductMapper mongoMapper;
    private final Executor executor;


    @Autowired
    public ProductService(MongoProductRepository mongoRepository,
                          MongoProductMapper mongoMapper,
                          Executor executor)
    {
        this.mongoRepository = mongoRepository;
        this.mongoMapper = mongoMapper;
        this.executor = executor;
    }
    
    /**
     * Guarda una tarea en MongoDB Atlas.
     * Spring Data MongoDB maneja automáticamente la generación de IDs.
     */
    public Product saveToMongo(Product product) {
        // Convertir entidad de dominio a documento MongoDB
        ProductDocument document = mongoMapper.toDocument(product);
        
        // Guardar en MongoDB (Spring Data genera automáticamente el ID si es null)
        ProductDocument savedDocument = mongoRepository.save(document);
        
        // Convertir de vuelta a entidad de dominio
        return mongoMapper.toDomain(savedDocument);
    }
    
    /**
     * Busca una tarea por ID en MongoDB.
     */

    public Product findByIdFromMongo(String id) {
        return mongoRepository.findById(id)
                            .map(mongoMapper::toDomain)
                            .orElse(null);
    }

    /*
     /**
     * Actualiza una tarea en MongoDB.
     */
    public Product updateInMongo(Product product) {
        ProductDocument document = mongoMapper.toDocument(product);
        ProductDocument updatedDocument = mongoRepository.save(document);
        return mongoMapper.toDomain(updatedDocument);
    }


    @Async("taskExecutor")
    public CompletableFuture<List<Product>> finAllProducts() {
        return CompletableFuture.supplyAsync(()->
               mongoRepository.findAll().stream()
                       .map(mongoMapper::toDomain)
                       .toList()
                , executor
        );
/*        return mongoRepository.findAll().stream().map(mongoMapper::toDomain).toList();*/
    }

    public void deleteById(String id) {
        mongoRepository.deleteById(id);
    }

}
