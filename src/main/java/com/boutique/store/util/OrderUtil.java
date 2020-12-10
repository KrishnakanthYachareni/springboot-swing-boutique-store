package com.boutique.store.util;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.Product;
import com.boutique.store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderUtil {

    /**
     * It updates the order item in database.
     */
    public static void updateItem(long id, ProductRepository productRepository) {
        Optional<Product> item = productRepository.findById(id);
        if (item.isPresent()) {
            double unitPrice = item.get().getPrice() / item.get().getQuantity();
            item.get().setQuantity(item.get().getQuantity() + 1);
            item.get().setPrice(item.get().getPrice() + unitPrice);
            productRepository.save(item.get());
        }
    }

    /**
     * It deletes the item in database.
     */
    public static void deleteItem(long id, ProductRepository productRepository) {
        Optional<Product> item = productRepository.findById(id);
        item.ifPresent(productRepository::delete);
    }


    public static List<Product> extractProduct(List<Order> orders) {
        List<Product> products = new ArrayList<>();
        for (Order order : orders) {
            products.add(order.getProduct());
        }
        return products;
    }

    public static double totalAmount(Product product) {
        return product.getPrice() + product.getTax();
    }


    /**
     * Calculates the total order amount of the current user.
     *
     * @return
     */
    public static String grandTotalAmount(List<Order> orders) {
        double grandAmount = 0;
        for (Order order : orders) {
            Product product = order.getProduct();
            double sum = product.getPrice() + product.getTax();
            grandAmount += sum;
        }
        return String.valueOf(grandAmount);
    }
}
