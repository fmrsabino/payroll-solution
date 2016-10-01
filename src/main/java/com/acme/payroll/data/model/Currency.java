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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (Double.compare(currency.rate, rate) != 0) return false;
        return currencyId != null ? currencyId.equals(currency.currencyId) : currency.currencyId == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = currencyId != null ? currencyId.hashCode() : 0;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
