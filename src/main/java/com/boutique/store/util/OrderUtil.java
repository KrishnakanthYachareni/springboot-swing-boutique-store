package com.boutique.store.util;

import com.boutique.store.entities.OrderItem;
import com.boutique.store.repository.OrderRepository;

import java.util.Optional;

public class OrderUtil {

    public static void addItem(long id, OrderRepository orderRepository) {
        Optional<OrderItem> item = orderRepository.findById(id);
        if (item.isPresent()) {
            double unitPrice = item.get().getPrice() / item.get().getQuantity();
            item.get().setQuantity(item.get().getQuantity() + 1);
            item.get().setPrice(item.get().getPrice() + unitPrice);
            orderRepository.save(item.get());
        }
    }

    public static void deleteItem(long id, OrderRepository orderRepository) {
        Optional<OrderItem> item = orderRepository.findById(id);
        item.ifPresent(orderRepository::delete);
    }
}
