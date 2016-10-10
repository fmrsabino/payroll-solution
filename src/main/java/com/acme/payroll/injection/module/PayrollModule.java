package com.acme.payroll.injection.module;

import com.acme.payroll.Payroll;
import com.acme.payroll.configuration.Configuration;
import com.acme.payroll.data.Storage;
import com.acme.payroll.resourceloader.ResourceLoader;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class PayrollModule {
    private final Storage storage;
    private final ResourceLoader resourceLoader;
    private final Configuration configuration;

    public PayrollModule(Storage storage, ResourceLoader resourceLoader, Configuration configuration) {
        this.storage = storage;
        this.resourceLoader = resourceLoader;
        this.configuration = configuration;
    }

    @Provides
    @Singleton
    Storage provideStorage() {
        return storage;
    }

    @Provides
    @Singleton
    Payroll providePayroll() {
        return new Payroll(storage);
    }

    @Provides
    @Singleton
    ResourceLoader provideResourceLoader() {
        return resourceLoader;
    }

    @Provides
    @Singleton
    Configuration provideConfiguration() {
        return configuration;
    }

}
