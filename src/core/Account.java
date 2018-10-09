/*
 * This file is a part of "CS102 Project".
 *
 * Authors: Kerem Gure & Berk Egen
 */

package core;

import java.text.DecimalFormat;

public class Account {
    private double balance;
    private static int count = 1;
    private int id;

    public Account(double balance) {
        this.balance = balance;
        this.id = count++;
    }

    public Account(double balance, int id) {
        this.balance = balance;
        this.id = id;
    }

    public Account() {
        this.id = count++;
    }

    public double getBalance() {
        DecimalFormat df = new DecimalFormat("##.###");
        return Double.parseDouble(df.format(balance));
    }

    public static void transferMoney(Person from, Person to, double amount) {
        Account fromAcc = from.getAcc();
        Account toAcc = to.getAcc();
        boolean shouldCommissionApply = true;
        double commission;
        if (toAcc.id == 0)
            shouldCommissionApply = false;
        if (shouldCommissionApply) {
            commission = amount * FarmBank.COMMISSION_RATE / 100;
            double toTransfer = amount - commission;
            fromAcc.balance -= toTransfer;
            toAcc.balance += toTransfer;
            transferMoney(from, FarmBank.FARMBANK, commission);
        } else {
            fromAcc.balance -= amount;
            toAcc.balance += amount;
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", id=" + id +
                '}';
    }
}
