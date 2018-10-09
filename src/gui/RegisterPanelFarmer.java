/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.*;
import utilities.SpringLayoutUtilities;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RegisterPanelFarmer extends RegisterPanelBase {
    private DefaultListModel locationListModel;
    private DefaultListModel itemListModel;
    private JList locationList;
    private JList itemList;

    public RegisterPanelFarmer() {
        super();

        locationListModel = new DefaultListModel();
        locationList = new JList(locationListModel);
        locationList.setLayoutOrientation(JList.VERTICAL);
        locationList.setVisibleRowCount(-1);
        locationList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (Location loc : FarmBank.ALL_LOCATIONS)
            locationListModel.addElement(loc);

        fieldPanel.add(new JLabel("Locations"));

        JScrollPane locationListScroller = new JScrollPane(locationList);
        locationListScroller.setPreferredSize(new Dimension(150, 80));
        fieldPanel.add(locationListScroller, BorderLayout.WEST);

        itemListModel = new DefaultListModel();
        itemList = new JList(itemListModel);
        itemList.setLayoutOrientation(JList.VERTICAL);
        itemList.setVisibleRowCount(-1);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (Item item : FarmBank.ALL_ITEMS)
            itemListModel.addElement(item);

        fieldPanel.add(new JLabel("Items"));

        JScrollPane itemListScroller = new JScrollPane(itemList);
        itemListScroller.setPreferredSize(new Dimension(200, 80));
        fieldPanel.add(itemListScroller, BorderLayout.WEST);

        SpringLayoutUtilities.createGrid(fieldPanel,3,2,5,5,10,5);

        JButton createItemBtn = new JButton("Create new Item");
        createItemBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new RegisterFrameItem();
            }
        });

        //Organize Buttons
        buttonsPanel.add(createItemBtn);
        buttonsPanel.add(createLocBtn);
        buttonsPanel.add(submitBtn);

        add(buttonsPanel);


        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isValidRegistration()) {
                    boolean nameAlreadyExist = false;
                    for (Farmer farmer : FarmBank.FARMERS) {
                        if (farmer.getName().equalsIgnoreCase(nameField.getText())) {
                            nameAlreadyExist = true;
                            JOptionPane.showMessageDialog(getPanel(), "Name Already Exist!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (!nameAlreadyExist) {
                        String name = nameField.getText();
                        List<Location> locations = locationList.getSelectedValuesList();
                        List<Item> items = itemList.getSelectedValuesList();
                        FarmBank.FARMERS.add(new Farmer(name, new Account(), items, locations));

                        JOptionPane.showMessageDialog(getPanel(), "Registration Successful.\nName:" + name + "\nLocations: " + locations + "\nItems: " + items);
                    }
                }else{
                    JOptionPane.showMessageDialog(getPanel(),"All the fields have to be filled in order to register!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public void updateLocationList() {
        locationListModel.add(0, FarmBank.ALL_LOCATIONS.get(FarmBank.ALL_LOCATIONS.size() - 1));
    }

    public void updateItemList() {
        itemListModel.add(0, FarmBank.ALL_ITEMS.get(FarmBank.ALL_ITEMS.size() - 1));
    }

    private boolean isValidRegistration() {
        return !nameField.getText().equalsIgnoreCase("") && locationList.getSelectedValuesList().size() > 0 && itemList.getSelectedValuesList().size() > 0;
    }
}
