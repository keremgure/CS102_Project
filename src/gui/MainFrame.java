/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainFrame extends JFrame {
    private RegisterPanelFarmer registerFarmer = new RegisterPanelFarmer();
    private RegisterPanelCustomer registerCustomer = new RegisterPanelCustomer();

    public MainFrame() {
        super("FarmBank");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(2, 1));

        JLabel welcomeLabel = new JLabel("Welcome to FarmBank");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 64));

        JLabel welcomeLabel2 = new JLabel("Please Select an Option above to Continue");
        welcomeLabel2.setFont(new Font("Calibri", Font.ITALIC, 32));
        welcomeLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(welcomeLabel);
        mainPanel.add(welcomeLabel2);

        JTabbedPane topPanel = new JTabbedPane(SwingConstants.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        topPanel.addTab("Main", mainPanel);
        topPanel.addTab("Register as Farmer", registerFarmer);
        topPanel.addTab("Register as Customer", registerCustomer);
        topPanel.addTab("Login as Farmer", new LoginPanelFarmer());
        topPanel.addTab("Login as Customer", new LoginPanelCustomer());
        topPanel.addTab("Review", new ReviewPanel());

        topPanel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (topPanel.getTitleAt(topPanel.getSelectedIndex()).equalsIgnoreCase("review")) {
                    topPanel.setComponentAt(topPanel.getSelectedIndex(), new ReviewPanel().getPanel());
                }
            }
        });


        add(topPanel);
        setVisible(true);
    }
    public void updateLocationLists(){
        registerCustomer.updateLocationList();
        registerFarmer.updateLocationList();
    }
    public void updateItemList(){
        registerFarmer.updateItemList();
    }
}