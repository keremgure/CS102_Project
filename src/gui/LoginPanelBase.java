/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import javax.swing.*;
import java.awt.*;

public class LoginPanelBase extends JPanel {
    JTextField nameField;
    JButton loginBtn;

    public LoginPanelBase() {
        super();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Name of the User"), constraints);

        nameField = new JTextField("", 15);
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(nameField, constraints);

        loginBtn = new JButton("Login");
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(loginBtn, constraints);
    }

    public JPanel getPanel() {
        return this;
    }
}
