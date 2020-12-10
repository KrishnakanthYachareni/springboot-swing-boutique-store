package com.boutique.store.util;

import com.boutique.store.entities.User;
import com.boutique.store.presentation.AdminStoreJFrame;
import com.boutique.store.repository.ProductRepository;

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
    private final ProductRepository productRepository;
    private final User user;
    private final AdminStoreJFrame thisObj;

    public AdminHandler(JTextField txt, ProductRepository productRepository, User user, AdminStoreJFrame thisObj) {
        super(txt);
        this.productRepository = productRepository;
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

        OrderUtil.deleteItem(id, productRepository);
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            new AdminStoreJFrame(productRepository, user).setVisible(true);
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
