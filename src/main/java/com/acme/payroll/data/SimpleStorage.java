package com.acme.payroll.data;

import com.acme.payroll.data.model.Currency;
import com.acme.payroll.data.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleStorage implements Storage {
    private final Map<String, Employee> employees = new HashMap<>();
    private final Map<String, Currency> currencies = new HashMap<>();

    public void setEmployees(List<Employee> employees) {
        this.employees.clear();
        employees.stream().forEach(employee -> this.employees.put(employee.getName(), employee));
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies.clear();
        currencies.stream().forEach(currency -> this.currencies.put(currency.getCurrencyId(), currency));
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public Collection<Currency> getCurrencies() {
        return currencies.values();
    }

    @Override
    public Employee getEmployee(String name) {
        return employees.get(name);
    }

    @Override
    public Currency getCurrency(String currencyId) {
        return currencies.get(currencyId);
    }
}
