package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

public class Salary implements RootItem {
    @SerializedName("currency") private final String currencyId;
    private final String amount;
    @SerializedName("display_amount") private final String displayAmount;

    public Salary(String currencyId, String amount, String displayAmount) {
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

        if (currencyId != null ? !currencyId.equals(salary.currencyId) : salary.currencyId != null) return false;
        if (amount != null ? !amount.equals(salary.amount) : salary.amount != null) return false;
        return displayAmount != null ? displayAmount.equals(salary.displayAmount) : salary.displayAmount == null;

    }

    @Override
    public int hashCode() {
        int result = currencyId != null ? currencyId.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (displayAmount != null ? displayAmount.hashCode() : 0);
        return result;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getAmount() {
        return amount;
    }

    public String getDisplayAmount() {
        return displayAmount;
    }
}
