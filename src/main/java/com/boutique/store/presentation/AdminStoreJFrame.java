package com.boutique.store.presentation;

import com.boutique.store.entities.Product;
import com.boutique.store.entities.User;
import com.boutique.store.enums.Role;
import com.boutique.store.repository.ProductRepository;
import com.boutique.store.service.AdminService;
import com.boutique.store.util.ButtonRenderer;
import com.boutique.store.util.WordWrapCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Store Back End screen.
 * <p>
 * Note: On this Back end screen Edit, Delete buttons should be double clicked in order to work, bcz it handled by Button wrapper in side JTable
 */
@Component
public class AdminStoreJFrame {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddItemJFrame addItemJFrame;

    @Autowired
    private FrontStoreJFrame frontStoreJFrame;

    private JFrame jFrame;

    public JFrame adminStoreJFrame(User user) {
        jFrame = new JFrame("Store Backend – Admin");
        final JPanel mainPanel = new JPanel(); // main panel

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("Store Backend – Admin Personnel Login (" + user.getUsername() + ")\n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel("Today's Date: " + new Date().toString()));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));

        JButton addItem = new JButton("Add New");
        addItem.addActionListener(e -> {
            addItemJFrame.addItemJFrame(this, user).setVisible(true);
        });
        mainPanel.add(addItem);
        mainPanel.add(new JLabel(" \n"));

        // Admin Navigation to front store.
        JButton button = new JButton("Store");
        button.addActionListener(e -> {
            frontStoreJFrame.frontStoreJFrame(user).setVisible(true);
            jFrame.dispose();
        });

        if (Role.ADMIN == user.getRole()) {
            mainPanel.add(button);
        }

        mainPanel.add(new JLabel(" \n"));
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JScrollPane pane = getOrderTable(productRepository, user);
        mainPanel.add(pane);
        jFrame.add(mainPanel);

        jFrame.setBounds(450, 190, 1014, 597);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        return jFrame;
    }

    private JScrollPane getOrderTable(ProductRepository productRepository, User user) {
        List<Product> products = (List<Product>) productRepository.findAll();

        List<List<String>> decoratedTableRows = new ArrayList<>();
        for (Product item : products) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add("Title: " + item.getTitle() + "\n Colour:" + item.getColor());
            list.add("Barcode number: " + item.getBarcodeNumber() + "\n Quantity: " + item.getQuantity() + "\nPrice: $" + item.getPrice());
            list.add("Description: " + item.getDescription());
            list.add("Reason unavailable: " + item.getStatus());
            list.add("Delete");
            list.add("Edit");
            decoratedTableRows.add(list);
        }

        Object[][] data = decoratedTableRows.stream()
                .map(l -> l.toArray(new String[0]))
                .toArray(Object[][]::new);

        //COLUMN HEADERS
        String[] columnHeaders = new String[]{"Id", "Title", "Order", "Description", "Status", "", ""};
        //CREATE OUR TABLE AND SET HEADER
        JTable table = new JTable(data, columnHeaders);

        //SET CUSTOM RENDERER TO TEAMS COLUMN
        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        table.getColumnModel().getColumn(5).setCellEditor(new AdminService(new JTextField(), productRepository, user, this));

        table.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(new WordWrapCellRenderer());

        return new JScrollPane(table);
    }

    public JFrame adminJFrame() {
        return this.jFrame;
    }
}



