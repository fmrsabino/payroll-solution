package com.acme.payroll;

import com.acme.payroll.data.MockStorage;
import com.acme.payroll.injection.component.DaggerPayrollComponent;
import com.acme.payroll.injection.module.PayrollModule;
import com.acme.payroll.utils.JsonUtils;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PayrollApp {
    private static final String EMPLOYEES_PATH = "employees.json";

    @Inject Payroll payroll;

    public static void main(String[] args) {
        PayrollApp payrollApp = new PayrollApp();
        payrollApp.inject();
        payrollApp.run();
    }

    private void inject() {
        DaggerPayrollComponent.builder()
                .payrollModule(new PayrollModule(new MockStorage()))
                .build()
                .inject(this);
    }

    private void run() {
        String employeesJson = readJson(EMPLOYEES_PATH);
        payroll.getPayroll(employeesJson).stream().forEach(System.out::println);
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
