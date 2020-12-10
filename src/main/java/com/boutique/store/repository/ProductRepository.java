package com.boutique.store.repository;

import com.boutique.store.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Data access layer to perform CRDU operations of {@link Product}
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
