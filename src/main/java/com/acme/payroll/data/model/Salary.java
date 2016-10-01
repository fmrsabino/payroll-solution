package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

public class Salary implements RootItem {
    @SerializedName("currency") private final String currencyId;
    private final double amount;
    @SerializedName("display_amount") private final String displayAmount;

    public Salary(String currencyId, double amount, String displayAmount) {
        this.currencyId = currencyId;
        this.amount = amount;
        this.displayAmount = displayAmount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "currencyId='" + currencyId + '\'' +
                ", amount='" + amount + '\'' +
                ", displayAmount='" + displayAmount + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salary salary = (Salary) o;

        if (Double.compare(salary.amount, amount) != 0) return false;
        if (currencyId != null ? !currencyId.equals(salary.currencyId) : salary.currencyId != null) return false;
        return displayAmount != null ? displayAmount.equals(salary.displayAmount) : salary.displayAmount == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = currencyId != null ? currencyId.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (displayAmount != null ? displayAmount.hashCode() : 0);
        return result;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDisplayAmount() {
        return displayAmount;
    }
}
