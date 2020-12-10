package com.boutique.store.util;

import com.boutique.store.entities.OrderItem;
import com.boutique.store.repository.OrderRepository;

import java.util.Optional;

public class OrderUtil {

    /**
     * It updates the order item in database.
     */
    public static void updateItem(long id, OrderRepository orderRepository) {
        Optional<OrderItem> item = orderRepository.findById(id);
        if (item.isPresent()) {
            double unitPrice = item.get().getPrice() / item.get().getQuantity();
            item.get().setQuantity(item.get().getQuantity() + 1);
            item.get().setPrice(item.get().getPrice() + unitPrice);
            orderRepository.save(item.get());
        }
    }

    /**
     * It deletes the item in database.
     */
    public static void deleteItem(long id, OrderRepository orderRepository) {
        Optional<OrderItem> item = orderRepository.findById(id);
        item.ifPresent(orderRepository::delete);
    }
}
