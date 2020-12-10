package com.boutique.store.presentation;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.Product;
import com.boutique.store.entities.User;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.util.OrderUtil;
import com.boutique.store.util.WordWrapCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This Jframe is final user cart screen, where sale user can add his/he items into cart and make their items for final purchase.
 */
public class UserCartJFrame extends JFrame {

    public UserCartJFrame(OrderRepository orderRepository, User user) {
        setTitle("User Cart Item(s)");
        JPanel mainPanel = new JPanel(); // main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(new JLabel("Order history:"));
        mainPanel.add(new JLabel(" \n"));

        mainPanel.add(new JLabel("Current Order(s): "));
        mainPanel.add(new JLabel(" \n"));

        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JScrollPane pane = getCurrentOrders(orderRepository, user);
        mainPanel.add(pane);
        add(mainPanel);

        setBounds(450, 190, 1014, 597);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JScrollPane getCurrentOrders(OrderRepository orderRepository, User user) {
        List<Order> orders = orderRepository.findAllByUserId(user.getId());

        List<List<String>> orderItems = new ArrayList<>();
        /*for (Product item : OrderUtil.extractProduct(orders)) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add("Item: " + item.getTitle() + " \n Barcode number: " + item.getBarcodeNumber());
            list.add("Price: " + item.getPrice() + " \n Tax: " + item.getTax() + "Total: " + OrderUtil.totalAmount(item));
            orderItems.add(list);
        }*/

        List<String> list = new ArrayList<>();
        list.add(String.valueOf(1));
        list.add("Item: " + "Cross body bag x 1" + " \n Barcode number: " + "123466812");
        list.add("Price: " + "150" + " \n Tax: " + "19.40" + "Total: " + "160");

        orderItems.add(list);

        List<String> grandTotalRow = new ArrayList<>();
        grandTotalRow.add("");
        grandTotalRow.add("");
//        grandTotalRow.add(OrderUtil.grandTotalAmount(orders));
        grandTotalRow.add("200");
        orderItems.add(grandTotalRow);

        Object[][] data = orderItems.stream()
                .map(l -> l.toArray(new String[0]))
                .toArray(Object[][]::new);

        //COLUMN HEADERS
        String[] columnHeaders = new String[]{"Id", "Item Details", "Amount"};


        //CREATE OUR TABLE AND SET HEADER
        JTable table = new JTable(data, columnHeaders);

        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());

        return new JScrollPane(table);
    }
}



