package com.acme.payroll.data.model;

import com.google.gson.annotations.SerializedName;

public class Employee implements RootItem {
    @SerializedName("first_name") private final String firstName;
    @SerializedName("last_name") private final String lastName;
    private final String section;
    @SerializedName("currency") private final String requiredCurrencyId;
    private final Salary salary;

    public Employee(String firstName, String lastName, String section, String requiredCurrencyId, Salary salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
        this.requiredCurrencyId = requiredCurrencyId;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section='" + section + '\'' +
                ", requiredCurrencyId='" + requiredCurrencyId + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return firstName;
    }

    public String getRequiredCurrencyId() {
        return requiredCurrencyId;
    }

    public Salary getSalary() {
        return salary;
    }
}
