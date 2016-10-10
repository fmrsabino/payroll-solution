package com.acme.payroll.configuration;


import java.util.Locale;

public class BaseConfiguration implements Configuration {
    @Override
    public Locale getLocale() {
        return Locale.UK;
    }
}
