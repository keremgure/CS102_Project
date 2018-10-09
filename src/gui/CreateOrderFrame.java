/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Customer;
import core.FarmBank;
import core.Farmer;
import core.Location;
import utilities.JTableButtonColumn;
import utilities.PopupFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CreateOrderFrame extends PopupFrame {
    public CreateOrderFrame(Customer customer) {
        super("Create new Order");
        JPanel mainPanel = new JPanel();
        ArrayList<Farmer> farmers = new ArrayList<>();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        Location location = customer.getLocation();
        for (Farmer farmer : FarmBank.FARMERS) {
            if (farmer.getLocations().contains(location)) {
                farmers.add(farmer);
            }
        }
        Object[][] rowData = new Object[farmers.size()][2];
        for (int i = 0; i < rowData.length; i++) {
            rowData[i][0] = farmers.get(i);
            rowData[i][1] = "View Farmer";
        }
        String[] colNames = {"Farmer", ""};


        DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(1).setMinWidth(120);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = Integer.valueOf(e.getActionCommand());
                Farmer farmer = (Farmer) table.getValueAt(rowNum, 0);
//                JOptionPane.showMessageDialog(null,farmer.getItems());
                new ListItemFrame(farmer, customer);
            }
        };
        JTableButtonColumn btnCol = new JTableButtonColumn(table, action, 1);

        JScrollPane scroller = new JScrollPane(table);
        mainPanel.add(scroller);


        JButton viewBasketBtn = new JButton("View Basket");
        viewBasketBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ViewBasketFrame(customer);
            }
        });
        mainPanel.add(viewBasketBtn);


        setContentPane(mainPanel);
        pack();
    }
}
