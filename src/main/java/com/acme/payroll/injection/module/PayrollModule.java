package com.acme.payroll.injection.module;

import com.acme.payroll.Payroll;
import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.Storage;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class PayrollModule {

    private final Storage storage;

    public PayrollModule(Storage storage) {
        this.storage = storage;
    }

    @Provides
    @Singleton
    Storage provideStorage() {
        return new SimpleStorage();
    }

    @Provides
    @Singleton
    Payroll providePayroll() {
        return new Payroll(storage);
    }
}
