/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.FarmBank;
import core.Item;
import utilities.CommonUtilities;
import utilities.PopupFrame;
import utilities.SpringLayoutUtilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFrameItem extends PopupFrame {
    private String[] unitArray = {"kg", "liter", "piece"};
    private JLabel resultLabel;
    private JList productList;
    private JTextField shortDescField;
    private JSpinner priceField;
    private JComboBox unitBox;

    public RegisterFrameItem() {
        super("Create new Item");
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(1, 1));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        itemPanel.add(leftPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new SpringLayout());
        leftPanel.add(fieldPanel);


        fieldPanel.add(new JLabel("Generic Product"));

        productList = new JList(FarmBank.GENERIC_PRODUCTS);
        productList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateLabel();
            }
        });
        productList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        productList.setVisibleRowCount(-1);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane productListScroller = new JScrollPane(productList);
        productListScroller.setPreferredSize(new Dimension(150, 80));
        fieldPanel.add(productListScroller);


        fieldPanel.add(new JLabel("Price"));

        priceField = CommonUtilities.createDecimalSpinner("\u20BA" + "0.00", 1.00, 0.0, Double.MAX_VALUE, 1.0);
        final JTextField jtf = ((JSpinner.DefaultEditor) priceField.getEditor()).getTextField();
        jtf.getDocument().addDocumentListener(new DocumentListener() {
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
        priceField.setPreferredSize(new Dimension(150, 22));
        fieldPanel.add(priceField);


        fieldPanel.add(new JLabel("Short Description"));

        shortDescField = new JTextField("", 10);
        shortDescField.getDocument().addDocumentListener(new DocumentListener() {
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
        fieldPanel.add(shortDescField);


        fieldPanel.add(new JLabel("Unit"));

        unitBox = new JComboBox(unitArray);
        unitBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    updateLabel();
            }
        });
        fieldPanel.add(unitBox);

        SpringLayoutUtilities.createGrid(fieldPanel, 4, 2, 5, 5, 10, 10);


        JButton submitBtn = new JButton("Submit");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isValidRegistration()) {
                    String product = (String) productList.getSelectedValue();
                    String unit = (String) unitBox.getSelectedItem();
                    String shortDesc = shortDescField.getText();
                    double price = ((Number) priceField.getValue()).doubleValue();

                    FarmBank.ALL_ITEMS.add(new Item(product, price, unit, shortDesc));

                    MainFrame mainFrame = (MainFrame) FarmBank.MAIN_FRAME;
                    mainFrame.updateItemList();

                    JOptionPane.showMessageDialog(getFrame(), "Item Creation Successful.\n" + product + "\n" + price + "per " + unit + "\n" + shortDesc);
                } else {
                    JOptionPane.showMessageDialog(getFrame(),"All the fields have to be filled in order to create an Item!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton clearBtn = new JButton("Clear");
        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                priceField.setValue(0.0);
                shortDescField.setText("");
                productList.setSelectedIndex(0);
            }
        });

        //Organize Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        buttonsPanel.add(clearBtn);
        buttonsPanel.add(submitBtn);
        leftPanel.add(buttonsPanel);


        JPanel rightPanel = new JPanel();
        resultLabel = new JLabel("No Item set.");
        rightPanel.add(resultLabel);
        itemPanel.add(rightPanel);


        setContentPane(itemPanel);
        pack();
    }

    private void updateLabel() {
        resultLabel.setText("<html><i> Generic Type: " + productList.getSelectedValue() + "<br/> Price: " + ((Number) priceField.getValue()).doubleValue() + "<br/> Per: " + unitBox.getSelectedItem() + "<br/> Short Desc: " + shortDescField.getText() + "</i></html>");
    }

    private boolean isValidRegistration() {
        return productList.getSelectedValue() != null && shortDescField.getText().length() > 0;
    }
}
