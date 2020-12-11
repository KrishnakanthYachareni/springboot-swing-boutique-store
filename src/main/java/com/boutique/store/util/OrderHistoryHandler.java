package com.boutique.store.util;

import com.boutique.store.entities.User;
import com.boutique.store.presentation.UserCartJFrame;
import com.boutique.store.repository.OrderHistoryRepository;

import javax.swing.*;
import java.awt.*;

/**
 * Handler for Order history screen view button.
 */
public class OrderHistoryHandler extends DefaultCellEditor {

    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private final OrderHistoryRepository orderHistoryRepository;
    private final User user;
    private UserCartJFrame userCartJFrame;

    public OrderHistoryHandler(JTextField jTextField, OrderHistoryRepository orderHistoryRepository, User user, UserCartJFrame userCartJFrame) {
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

//        long id = Long.parseLong(String.valueOf(table.getModel().getValueAt(0, 0)));
//TODO: Call View Items
//        OrderUtil.deleteItem(id, productRepository);
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            //TODO: Call View Items
//            userCartJFrame.userCartJFrame().dispose();
//            userCartJFrame.(user).setVisible(true);
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
