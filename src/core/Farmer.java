/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

import utilities.CommonUtilities;

import java.util.ArrayList;
import java.util.List;

public class Farmer extends Person {
    private ArrayList<Location> locations;
    private ArrayList<Item> items;

    public Farmer(String name, Account account, List<Item> items, List<Location> locations) {
        super(name, account);
        this.items = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.items.addAll(items);
        this.locations.addAll(locations);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String getDetails() {
        return "Name: " + getName() + "\nAccount Balance: " + getAcc().getBalance() + "\u20BA \nLocations : " + CommonUtilities.getListAsString(locations) + "Products: " + CommonUtilities.getListAsString(items);
    }
}
