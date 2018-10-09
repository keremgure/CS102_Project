/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.FarmBank;
import core.Farmer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanelFarmer extends LoginPanelBase {
    public LoginPanelFarmer() {
        super();
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean found = false;
                String name = null;
                for (Farmer farmer : FarmBank.FARMERS) {
                    name = nameField.getText();
                    if (farmer.getName().equalsIgnoreCase(name)) {
                        found = true;
                        new UserFrameFarmer(farmer);
                        break;
                    }
                }
                if (!found)
                    JOptionPane.showMessageDialog(FarmBank.MAIN_FRAME, "No Farmer Found named " + name);
            }
        });
    }
}
