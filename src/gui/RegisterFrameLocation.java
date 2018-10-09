/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.FarmBank;
import core.Location;
import utilities.CommonUtilities;
import utilities.PopupFrame;
import utilities.SpringLayoutUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterFrameLocation extends PopupFrame {
    private JLabel resultLabel;
    private JTextField nameField;
    private JFormattedTextField zipCodeField;

    public RegisterFrameLocation() {
        super("Create New Location");
        setTitle("Create new Location");
        JPanel locationPanel = new JPanel();
        locationPanel.setLayout(new GridLayout(1, 1));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        locationPanel.add(leftPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new SpringLayout());
        leftPanel.add(fieldPanel);


        fieldPanel.add(new JLabel("Name"));

        nameField = new JTextField("", 15);
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel();
            }
        });
        fieldPanel.add(nameField);

        fieldPanel.add(new JLabel("Zip Code"));
        zipCodeField = new JFormattedTextField(CommonUtilities.createFormatter("#####"));
        zipCodeField.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateLabel();
            }
        });
        fieldPanel.add(zipCodeField);

        SpringLayoutUtilities.createGrid(fieldPanel, 2, 2, 5, 5, 10, 5);


        JButton clearBtn = new JButton("Clear");
        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameField.setText("");
                zipCodeField.setValue(null);
            }
        });

        JButton submitBtn = new JButton("Submit");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (isValidRegistration()) {
                    FarmBank.ALL_LOCATIONS.add(new Location(zipCodeField.getText(), nameField.getText()));

                    MainFrame mainFrame = (MainFrame) FarmBank.MAIN_FRAME;
                    mainFrame.updateLocationLists();

                    JOptionPane.showMessageDialog(getFrame(), "Location Creation Successful.");
                } else {
                    JOptionPane.showMessageDialog(getFrame(),"All fields have to be filled in order to create a Location!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Organize Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonsPanel.add(clearBtn);
        buttonsPanel.add(submitBtn);
        leftPanel.add(buttonsPanel);


        JPanel rightPanel = new JPanel();
        resultLabel = new JLabel("No Address Set.");
        rightPanel.add(resultLabel);
        locationPanel.add(rightPanel);


        setContentPane(locationPanel);
        pack();
    }

    private void updateLabel() {
        resultLabel.setText("<html><i> Name: " + (nameField.getText() != null ? nameField.getText() : "Not Set") + "<br/> Zip Code: " + (zipCodeField.getText() != null ? zipCodeField.getText() : "Not Set") + "</i></html>");
    }

    private boolean isValidRegistration() {
        return nameField.getText().length() > 0 && zipCodeField.getText().length() > 0;
    }
}
