package com.boutique.store.util;

import com.boutique.store.entities.User;
import com.boutique.store.forms.AdminStore;
import com.boutique.store.repository.OrderRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It handles the store back end screen sales order items logic to performs CRUD.
 */
public class AdminHandler extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private final OrderRepository orderRepository;
    private final User user;
    private final AdminStore thisObj;

    public AdminHandler(JTextField txt, OrderRepository orderRepository, User user, AdminStore thisObj) {
        super(txt);
        this.orderRepository = orderRepository;
        this.user = user;
        this.thisObj = thisObj;

        btn = new JButton();
        btn.setOpaque(true);

        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        clicked = true;

        long id = Long.parseLong(String.valueOf(table.getModel().getValueAt(0, 0)));

        OrderUtil.deleteItem(id, orderRepository);
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new AdminStore(orderRepository, user).setVisible(true);
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
