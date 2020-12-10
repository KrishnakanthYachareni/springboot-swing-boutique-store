package com.boutique.store.repository;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Data access layer to perform CRDU operations of {@link com.boutique.store.entities.Order}
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
