/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

import java.text.DecimalFormat;

public class Order {
    private Person seller;
    private Person buyer;
    private Item item;
    private double amount;
    private double totalCost;
    private DecimalFormat df = new DecimalFormat("##.###");
    private static int count = 0;
    private int id;

    public Order(Person seller, Person buyer, Item item, double amount) {
        this.seller = seller;
        this.buyer = buyer;
        this.item = item;
        this.amount = Double.parseDouble(df.format(amount));
        this.totalCost = Double.parseDouble(df.format(amount * item.getPrice()));
        this.id = count++;
    }

    public void processOrder() {
        Account.transferMoney(buyer, seller, totalCost);
    }

    public Person getSeller() {
        return seller;
    }

    public Person getBuyer() {
        return buyer;
    }

    public Item getItem() {
        return item;
    }

    public int getId() {
        return id;
    }

    public double getTotalCost() {
        updateTotalCost();
        return totalCost;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return item + "\n From: " + seller + "\nAmount: " + amount + "\n Total Cost: \u20BA" + totalCost + "\n";
    }

    public String farmerScreen() {
        return item + "\n To: " + buyer + "\n Amount: " + amount + "\n Total Price: \u20BA" + totalCost + "\n";
    }

    public void updateTotalCost() {
        this.totalCost = Double.parseDouble(df.format(this.amount * item.getPrice()));
    }
}
