package com.boutique.store.forms;

import com.boutique.store.entities.User;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is Application User, Admin login page. User would be able to navigate to respective store screens based on the user authentication ROLE.
 * 1. User with role having "ADMIN" would get navigated to Store back end screen.
 * 2.User with role having "USER" would get navigated to Store Front screen.
 */
@Controller
public class UserLogin extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JButton btnNewButton;
    private final JLabel label;
    private final JPanel contentPane;

    @Autowired
    private UserRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Constructor used to initialize the JFrame login screen elements and it performs the on click action operations which are present on the screen.
     */
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {


            /**
             * When User click on Login button from login screen.
             */
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText();
                String password = passwordField.getText();

                // Fetch the user from database.
                User user = repository.findByUsername(userName);

                //Validate the username and Password
                if (null == user) {
                    JOptionPane.showMessageDialog(null, "Given user is not Exist");
                }
                if (password.equals(user.getPassword())) {
                    new FrontStore(orderRepository, user).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid User or Password");
                }
            }
        });

        contentPane.add(btnNewButton);

        label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}
