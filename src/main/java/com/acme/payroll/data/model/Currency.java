package com.acme.payroll.data.model;

public class Currency {
    private final String currencyId;
    private final String rate;

    public Currency(String currencyId, String rate) {
        this.currencyId = currencyId;
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getRate() {
        return rate;
    }
}
