package com.boutique.store.util;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.Product;
import com.boutique.store.entities.User;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderUtil {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ProductRepository productRepository;

    /**
     * It remove the current use order items from the cart and update the Product Items quantity back to to original.
     **/
    public void removeOrder(User user, boolean isCancel) {
        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        for (Order order : orders) {

            Optional<Product> product = productRepository.findById(order.getProduct().getId());
            if (product.isPresent() && isCancel) {
                int quantity = product.get().getQuantity();
                product.get().setQuantity(quantity + 1);
                productRepository.save(product.get());
            }

            orderRepository.delete(order);
        }


    }

    /**
     * It updates the Product quantity and add the Product item into current use cart in database.
     */
    public static void addItemToCart(long id, User user, ProductRepository productRepository, OrderRepository orderRepository) {
        Optional<Product> item = productRepository.findById(id);
        if (item.isPresent()) {

            Order order = new Order();
            order.setUserId(user.getId());
            order.setProduct(item.get());
            order.setStatus("Added To Cart");

            orderRepository.save(order);

            int itemQuantity = item.get().getQuantity();
            if (itemQuantity <= 0) {
                itemQuantity = 0;
            } else {
                itemQuantity--;
            }
            item.get().setQuantity(itemQuantity);
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
