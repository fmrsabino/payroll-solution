package com.acme.payroll.exception;


public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String currencyId) {
        super(String.format("%s currency not found", currencyId));
    }
}
