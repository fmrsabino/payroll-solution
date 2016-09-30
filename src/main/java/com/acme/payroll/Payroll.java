package com.acme.payroll;

import com.acme.payroll.data.Storage;
import com.acme.payroll.data.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class Payroll {
    private final Storage storage = new Storage();

    public void setEmployees(List<Employee> employees) {
        storage.setEmployees(employees);
    }

    public void setCurrency(List<Currency> currency) {
        storage.setCurrency(currency);
    }

    public List<Salary> getPayroll(String inputData) {
        JsonHeader<Employee> employeeJsonHeader = parseRoot(inputData,
                new TypeToken<JsonHeader<Employee>>(){}.getType());
        return employeeJsonHeader.getItems().stream().map(Employee::getSalary).collect(Collectors.toList());
    }

    private <M extends RootItem> JsonHeader<M> parseRoot(String inputData, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(inputData, type);
    }
}
