package com.boutique.store.sampledata;

import com.boutique.store.entities.OrderItem;
import com.boutique.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * This Runner is responsible to load and persist the initial sample data into H2 in memory data base.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private OrderRepository orderRepository;

    @Autowired
    public DataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(ApplicationArguments args) {

        //Sample Order Item1
        OrderItem order = new OrderItem();
        order.setCurrency("CAD");
        order.setBarcodeNumber("12346812");
        order.setPrice(120);
        order.setQuantity(10);
        order.setColor("black");
        order.setTitle("Cross body purse");
        order.setDescription("all black leather with gold buttons and gold zipper.");
        order.setEditable(true);
        order.setStatus("N/A");

        orderRepository.save(order);

        //Sample Order Item2
        OrderItem order2 = new OrderItem();
        order2.setCurrency("CAD");
        order2.setBarcodeNumber("4525235");
        order2.setPrice(65);
        order2.setQuantity(1);
        order2.setColor("blue");
        order2.setTitle("Jeans straight x100");
        order2.setDescription("faded blue on left side with a little distress on right side below knee area.");
        order2.setEditable(true);
        order2.setStatus("backordered. Available on August 30, 2021.");

        orderRepository.save(order2);
    }
}
