package com.boutique.store.presentation;

import com.boutique.store.entities.Order;
import com.boutique.store.entities.OrderHistory;
import com.boutique.store.entities.Product;
import com.boutique.store.entities.User;
import com.boutique.store.repository.OrderHistoryRepository;
import com.boutique.store.repository.OrderRepository;
import com.boutique.store.util.ButtonRenderer;
import com.boutique.store.service.OrderHistoryService;
import com.boutique.store.service.OrderUtil;
import com.boutique.store.util.WordWrapCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This JFrame is final user cart screen, where sale user can add his/he items into cart and make their items for final purchase.
 */
@Component
public class UserCartJFrame {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private FrontStoreJFrame frontStoreJFrame;

    @Autowired
    private OrderUtil orderUtil;

    private JFrame jFrame;

    @Transactional
    public JFrame userCartJFrame(User user) {
        jFrame = new JFrame("User Cart Item(s)");
        JPanel mainPanel = new JPanel(); // main panel

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("Order history:"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));

        //Current user order history data.
        JTable table = getOrderHistoryTable(user);
        mainPanel.add(table);
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));

        mainPanel.add(new JLabel("Current Order(s): "));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.setBackground(Color.white);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JScrollPane pane = getCurrentOrders(user);
        mainPanel.add(pane);
        jFrame.add(mainPanel);

        // Purchase the order it adds the purchased items to order history.
        JButton purchaseButton = new JButton("Complete Purchase");
        purchaseButton.addActionListener(e -> {
            List<Order> orders = orderRepository.findAllByUserId(user.getId());
            for (Order order : orders) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setProduct(order.getProduct());
                orderHistory.setUserId(user.getId());
                orderHistory.setStatus("Delivered");
                orderHistoryRepository.save(orderHistory);
                orderUtil.removeOrder(user, false);
            }
            JOptionPane.showMessageDialog(jFrame, "Purchased");
            this.userCartJFrame().dispose();
            this.userCartJFrame(user).setVisible(true);
        });
        mainPanel.add(purchaseButton);

        // Remove the order completely.
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            orderUtil.removeOrder(user, true);
            this.userCartJFrame().dispose();
            this.userCartJFrame(user).setVisible(true);
        });
        mainPanel.add(removeButton);

        //Navigation to front store
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            this.userCartJFrame().dispose();
            this.frontStoreJFrame.frontStoreJFrame(user).setVisible(true);
        });
        mainPanel.add(cancelButton);
        addSomeSpace(mainPanel);

        jFrame.setBounds(450, 190, 1014, 597);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    private JTable getOrderHistoryTable(User user) {
        List<OrderHistory> historyOrders = orderHistoryRepository.findAllByUserId(user.getId());
        List<List<String>> history = new ArrayList<>();
        for (OrderHistory order : historyOrders) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(order.getId()));
            list.add(order.getCreatedAt().toString());
            list.add("[view]");
            history.add(list);
        }
        Object[][] data = history.stream()
                .map(l -> l.toArray(new String[0]))
                .toArray(Object[][]::new);

        //COLUMN HEADERS
        String[] columnHeaders = new String[]{"OrderId", "Date", ""};
        //CREATE OUR TABLE AND SET HEADER
        JTable table = new JTable(data, columnHeaders);
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        table.getColumnModel().getColumn(2).setCellEditor(new OrderHistoryService(new JTextField(), orderHistoryRepository, user, this));

        table.setShowGrid(false);
        return table;
    }

    private JScrollPane getCurrentOrders(User user) {
        List<Order> orders = orderRepository.findAllByUserId(user.getId());

        List<List<String>> orderItems = new ArrayList<>();
        for (Product item : OrderUtil.extractProduct(orders)) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(item.getId()));
            list.add("Item: " + item.getTitle() + " \nBarcode number: " + item.getBarcodeNumber());
            list.add("Price: $" + item.getPrice() + " \nTax: $" + item.getTax() + "\nTotal: $" + OrderUtil.totalAmount(item));
            orderItems.add(list);
        }

        List<String> grandTotalRow = new ArrayList<>();
        grandTotalRow.add("");
        grandTotalRow.add("");
        grandTotalRow.add("Grand Total: $" + OrderUtil.grandTotalAmount(orders));
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

    public JFrame userCartJFrame() {
        return this.jFrame;
    }

    public Container rootPanel() {
        return jFrame.getContentPane();
    }

    private void addSomeSpace(JPanel mainPanel) {
        addNewLine(mainPanel);
    }

    private void addNewLine(JPanel mainPanel) {
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
        mainPanel.add(new JLabel(" \n"));
    }

}

