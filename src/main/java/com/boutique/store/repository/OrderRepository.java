package com.boutique.store.repository;

import com.boutique.store.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Data access layer to perform CRDU operations of {@link OrderItem}
 */
@Repository
public interface OrderRepository extends CrudRepository<OrderItem, Long> {
}
