package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

public class Currency implements RootItem {
    @SerializedName("currency") private final String currencyId;
    private final double rate;

    public Currency(String currencyId, double rate) {
        this.currencyId = currencyId;
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public double getRate() {
        return rate;
    }
}
