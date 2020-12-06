package com.boutique.store.forms;

import com.boutique.store.enums.Role;
import com.boutique.store.entities.OrderItem;
import com.boutique.store.entities.User;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.util.AdminButtonEditor;
import com.boutique.store.util.ButtonRenderer;
import com.boutique.store.util.WordWrapCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminStore extends JFrame {
    private final JPanel mainPanel;

    public AdminStore(OrderRepository orderRepository, User user) {
        setTitle("Store Backend – Admin");
        mainPanel = new JPanel(); // main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(new JLabel("Store Backend – Admin Personnel Login (" + user.getUsername() + ")\n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel("Today's Date: " + new Date().toString()));

        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        JButton addItem = new JButton("Add New");
        addItem.addActionListener(e -> {
            new AddItem(orderRepository, this, user).setVisible(true);
        });
        mainPanel.add(addItem);
        mainPanel.add(new JLabel(" \n"));

        // Admin Navigation to front store.
        JButton button = new JButton("Store");
        button.addActionListener(e -> {
            new FrontStore(orderRepository, user).setVisible(true);
            dispose();
        });

        if (Role.ADMIN == user.getRole()) {
            mainPanel.add(button);
        }

        mainPanel.add(new JLabel(" \n"));
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JScrollPane pane = getOrderTable(orderRepository, user);
        mainPanel.add(pane);
        add(mainPanel);

        setBounds(450, 190, 1014, 597);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JScrollPane getOrderTable(OrderRepository orderRepository, User user) {
        List<OrderItem> orderItems = (List<OrderItem>) orderRepository.findAll();

        List<List<String>> decoratedTableRows = new ArrayList<>();
        for (OrderItem item : orderItems) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add("Title: " + item.getTitle() + "\n Colour:" + item.getColor());
            list.add("Barcode number: " + item.getBarcodeNumber() + "\n Quantity: " + item.getQuantity() + "\nPrice: $" + item.getPrice());
            list.add("Description: " + item.getDescription());
            list.add("Reason unavailable: " + item.getStatus());
            list.add("Delete");
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
        table.getColumnModel().getColumn(5).setCellEditor(new AdminButtonEditor(new JTextField(), orderRepository, user, this));


        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());

        return new JScrollPane(table);
    }
}



