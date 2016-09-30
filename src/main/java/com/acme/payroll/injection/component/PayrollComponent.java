package com.acme.payroll.injection.component;

import com.acme.payroll.Payroll;
import com.acme.payroll.PayrollApp;
import com.acme.payroll.injection.module.PayrollModule;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = PayrollModule.class)
public interface PayrollComponent {
    void inject(PayrollApp payrollApp);

    Payroll payroll();
}
