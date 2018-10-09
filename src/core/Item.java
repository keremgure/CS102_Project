/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

public class Item {
    private String product;
    private String unit;
    private double price;
    private String shortDesc;

    public Item(String product, double price, String unit, String shortDesc) {
        this.product = product;
        this.price = price;
        this.unit = unit;
        this.shortDesc = shortDesc;
    }

    public String getProduct() {
        return product;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    @Override
    public String toString() {
        return product + "(" + shortDesc + ")" + ", \u20BA" + price + " per " + unit;
    }
}
