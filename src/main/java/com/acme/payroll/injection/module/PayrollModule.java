package com.acme.payroll.injection.module;

import com.acme.payroll.Payroll;
import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.Storage;
import com.acme.payroll.utils.ResourceLoader;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class PayrollModule {

    private final Storage storage;
    private final ResourceLoader resourceLoader;

    public PayrollModule(Storage storage, ResourceLoader resourceLoader) {
        this.storage = storage;
        this.resourceLoader = resourceLoader;
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

    @Provides
    @Singleton
    ResourceLoader provideResourceLoader() {
        return this.resourceLoader;
    }

}
