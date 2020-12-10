package com.boutique.store.forms;

import com.boutique.store.entities.OrderItem;
import com.boutique.store.entities.User;
import com.boutique.store.enums.Role;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.util.ButtonRenderer;
import com.boutique.store.util.SalesFrontHandler;
import com.boutique.store.util.WordWrapCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrontStore extends JFrame {

    /**
     * Construction of JFrame Front screen elements and it performs the on click actions from the screen.
     */
    public FrontStore(OrderRepository orderRepository, User user) {
        setTitle("Store Front – Sales Personnel Login");
        JPanel mainPanel = new JPanel(); // main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("Store Front – Sales Personnel Login (" + user.getUsername() + ")\n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel("Today's Date: " + new Date().toString()));
        mainPanel.setBackground(Color.white);

        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));

        // Admin Navigation from front store.
        JButton button = new JButton("Admin");
        button.addActionListener(e -> {
            new AdminStore(orderRepository, user).setVisible(true);
            dispose();
        });

        // Check if the User role is Admin, then add Admin screen button.
        if (Role.ADMIN == user.getRole()) {
            mainPanel.add(button);
        }

        mainPanel.add(new JLabel(" \n"));
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Front Screen Data in a table view
        JScrollPane pane = getOrderTable(orderRepository, user);
        mainPanel.add(pane);
        add(mainPanel);

        setBounds(450, 190, 1014, 597);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * This method prepares the Order data in a table format by fetching the order data from database.
     */
    private JScrollPane getOrderTable(OrderRepository orderRepository, User user) {
        java.util.List<OrderItem> orderItems = (java.util.List<OrderItem>) orderRepository.findAll();

        java.util.List<java.util.List<String>> decoratedTableRows = new ArrayList<>();
        for (OrderItem item : orderItems) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add("Title: " + item.getTitle() + "\n Colour:" + item.getColor());
            list.add("Barcode number: " + item.getBarcodeNumber() + "\n Quantity: " + item.getQuantity() + "\nPrice: $" + item.getPrice());
            list.add("Description: " + item.getDescription());
            list.add("Reason unavailable: " + item.getStatus());
            list.add(item.isEditable() ? "Add" : "No Edit");
            decoratedTableRows.add(list);
        }

        Object[][] data = decoratedTableRows.stream()
                .map(l -> l.toArray(new String[0]))
                .toArray(Object[][]::new);

        //COLUMN HEADERS
        String[] columnHeaders = new String[]{"Id", "Title", "Order", "Description", "Status", ""};
        //CREATE OUR TABLE AND SET HEADER
        JTable table = new JTable(data, columnHeaders);

        //SET CUSTOM RENDERER TO TEAMS COLUMN
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        table.getColumnModel().getColumn(5).setCellEditor(new SalesFrontHandler(new JTextField(), orderRepository, user, this));


        // To Wrap the text in table cells.
        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());

        return new JScrollPane(table);
    }
}



