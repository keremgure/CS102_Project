/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Basket;
import core.Customer;
import core.FarmBank;
import core.Order;
import utilities.CommonUtilities;
import utilities.JTableButtonColumn;
import utilities.JTableSpinnerColumn;
import utilities.PopupFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

class ViewBasketFrame extends PopupFrame {
    private final Basket BASKET;
    private Customer customer;
    private double basketTotal;
    private JLabel basketTotalLabel;
    private JButton buyButton;

    ViewBasketFrame(Customer customer) {
        super("Basket of " + customer.getName());
        this.basketTotal = customer.getBasket().getTotalCost();
        this.customer = customer;
        this.BASKET = customer.getBasket();

        JPanel basketPanel = new JPanel();
        basketPanel.setLayout(new BoxLayout(basketPanel, BoxLayout.Y_AXIS));


        Object[][] rowData = new Object[customer.getBasket().getSize()][3];
        for (int i = 0; i < rowData.length; i++) {
            JSpinner amountField = CommonUtilities.createDecimalSpinner("0.00", 1.00, 0.0, Double.MAX_VALUE, 0.1);

            rowData[i][0] = BASKET.get(i);
            amountField.setValue(BASKET.get(i).getAmount());
            rowData[i][1] = amountField;

            final int I = i;
            amountField.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    BASKET.get(I).setAmount(((Number) ((JSpinner) e.getSource()).getValue()).doubleValue());
                    updateBasket();
                }
            });
            rowData[i][2] = "Remove";
        }
        String[] colNames = {"Item", "Amount", ""};


        DefaultTableModel model = new DefaultTableModel(rowData, colNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        JTable ordersTable = new JTable(model);
        ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        ordersTable.getColumnModel().getColumn(1).setMinWidth(120);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = Integer.valueOf(e.getActionCommand());
                Order order = (Order) ordersTable.getValueAt(rowNum, 0);
                BASKET.remove(order);
                updateBasket();
                model.removeRow(rowNum);
                model.fireTableDataChanged();
                if (BASKET.size() == 0)
                    buyButton.setEnabled(false);
            }
        };
        JTableButtonColumn btnCol = new JTableButtonColumn(ordersTable, action, 2);
        JTableSpinnerColumn jTableSpinnerColumn = new JTableSpinnerColumn(ordersTable, 1);
        JScrollPane scroller = new JScrollPane(ordersTable);
        basketPanel.add(scroller);

        buyButton = new JButton("Buy Now!");
        if (BASKET.size() == 0)
            buyButton.setEnabled(false);
        else {
            buyButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (customer.getAcc().getBalance() < basketTotal)
                        JOptionPane.showMessageDialog(getFrame(), "You don't have enough money to buy the items in your basket.\nBasket Total: \u20BA" + basketTotal + "\nAccount Balance: \u20BA" + customer.getAcc().getBalance());
                    else {
                        for (Order order : BASKET)
                            order.processOrder();
                        JOptionPane.showMessageDialog(getFrame(), "Buying Successful.\nBought Total: \u20BA" + basketTotal + "\n Balance left: \u20BA" + customer.getAcc().getBalance());

                        Basket.addBasketToList(customer.getBasket());
                        customer.renewBasket();

                        new ViewBasketFrame(customer);
                        dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));

                    }
                }
            });
        }
        basketPanel.add(buyButton);

        basketTotalLabel = new JLabel("Basket Total: \u20BA" + basketTotal);
        basketPanel.add(basketTotalLabel);

        JLabel accBalance = new JLabel("Account Balance: \u20BA" + customer.getAcc().getBalance());
        basketPanel.add(accBalance);


        FarmBank.changeFont(basketPanel,"Roboto");
        setContentPane(basketPanel);
        pack();
    }

    private void updateBasket() {
        basketTotal = BASKET.getTotalCost();
        basketTotalLabel.setText("Basket Total: \u20BA" + basketTotal);
        setTitle("Basket of " + customer.getName() + " | Basket Total: " + basketTotal);
    }
}
