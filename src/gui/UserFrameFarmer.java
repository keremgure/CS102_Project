/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Basket;
import core.FarmBank;
import core.Farmer;
import core.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class UserFrameFarmer extends UserFrameBase {
    UserFrameFarmer(Farmer farmer) {
        super(farmer);

        reviewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                String[] colNames = {"ID", "Total Price with Commission"};

                ArrayList<Order> orders = Basket.getFarmerBasedList(farmer);
                int rowLength = orders.size();

                Object[][] rowData = new Object[rowLength][2];

                for (int i = 0; i < rowLength; i++) {
                    rowData[i][0] = orders.get(i).getId();
                    rowData[i][1] = "\u20BA" + orders.get(i).getTotalCost();
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
                            Order order = null;
                            for (Order o : orders) {
                                if (o.getId() == id)
                                    order = o;
                            }
                            JOptionPane.showMessageDialog(getFrame(), order.farmerScreen());
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

        pack();
    }
}
