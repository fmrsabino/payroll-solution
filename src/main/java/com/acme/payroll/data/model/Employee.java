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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (section != null ? !section.equals(employee.section) : employee.section != null) return false;
        if (requiredCurrencyId != null ? !requiredCurrencyId.equals(employee.requiredCurrencyId) : employee.requiredCurrencyId != null)
            return false;
        return salary != null ? salary.equals(employee.salary) : employee.salary == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        result = 31 * result + (requiredCurrencyId != null ? requiredCurrencyId.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getRequiredCurrencyId() {
        return requiredCurrencyId;
    }

    public Salary getSalary() {
        return salary;
    }
}
