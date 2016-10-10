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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PayrollApp {
    private static final String RESULT_FORMAT = "Name: %s || Monthly Payment: %s %s";

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
        loadData();
        printMonthlyPayments(getEmployeesMonthlyPayment());
    }

    private void loadData() {
        payroll.setEmployees(jsonResourceLoader.getEmployees());
        payroll.setCurrencies(jsonResourceLoader.getCurrencies());
    }

    private List<MonthlyPaymentInfo> getEmployeesMonthlyPayment() {
        List<MonthlyPaymentInfo> monthlyPayments = new ArrayList<>();
        for (Employee employee : payroll.getEmployees()) {
            try {
                String name = employee.getName();
                String currencyId = employee.getRequiredCurrencyId();
                String rate = payroll.getCurrency(currencyId).getRate();
                BigDecimal yearlyAmount = SalaryUtils.convertSalary(employee.getSalary().getAmount(), rate);
                BigDecimal monthlyAmount = SalaryUtils.getMonthlyPayment(yearlyAmount);

                monthlyPayments.add(new MonthlyPaymentInfo(
                        name, currencyId, SalaryUtils.formatValue(monthlyAmount, Locale.UK)));
            } catch (CurrencyNotFoundException e) {
                System.err.println(String.format("%s for employee %s", e.getMessage(), employee.getName()));
            } catch (NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
        return monthlyPayments;
    }

    private void printMonthlyPayments(List<MonthlyPaymentInfo> monthlyPaymentInfo) {
        for (MonthlyPaymentInfo i : monthlyPaymentInfo) {
            System.out.println(String.format(RESULT_FORMAT, i.employeeName, i.currencyId, i.formattedValue));
        }
    }

    public static class MonthlyPaymentInfo {
        public final String employeeName;
        public final String currencyId;
        public final String formattedValue;

        private MonthlyPaymentInfo(String employeeName, String currencyId, String formattedValue) {
            this.employeeName = employeeName;
            this.currencyId = currencyId;
            this.formattedValue = formattedValue;
        }
    }
}
