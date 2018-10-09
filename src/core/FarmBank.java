/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FarmBank {
    public static final double COMMISSION_RATE = 2.0;
    public static final Person FARMBANK = new Person("FarmBank", new Account(0.0, 0)) {
        public String getDetails() {
            return null;
        }
    };

    public static final ArrayList<Farmer> FARMERS = new ArrayList<>();
    public static final ArrayList<Customer> CUSTOMERS = new ArrayList<>();
    public static final ArrayList<Location> ALL_LOCATIONS = new ArrayList<>();
    public static final ArrayList<Item> ALL_ITEMS = new ArrayList<>();
    public static final String[] GENERIC_PRODUCTS = {"Apple", "Artichoke", "Cheese", "Egg", "Meat", "Milk", "Olive Oil", "Potato", "Tomato"};
    public static final JFrame MAIN_FRAME = new MainFrame();


    public static void main(String[] args) {
        changeFont(MAIN_FRAME,"Roboto");
        UIManager.put("OptionPane.messageFont", new Font("Roboto", Font.BOLD, 14));
    }

    public static void changeFont(Component component, String fontName) {
        Font prev = component.getFont();
        Font toSet = new Font(fontName,prev.getStyle(),prev.getSize());
        component.setFont(toSet);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, toSet.getFontName());
            }
        }
    }
}
