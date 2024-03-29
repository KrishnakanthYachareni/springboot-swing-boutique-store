package com.boutique.store.presentation;

import com.boutique.store.entities.Product;
import com.boutique.store.entities.User;
import com.boutique.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Admin add item pop screen JFrame
 */
@Component
public class AddItemJFrame {

    @Autowired
    private AdminStoreJFrame adminStoreJFrame;

    @Autowired
    private ProductRepository productRepository;

    private JFrame jFrame;

    /**
     * Create the Add item pop screen
     */
    public JFrame addItemJFrame(AdminStoreJFrame thisobj, User user) {

        jFrame = new JFrame("Add Product");

        JTextField textField;
        JTextField textField1;
        JTextField textField2;
        JTextField textField3;
        JTextField textField4;

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(100, 100, 450, 404);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        jFrame.setContentPane(contentPane);

        JLabel lblAddBooks = new JLabel("Add Item");
        lblAddBooks.setForeground(Color.GRAY);
        lblAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 18));
        JLabel lblTitle = new JLabel("Title:");
        JLabel lblbarCodeNumber = new JLabel("BarCode:");
        JLabel lblPrice = new JLabel("Price:");
        JLabel lblDescription = new JLabel("Description:");
        JLabel lblQuantity = new JLabel("Quantity:");

        textField = new JTextField();
        textField.setColumns(10);

        textField1 = new JTextField();
        textField1.setColumns(10);

        textField2 = new JTextField();
        textField2.setColumns(10);

        textField3 = new JTextField();
        textField3.setColumns(10);

        textField4 = new JTextField();
        textField4.setColumns(10);

        JButton btnAddBooks = new JButton("Add Item");

        // After cliking on Add Item button, it saves the new order data inside database using spring data repository.
        btnAddBooks.addActionListener(e -> {
            String title = textField.getText();
            String barCode = textField1.getText();
            String price = textField2.getText();
            String description = textField3.getText();
            String squantity = textField4.getText();

            Product item = new Product();
            item.setTitle(title);
            item.setPrice(Double.parseDouble(price));
            item.setQuantity(Integer.parseInt(squantity));
            item.setBarcodeNumber(barCode);
            item.setCurrency("CAD");
            item.setColor("blue");
            item.setStatus("N/A");
            item.setDescription(description);
            item.setTax(0.13 * Double.parseDouble(price));

            productRepository.save(item);
            thisobj.adminJFrame().dispose();
            adminStoreJFrame.adminStoreJFrame(user).setVisible(true);
            jFrame.dispose();
        });

        JButton btnBack = new JButton("Cancel");
        btnBack.addActionListener(e -> {
            thisobj.adminJFrame().dispose();
            adminStoreJFrame.adminStoreJFrame(user).setVisible(true);
            jFrame.dispose();
        });
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(lblAddBooks))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(18)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(lblbarCodeNumber, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTitle)
                                                        .addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                                        .addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
                                                .addGap(47)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(125, Short.MAX_VALUE))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addGap(161)
                                .addComponent(btnAddBooks, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(162, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(359, Short.MAX_VALUE)
                                .addComponent(btnBack)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblAddBooks)
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblTitle)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblbarCodeNumber)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblPrice)
                                        .addComponent(textField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblDescription)
                                        .addComponent(textField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblQuantity)
                                        .addComponent(textField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(30)
                                .addComponent(btnAddBooks, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(btnBack)
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        return jFrame;
    }

}
