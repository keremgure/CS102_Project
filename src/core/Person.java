/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

public abstract class Person {
    private String name;
    private Account acc;


    public Person(String name, Account acc) {
        this.name = name;
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public Account getAcc() {
        return acc;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract String getDetails();
}
