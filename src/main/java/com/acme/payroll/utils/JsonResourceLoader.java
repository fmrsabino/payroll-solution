package com.acme.payroll.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonResourceLoader implements ResourceLoader {
    private static final String EMPLOYEES_PATH = "resources/employees.json";
    private static final String EXCHANGE_RATES_PATH = "resources/exchange_rates.json";

    @Override
    public String getEmployees() {
        return readJson(EMPLOYEES_PATH);
    }

    @Override
    public String getCurrencies() {
        return readJson(EXCHANGE_RATES_PATH);
    }

    private static String readJson(String path) {
        try {
            return JsonUtils.inputStreamToString(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
