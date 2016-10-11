package com.acme.payroll;

import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.model.Currency;
import com.acme.payroll.data.model.Employee;
import com.acme.payroll.data.model.Salary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    private SimpleStorage storage;

    @Before
    public void setup() {
        this.storage = new SimpleStorage();
    }

    @Test
    public void setEmployeesNull() {
        storage.setEmployees(null);
        Assert.assertEquals(0, storage.getEmployees().size());
    }

    @Test
    public void setEmployee() {
        String firstName = "MockFirstName";
        String secondName = "MockSecondName";
        Salary salary = new Salary("MockCurrency", "10", "MockDisplay");
        Employee employee = new Employee(firstName, secondName, "MockSection", "MockCurrency", salary);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        storage.setEmployees(employees);

        Assert.assertEquals(1, storage.getEmployees().size());
        Assert.assertSame(employee, storage.getEmployee(firstName + " " + secondName));
    }

    @Test
    public void setCurrenciesNull() {
        storage.setCurrencies(null);
        Assert.assertEquals(0, storage.getCurrencies().size());
    }

    @Test
    public void setCurrency() {
        Currency currency = new Currency("MockId", "1");
        List<Currency> currencies = new ArrayList<>();
        currencies.add(currency);

        storage.setCurrencies(currencies);

        Assert.assertEquals(1, storage.getCurrencies().size());
        Assert.assertSame(currency, storage.getCurrency("MockId"));
    }

    @Test
    public void setSameEmployees() {
        String firstName1 = "MockFirstName";
        String secondName1 = "MockSecondName";
        Salary salary1 = new Salary("MockCurrency", "10", "MockDisplay");
        Employee employee1 = new Employee(firstName1, secondName1, "MockSection", "MockCurrency", salary1);

        String firstName2 = "MockFirstName";
        String secondName2 = "MockSecondName";
        Salary salary2 = new Salary("MockCurrency", "10", "MockDisplay");
        Employee employee2 = new Employee(firstName2, secondName2, "MockSection", "MockCurrency", salary2);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        Assert.assertEquals(2, employees.size());

        storage.setEmployees(employees);

        Assert.assertEquals(1, storage.getEmployees().size());
        Assert.assertEquals(employee1, storage.getEmployee(firstName1 + " " + secondName1));
    }

    @Test
    public void setSameCurrency() {
        Currency currency1 = new Currency("MockId", "1");
        Currency currency2 = new Currency("MockId", "1");
        List<Currency> currencies = new ArrayList<>();
        currencies.add(currency1);
        currencies.add(currency2);

        Assert.assertEquals(2, currencies.size());

        storage.setCurrencies(currencies);

        Assert.assertEquals(1, storage.getCurrencies().size());
        Assert.assertEquals(currency1, storage.getCurrency("MockId"));
    }
}
