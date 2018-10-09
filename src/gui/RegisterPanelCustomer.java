/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Account;
import core.Customer;
import core.FarmBank;
import core.Location;
import utilities.CommonUtilities;
import utilities.SpringLayoutUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterPanelCustomer extends RegisterPanelBase {
    private JComboBox locationBox;

    public RegisterPanelCustomer() {
        super();
        JSpinner balanceField = CommonUtilities.createDecimalSpinner("\u20BA" + "0.00", 10.00, 0.0, Double.MAX_VALUE, 1.0);
        balanceField.setPreferredSize(new Dimension(75, 20));

        fieldPanel.add(new JLabel("Initial Balance"));
        fieldPanel.add(balanceField);

        locationBox = new JComboBox(FarmBank.ALL_LOCATIONS.toArray());
        fieldPanel.add(new JLabel("Location"));
        fieldPanel.add(locationBox);


        //Organize Buttons
        buttonsPanel.add(createLocBtn);
        buttonsPanel.add(submitBtn);
        add(buttonsPanel);


        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isValidRegistration()) {
                    boolean nameAlreadyExist = false;
                    for (Customer customer : FarmBank.CUSTOMERS) {
                        if (customer.getName().equalsIgnoreCase(nameField.getText())) {
                            nameAlreadyExist = true;
                            JOptionPane.showMessageDialog(getPanel(), "Name Already Exist!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (!nameAlreadyExist) {
                        String name = nameField.getText();
                        double balance = ((Number) balanceField.getValue()).doubleValue();
                        FarmBank.CUSTOMERS.add(new Customer(name, new Account(balance), (Location) locationBox.getSelectedItem()));

                        JOptionPane.showMessageDialog(getPanel(), "Registration Successful.\nName:" + name + "\nInitial Balance: " + balance + "\nLocation: " + locationBox.getSelectedItem());
                    }
                }else{
                    JOptionPane.showMessageDialog(getPanel(),"All the fields have to be filled in order to register!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        SpringLayoutUtilities.createGrid(fieldPanel, 3, 2, 5, 5, 10, 10);
    }

    @Override
    public void updateLocationList() {
        locationBox.insertItemAt(FarmBank.ALL_LOCATIONS.get(FarmBank.ALL_LOCATIONS.size() - 1), 0);
        locationBox.setSelectedIndex(0);
    }

    private boolean isValidRegistration() {
        return !nameField.getText().equalsIgnoreCase("") && locationBox.getSelectedItem() != null;
    }
}
