package com.acme.payroll.data;

import com.acme.payroll.data.model.Currency;
import com.acme.payroll.data.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final List<Employee> employees = new ArrayList<>();
    private final List<Currency> currency = new ArrayList<>();

    public void setEmployees(List<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
    }

    public void setCurrency(List<Currency> currency) {
        this.currency.clear();
        this.currency.addAll(currency);
    }
}
