/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Basket extends ArrayList<Order> {
    public static final ArrayList<Basket> ALL_BOUGHT_BASKETS = new ArrayList<>();
    private static int count = 0;
    private int id;
    private Customer owner;

    public Basket(Customer owner) {
        super();
        this.owner = owner;
        this.id = count++;
    }

    public double getTotalCost() {
        DecimalFormat df = new DecimalFormat("##.###");
        double total = 0.0;
        for (Order order : this)
            total += order.getTotalCost();
        total = Double.parseDouble(df.format(total));
        return total;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size();
    }

    public ArrayList<Order> getList() {
        return this;
    }

    public Customer getOwner() {
        return owner;
    }

    public static void addBasketToList(Basket basket) {
        ALL_BOUGHT_BASKETS.add(basket);
    }

    public static ArrayList<Basket> getCustomerBasedList(Customer customer) {
        ArrayList<Basket> list = new ArrayList<>();
        for (Basket basket : ALL_BOUGHT_BASKETS) {
            if (basket.getOwner().equals(customer)) {
                list.add(basket);
            }
        }
        return list;
    }

    public static ArrayList<Order> getFarmerBasedList(Farmer farmer) {
        ArrayList<Order> orders = new ArrayList<>();
        for (Basket basket : ALL_BOUGHT_BASKETS) {
            for (Order order : basket.getList()) {
                if (order.getSeller().equals(farmer)) {
                    orders.add(order);
                }
            }
        }
        return orders;
    }
}
