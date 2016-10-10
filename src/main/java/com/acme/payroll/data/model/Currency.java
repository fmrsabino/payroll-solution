package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

public class Currency implements RootItem {
    @SerializedName("currency") private final String currencyId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (currencyId != null ? !currencyId.equals(currency.currencyId) : currency.currencyId != null) return false;
        return rate != null ? rate.equals(currency.rate) : currency.rate == null;

    }

    @Override
    public int hashCode() {
        int result = currencyId != null ? currencyId.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }
}
