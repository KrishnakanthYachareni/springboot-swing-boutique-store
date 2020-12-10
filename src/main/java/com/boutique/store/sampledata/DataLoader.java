package com.boutique.store.sampledata;

import com.boutique.store.entities.Product;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * This Runner is responsible to load and persist the initial sample data into H2 in memory data base.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public DataLoader(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        //Sample Order Item1
        Product product = new Product();
        product.setCurrency("CAD");
        product.setBarcodeNumber("12346812");
        product.setPrice(120);
        product.setTax(0.13 * 120);
        product.setQuantity(10);
        product.setColor("black");
        product.setTitle("Cross body purse");
        product.setDescription("all black leather with gold buttons and gold zipper.");
        product.setEditable(true);
        product.setStatus("N/A");

        productRepository.save(product);

        //Sample Order Item2
        Product product1 = new Product();
        product1.setCurrency("CAD");
        product1.setBarcodeNumber("4525235");
        product1.setPrice(65);
        product1.setQuantity(1);
        product1.setColor("blue");
        product1.setTitle("Jeans straight x100");
        product1.setDescription("faded blue on left side with a little distress on right side below knee area.");
        product1.setEditable(true);
        product1.setStatus("backordered. Available on August 30, 2021.");

        productRepository.save(product1);
/*
        Order order = new Order();
        order.setUserId(2L);
        order.setStatus("Delivered");
        order.setProduct(product);

        orderRepository.save(order);*/
    }
}
