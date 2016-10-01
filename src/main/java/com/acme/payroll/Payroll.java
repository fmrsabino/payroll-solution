package com.acme.payroll;

import com.acme.payroll.data.Storage;
import com.acme.payroll.data.model.*;
import com.acme.payroll.exception.CurrencyNotFoundException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Payroll {
    private final Storage storage;

    @Inject
    public Payroll(Storage storage) {
        this.storage = storage;
    }

    public void setEmployees(String employeesData) {
        JsonHeader<Employee> employeesJson =
                parseRoot(employeesData, new TypeToken<JsonHeader<Employee>>(){}.getType());
        if (employeesJson == null) { return; }
        storage.setEmployees(employeesJson.getItems());
    }

    public void setCurrencies(String currencyData) {
        JsonHeader<Currency> currencyJson =
                parseRoot(currencyData, new TypeToken<JsonHeader<Currency>>(){}.getType());
        if (currencyJson == null) { return; }
        storage.setCurrencies(currencyJson.getItems());
    }

    public Collection<Employee> getEmployees() {
        return storage.getEmployees();
    }

    public Currency getCurrency(String currencyId) throws CurrencyNotFoundException {
        Currency currency = storage.getCurrency(currencyId);
        if (currency == null) {
            throw new CurrencyNotFoundException(currencyId);
        } else {
            return currency;
        }
    }

    public List<Salary> getPayroll(String inputData) {
        JsonHeader<Employee> employeeJsonHeader = parseRoot(inputData,
                new TypeToken<JsonHeader<Employee>>(){}.getType());
        if (employeeJsonHeader == null) { return new ArrayList<>(); }
        storage.setEmployees(employeeJsonHeader.getItems());
        return employeeJsonHeader.getItems().stream().map(Employee::getSalary).collect(Collectors.toList());
    }

    private <M extends RootItem> JsonHeader<M> parseRoot(String inputData, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(inputData, type);
    }
}
