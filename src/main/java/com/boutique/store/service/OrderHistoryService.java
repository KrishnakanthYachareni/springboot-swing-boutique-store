package com.boutique.store.service;

import com.boutique.store.entities.OrderHistory;
import com.boutique.store.entities.User;
import com.boutique.store.presentation.UserCartJFrame;
import com.boutique.store.repository.OrderHistoryRepository;

import javax.swing.*;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.Optional;

/**
 * Handler for Order history screen view button.
 */
public class OrderHistoryService extends DefaultCellEditor {

    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private final OrderHistoryRepository orderHistoryRepository;
    private final User user;
    private UserCartJFrame userCartJFrame;
    private long orderId;

    public OrderHistoryService(JTextField jTextField, OrderHistoryRepository orderHistoryRepository, User user, UserCartJFrame userCartJFrame) {
        super(jTextField);
        this.user = user;
        this.orderHistoryRepository = orderHistoryRepository;
        this.userCartJFrame = userCartJFrame;
        btn = new JButton();
        btn.setOpaque(true);
        //WHEN BUTTON IS CLICKED
        btn.addActionListener(e -> fireEditingStopped());
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        clicked = true;

        this.orderId = Long.parseLong(String.valueOf(table.getModel().getValueAt(0, 0)));
        return btn;
    }

    @Override
    @Transactional
    public Object getCellEditorValue() {
        if (clicked) {
            Optional<OrderHistory> order = orderHistoryRepository.findById(orderId);
            Object[][] data = null;

            if (order.isPresent()) {
                OrderHistory o = order.get();
                data = new Object[][]{{"OrderId:", o.getId()},
                        {"OrderDate:", o.getCreatedAt().toString()},
                        {"Title:", o.getProduct().getTitle()},
                        {"BarCode:", o.getProduct().getBarcodeNumber()},
                        {"Price: $", o.getProduct().getPrice()},
                        {"Tax: $", o.getProduct().getTax()},
                        {"Total: $", o.getProduct().getPrice() + o.getProduct().getTax()},
                        {"Status:", o.getStatus()}};
            }

            Object[] columnNames = {"", ""};
            final JTable table = new JTable(data, columnNames);
            table.setFont(new Font("Tahoma", Font.BOLD, 14));
            table.setShowGrid(false);

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout());
            JScrollPane sp = new JScrollPane(table);
            jPanel.add(sp);
            JDialog jdialog = new JDialog();
            jdialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jdialog.setContentPane(jPanel);
            jdialog.pack();
            jdialog.setVisible(true);
        }
        clicked = false;
        return lbl;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
