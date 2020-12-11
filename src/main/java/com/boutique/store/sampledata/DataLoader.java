package com.boutique.store.sampledata;

import com.boutique.store.entities.OrderHistory;
import com.boutique.store.entities.Product;
import com.boutique.store.repository.OrderHistoryRepository;
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
    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public DataLoader(ProductRepository productRepository, OrderHistoryRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderHistoryRepository = orderRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        //Sample Order Item1
        Product product = new Product();
        product.setCurrency("CAD");
        product.setBarcodeNumber("12346812");
        product.setPrice(150);
        product.setTax(19.5);
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
        product1.setTax(8.45);
        product1.setQuantity(1);
        product1.setColor("blue");
        product1.setTitle("Jeans straight x100");
        product1.setDescription("faded blue on left side with a little distress on right side below knee area.");
        product1.setEditable(true);
        product1.setStatus("backordered. Available on August 30, 2021.");

        productRepository.save(product1);


        insertSampleOrderHistory();
    }

    private void insertSampleOrderHistory() {
        OrderHistory orderHistory = new OrderHistory();
        // User
        Product oldItem = new Product();
        prepareOrderHistory(oldItem);
        productRepository.save(oldItem);
        orderHistory.setUserId(2L);
        orderHistory.setProduct(oldItem);
        orderHistory.setStatus("Delivered");
        orderHistoryRepository.save(orderHistory);
    }

    private void prepareOrderHistory(Product oldItem) {
        oldItem.setCurrency("CAD");
        oldItem.setBarcodeNumber("45643534");
        oldItem.setPrice(120);
        oldItem.setTax(15.3);
        oldItem.setQuantity(1);
        oldItem.setColor("Green");
        oldItem.setTitle("Cracking the coding Interview");
        oldItem.setDescription("Algorithm book by Gayle Laakmann");
        oldItem.setEditable(true);
        oldItem.setStatus("Purchased");
    }
}
