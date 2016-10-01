package com.acme.payroll;

import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.model.Employee;
import com.acme.payroll.exception.CurrencyNotFoundException;
import com.acme.payroll.injection.component.DaggerPayrollComponent;
import com.acme.payroll.injection.module.PayrollModule;
import com.acme.payroll.utils.JsonUtils;
import com.acme.payroll.utils.SalaryUtils;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PayrollApp {
    private static final String EMPLOYEES_PATH = "resources/employees.json";
    private static final String EXCHANGE_RATES_PATH = "resources/exchange_rates.json";

    @Inject Payroll payroll;

    public static void main(String[] args) {
        PayrollApp payrollApp = new PayrollApp();
        payrollApp.inject();
        payrollApp.run();
    }

    private void inject() {
        DaggerPayrollComponent.builder()
                .payrollModule(new PayrollModule(new SimpleStorage()))
                .build()
                .inject(this);
    }

    private void run() {
        String employeesJson = readJson(EMPLOYEES_PATH);
        payroll.setEmployees(employeesJson);

        String currencyJson = readJson(EXCHANGE_RATES_PATH);
        payroll.setCurrencies(currencyJson);

        for (Employee employee : payroll.getEmployees()) {
            try {
                String name = employee.getName();
                String currencyId = employee.getRequiredCurrencyId();
                double rate = payroll.getCurrency(currencyId).getRate();
                double amount = SalaryUtils.convertSalary(employee.getSalary().getAmount(), rate);

                System.out.println(String.format("Name: %s || Monthly Payment: %s %s",
                        name, currencyId, SalaryUtils.formatSalary(amount)));
            } catch (CurrencyNotFoundException e) {
                System.err.println(String.format("%s for employee %s", e.getMessage(), employee.getName()));
            } catch (NullPointerException e) {
                System.err.println("");
            }
        }
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
