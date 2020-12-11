package com.boutique.store.repository;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.OrderHistory;
import com.boutique.store.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Data access layer to perform CRDU operations of {@link OrderHistory}
 */
@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    List<OrderHistory> findAllByUserId(Long userId);

    Order findByProduct(Product product);
}
