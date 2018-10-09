/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;


public class Customer extends Person {
    private Location loc;
    private Basket basket;

    public Customer(String name, Account acc, Location loc) {
        super(name, acc);
        this.loc = loc;
        basket = new Basket(this);
    }

    public Location getLocation() {
        return loc;
    }

    public Basket getBasket() {
        return basket;
    }

    @Override
    public String getDetails() {
        return "Name: " + getName() + "\nAccount Balance: " + getAcc().getBalance() + "\u20BA \nLocation: " + getLocation();
    }
    public void renewBasket(){
        basket = new Basket(this);
    }
}
