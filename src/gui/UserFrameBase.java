/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package gui;

import core.*;
import utilities.PopupFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;


public abstract class UserFrameBase extends PopupFrame {
    JPanel buttonsPanel;
    JButton reviewBtn;

    public UserFrameBase(Person user) {
        super("Welcome " + user.getName() + " to FarmBank.");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JLabel header = new JLabel("Welcome to FarmBank, " + user.getName());
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setFont(new Font("Calibri", Font.BOLD, 32));
        mainPanel.add(header);

        JLabel accBalance = new JLabel("Account Balance: " + user.getAcc().getBalance());
        accBalance.setHorizontalAlignment(SwingConstants.CENTER);
        accBalance.setFont(new Font("Calibri", Font.ITALIC, 14));
        mainPanel.add(accBalance);

        JLabel header2 = new JLabel("Please select an option to continue");
        header2.setHorizontalAlignment(SwingConstants.CENTER);
        header2.setFont(new Font("Calibri", Font.ITALIC, 16));
        mainPanel.add(header2);

        reviewBtn = new JButton("Review Orders");

        JButton logOut = new JButton("Log Out");
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
            }
        });

        //Organize Buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        buttonsPanel.add(reviewBtn);
        buttonsPanel.add(logOut);
        mainPanel.add(buttonsPanel);


        setContentPane(mainPanel);
    }
}