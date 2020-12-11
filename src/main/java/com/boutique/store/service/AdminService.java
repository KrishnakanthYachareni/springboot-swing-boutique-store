package com.boutique.store.service;

import com.boutique.store.entities.User;
import com.boutique.store.presentation.AdminStoreJFrame;
import com.boutique.store.repository.ProductRepository;

import javax.swing.*;
import java.awt.*;

/**
 * It handles the store back end screen sales order items logic to performs CRUD.
 */
public class AdminService extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private final ProductRepository productRepository;
    private final User user;
    private final AdminStoreJFrame adminStoreJFrame;

    public AdminService(JTextField txt, ProductRepository productRepository, User user, AdminStoreJFrame adminStoreJFrame) {
        super(txt);
        this.productRepository = productRepository;
        this.user = user;
        this.adminStoreJFrame = adminStoreJFrame;

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

        long id = Long.parseLong(String.valueOf(table.getModel().getValueAt(0, 0)));

        OrderService.deleteItem(id, productRepository);
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            adminStoreJFrame.adminJFrame().dispose();
            adminStoreJFrame.adminStoreJFrame(user).setVisible(true);
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
