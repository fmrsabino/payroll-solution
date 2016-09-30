package com.acme.payroll.data;


import com.acme.payroll.data.model.Currency;
import com.acme.payroll.data.model.Employee;

import java.util.Collection;
import java.util.List;

public interface Storage {
    void setEmployees(List<Employee> employees);
    void setCurrencies(List<Currency> currencies);

    Collection<Employee> getEmployees();
    Collection<Currency> getCurrencies();

    Employee getEmployee(String name);
    Currency getCurrency(String currencyId);
}
