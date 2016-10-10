package com.acme.payroll;

import com.acme.payroll.data.SimpleStorage;
import com.acme.payroll.data.model.Currency;
import com.acme.payroll.data.model.Employee;
import com.acme.payroll.data.model.Salary;
import com.acme.payroll.exception.CurrencyNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.acme.payroll.TestUtils.inputStreamToString;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PayrollTest {

    private static final String EMPLOYEES_FILE = readJSON("/employees.json");
    private static final String RATES_FILE = readJSON("/exchange_rates.json");

    private Payroll payroll;

    @Mock SimpleStorage simpleStorage;

    @Before
    public void setup() {
        payroll =  new Payroll(simpleStorage);
    }

    @After
    public void teardown() {
    }

    @Test
    public void testInputFile() {
        assertNotNull(EMPLOYEES_FILE);
        assertTrue(EMPLOYEES_FILE.contains("Mary"));
    }

    @Test public void testPayrollAcceptance() {
        List<Salary> salaries = payroll.getPayroll(EMPLOYEES_FILE);
        assertNotNull(salaries);
        assertEquals(6, salaries.size());

        for (Salary salary : salaries) {
            System.out.println(salary.toString());
        }
    }

    @Test
    public void testPayrollNull() {
        List<Salary> salaries = payroll.getPayroll(null);
        assertEquals(salaries.size(), 0);
        verify(simpleStorage, never()).setEmployees(anyListOf(Employee.class));
    }

    @Test
    public void setEmployeesCallsStorage() {
        payroll.setEmployees(EMPLOYEES_FILE);
        verify(simpleStorage).setEmployees(anyListOf(Employee.class));
    }

    @Test
    public void setEmployeesNull() {
        payroll.setEmployees(null);
        verify(simpleStorage, never()).setEmployees(anyListOf(Employee.class));
    }

    @Test
    public void setCurrenciesCallsStorage() {
        payroll.setCurrencies(RATES_FILE);
        verify(simpleStorage).setCurrencies(anyListOf(Currency.class));
    }

    @Test
    public void setCurrenciesNull() {
        payroll.setCurrencies(null);
        verify(simpleStorage, never()).setCurrencies(anyListOf(Currency.class));
    }

    @Test
    public void getEmployeesCallsStorage() {
        List<Employee> employees = new ArrayList<>();
        Employee e = getMockEmployee();
        employees.add(e);

        when(simpleStorage.getEmployees()).thenReturn(employees);
        Collection<Employee> employeeCollection = payroll.getEmployees();
        verify(simpleStorage).getEmployees();
        assertTrue(employeeCollection.contains(e));
    }

    @Test
    public void getCurrencyCallsStorage() throws CurrencyNotFoundException {
        Currency c = getMockCurrency();

        when(simpleStorage.getCurrency(c.getCurrencyId())).thenReturn(c);
        Currency result = payroll.getCurrency(c.getCurrencyId());
        assertEquals(result, c);
    }

    @Test(expected = CurrencyNotFoundException.class)
    public void getCurrencyThrowsException() throws CurrencyNotFoundException {
        when(simpleStorage.getCurrency(anyString())).thenReturn(null);
        payroll.getCurrency(anyString());
    }

    private static String readJSON(String path) {
        return inputStreamToString(PayrollTest.class.getResourceAsStream(path));
    }

    private Employee getMockEmployee() {
        String firstName1 = "MockFirstName";
        String secondName1 = "MockSecondName";
        Salary salary1 = new Salary("MockCurrency", "10", "MockDisplay");
        return new Employee(firstName1, secondName1, "MockSection", "MockCurrency", salary1);
    }

    private Currency getMockCurrency() {
        return new Currency("MockId", "1");
    }
}
