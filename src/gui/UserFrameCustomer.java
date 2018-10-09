/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Basket;
import core.Customer;
import core.FarmBank;
import utilities.CommonUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserFrameCustomer extends UserFrameBase {
    UserFrameCustomer(Customer customer) {
        super(customer);

        reviewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                String[] colNames = {"ID", "Total Cost"};

                ArrayList<Basket> baskets = Basket.getCustomerBasedList(customer);
                int rowLength = baskets.size();

                Object[][] rowData = new Object[rowLength][2];

                for (int i = 0; i < rowLength; i++) {
                    rowData[i][0] = baskets.get(i).getId();
                    rowData[i][1] = "\u20BA" + baskets.get(i).getTotalCost();
                }

                DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                JTable table = new JTable(model);
                table.setFont(new Font("Roboto",table.getFont().getStyle(),table.getFont().getSize()));
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setRowSelectionAllowed(false);

                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table.getSelectedRow();
                        int col = table.getSelectedColumn();
                        if (table.getValueAt(row, col) != null && row >= 0 && col >= 0) {
                            int id = ((Number) table.getValueAt(row, 0)).intValue();
                            Basket basket = null;
                            for (Basket b : baskets) {
                                if (b.getId() == id)
                                    basket = b;
                            }
                            JOptionPane.showMessageDialog(getFrame(), CommonUtilities.getListAsString(basket));
                        }
                    }
                });
                JScrollPane scroller = new JScrollPane(table);
                panel.add(scroller);

                JLabel hint = new JLabel("Hint: Click on the cells for more information...");
                hint.setFont(new Font("Roboto Light", Font.ITALIC, 14));
                panel.add(hint);

                JOptionPane.showMessageDialog(getFrame(), panel);
            }
        });

        JButton orderBtn = new JButton("New Order");
        orderBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CreateOrderFrame(customer);
            }
        });
        buttonsPanel.add(orderBtn);

        pack();
    }
}
