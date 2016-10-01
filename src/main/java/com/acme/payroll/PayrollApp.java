package com.acme.payroll;

import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.model.Employee;
import com.acme.payroll.exception.CurrencyNotFoundException;
import com.acme.payroll.injection.component.DaggerPayrollComponent;
import com.acme.payroll.injection.module.PayrollModule;
import com.acme.payroll.utils.JsonResourceLoader;
import com.acme.payroll.utils.ResourceLoader;
import com.acme.payroll.utils.SalaryUtils;

import javax.inject.Inject;

public class PayrollApp {
    @Inject Payroll payroll;
    @Inject ResourceLoader jsonResourceLoader;

    public static void main(String[] args) {
        PayrollApp payrollApp = new PayrollApp();
        payrollApp.inject();
        payrollApp.run();
    }

    private void inject() {
        DaggerPayrollComponent.builder()
                .payrollModule(new PayrollModule(new SimpleStorage(), new JsonResourceLoader()))
                .build()
                .inject(this);
    }

    private void run() {
        payroll.setEmployees(jsonResourceLoader.getEmployees());
        payroll.setCurrencies(jsonResourceLoader.getCurrencies());

        for (Employee employee : payroll.getEmployees()) {
            try {
                String name = employee.getName();
                String currencyId = employee.getRequiredCurrencyId();
                double rate = payroll.getCurrency(currencyId).getRate();
                double yearlyAmount = SalaryUtils.convertSalary(employee.getSalary().getAmount(), rate);
                double monthlyAmount = SalaryUtils.getMonthlyPayment(yearlyAmount);

                System.out.println(String.format("Name: %s || Monthly Payment: %s %s",
                        name, currencyId, SalaryUtils.formatValue(monthlyAmount)));
            } catch (CurrencyNotFoundException e) {
                System.err.println(String.format("%s for employee %s", e.getMessage(), employee.getName()));
            } catch (NullPointerException e) {
                System.err.println("");
            }
        }
    }
}
