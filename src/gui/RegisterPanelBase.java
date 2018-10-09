/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class RegisterPanelBase extends JPanel {
    JPanel fieldPanel;
    JButton submitBtn;
    JButton createLocBtn;
    JTextField nameField;
    JPanel buttonsPanel;

    public RegisterPanelBase() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new SpringLayout());
        add(fieldPanel);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));


        JLabel nameLabel = new JLabel("Name", JLabel.TRAILING);
        fieldPanel.add(nameLabel);

        nameField = new JTextField("", 15);
        fieldPanel.add(nameField);
//        nameField.setHorizontalAlignment(SwingConstants.CENTER);


        createLocBtn = new JButton("Create new Location");
        createLocBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterFrameLocation();
            }
        });

        submitBtn = new JButton("Submit");
        submitBtn.setHorizontalAlignment(SwingConstants.CENTER);


    }

    public JPanel getPanel() {
        return this;
    }

    public abstract void updateLocationList();

}
