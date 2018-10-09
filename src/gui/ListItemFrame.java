/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Customer;
import core.Farmer;
import core.Item;
import core.Order;
import utilities.CommonUtilities;
import utilities.JTableButtonColumn;
import utilities.JTableSpinnerColumn;
import utilities.PopupFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ListItemFrame extends PopupFrame {
    public ListItemFrame(Farmer farmer, Customer customer) {
        super("Items sold by " + farmer.getName());
        JPanel mainPanel = new JPanel();
        add(mainPanel);

        Object[][] rowData = new Object[farmer.getItems().size()][3];
        for (int i = 0; i < rowData.length; i++) {
            rowData[i][0] = farmer.getItems().get(i);

            JSpinner amountField = CommonUtilities.createDecimalSpinner("0.00", 1.00, 0.0, Double.MAX_VALUE, 0.1);

            rowData[i][1] = amountField;
            rowData[i][2] = "Add to Basket";
        }
        String[] colNames = {"Farmer", "Amount", ""};


        DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Roboto",table.getFont().getStyle(),table.getFont().getSize()));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.getColumnModel().getColumn(1).setMinWidth(120);


        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = Integer.valueOf(e.getActionCommand());
                Item item = (Item) table.getValueAt(rowNum, 0);

                double amount;
                if (table.getValueAt(rowNum, 1) instanceof JSpinner) {
                    JSpinner sp = (JSpinner) table.getValueAt(rowNum, 1);
                    amount = ((Number) sp.getValue()).doubleValue();
                } else {
                    SpinnerNumberModel spnm = (SpinnerNumberModel) table.getValueAt(rowNum, 1);
                    amount = ((Number) spnm.getValue()).doubleValue();
                }
                if (customer.getBasket().getList().add(new Order(farmer, customer, item, amount)))
                    JOptionPane.showMessageDialog(getFrame(), "Order:\n" + item + "\n Amount:" + amount + "\n added to basket successfully");
            }
        };
        JTableButtonColumn btnCol = new JTableButtonColumn(table, action, 2);
        JTableSpinnerColumn jTableSpinnerColumn = new JTableSpinnerColumn(table, 1);
        JScrollPane scroller = new JScrollPane(table);
        mainPanel.add(scroller);


        setContentPane(mainPanel);
        pack();
    }
}
