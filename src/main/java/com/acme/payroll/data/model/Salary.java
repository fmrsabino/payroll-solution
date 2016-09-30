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
