package com.boutique.store.util;

import com.boutique.store.entities.User;
import com.boutique.store.forms.FrontStore;
import com.boutique.store.repository.OrderRepository;

import javax.swing.*;
import java.awt.*;

/**
 * It handles the store front screen sales order items logic to performs CRUD.
 */
public class SalesFrontHandler extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private final OrderRepository orderRepository;
    private final User user;
    private final FrontStore thisObj;

    public SalesFrontHandler(JTextField txt, OrderRepository orderRepository, User user, FrontStore thisObj) {
        super(txt);
        this.orderRepository = orderRepository;
        this.user = user;
        this.thisObj = thisObj;

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

        // Handles the order item addition to cart.
        long id = Long.parseLong(String.valueOf(table.getModel().getValueAt(row, 0)));

        OrderUtil.updateItem(id, orderRepository);
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new FrontStore(orderRepository, user).setVisible(true);
            thisObj.dispose();
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
