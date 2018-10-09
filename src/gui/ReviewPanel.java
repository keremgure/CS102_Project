/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Customer;
import core.FarmBank;
import core.Farmer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviewPanel extends JPanel {

    public ReviewPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] colNames = {"Customer", "Farmer"};

        int rowLength = Math.max(FarmBank.CUSTOMERS.size(), FarmBank.FARMERS.size());

        Object[][] rowData = new Object[rowLength][2];

        for (int i = 0; i < FarmBank.CUSTOMERS.size(); i++) {
            rowData[i][0] = FarmBank.CUSTOMERS.get(i);
        }
        for (int i = 0; i < FarmBank.FARMERS.size(); i++) {
            rowData[i][1] = FarmBank.FARMERS.get(i);
        }

        DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.setRowSelectionAllowed(false);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (table.getValueAt(row, col) != null) {
                    if (row >= 0 && col == 0) {
                        Customer customer = (Customer) table.getValueAt(row, col);
                        JOptionPane.showMessageDialog(getPanel(), customer.getDetails());
                    } else if (row >= 0 && col == 1) {
                        Farmer farmer = (Farmer) table.getValueAt(row, col);
                        JOptionPane.showMessageDialog(getPanel(), farmer.getDetails());
                    }
                }
            }
        });


        JScrollPane scroller = new JScrollPane(table);
        add(scroller);

        JLabel farmbankAcc = new JLabel("FarmBank Account Balance: \u20BA" + FarmBank.FARMBANK.getAcc().getBalance());
        farmbankAcc.setFont(new Font("Roboto BOLD", Font.ITALIC, 16));
        add(farmbankAcc);

        JLabel hint = new JLabel("Hint: Click on the cells for more information...");
        hint.setFont(new Font("Roboto Light", Font.ITALIC, 14));
        add(hint);
    }

    public JPanel getPanel() {
        return this;
    }
}
