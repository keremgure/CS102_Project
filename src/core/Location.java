/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

public class Location {
    private String zipCode;
    private String name;

    public Location(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + zipCode + ")";
    }
}