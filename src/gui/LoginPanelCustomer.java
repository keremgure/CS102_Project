/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.Customer;
import core.FarmBank;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanelCustomer extends LoginPanelBase {
    public LoginPanelCustomer() {
        super();
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean found = false;
                String name = null;
                for (Customer customer : FarmBank.CUSTOMERS) {
                    name = nameField.getText();
                    if (customer.getName().equalsIgnoreCase(name)) {
                        found = true;
                        new UserFrameCustomer(customer);
                        break;
                    }
                }
                if (!found)
                    JOptionPane.showMessageDialog(FarmBank.MAIN_FRAME, "No Customer Found named " + name);
            }
        });
    }
}
