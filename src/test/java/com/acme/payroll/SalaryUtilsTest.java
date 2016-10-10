package com.acme.payroll;


import com.acme.payroll.utils.SalaryUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class SalaryUtilsTest {
    @Test
    public void testSalaryConversion() {
        BigDecimal bg1 = SalaryUtils.convertSalary("10", "1");
        assertEquals(getBigDecimal(10), bg1);

        BigDecimal bg2 = SalaryUtils.convertSalary("10", "1.5");
        assertEquals(getBigDecimal(15), bg2);

        BigDecimal bg3 = SalaryUtils.convertSalary("2.3275", "2");
        assertEquals(getBigDecimal(4.66), bg3);
    }

    @Test
    public void testFormatSalary() {
        Locale l = Locale.US;

        String s1 = SalaryUtils.formatValue(getBigDecimal(1), l);
        assertEquals("1.00", s1);

        String s2 = SalaryUtils.formatValue(getBigDecimal(1000), l);
        assertEquals("1,000.00", s2);

        String s3 = SalaryUtils.formatValue(getBigDecimal(1.051), l);
        assertEquals("1.05", s3);

        String s4 = SalaryUtils.formatValue(getBigDecimal(1.056), l);
        assertEquals("1.06", s4);
    }

    @Test
    public void testMonthlyPayment() {
        BigDecimal bd1 = SalaryUtils.getMonthlyPayment(getBigDecimal(1000));
        assertEquals(getBigDecimal(83.33), bd1);

        BigDecimal bd2 = SalaryUtils.getMonthlyPayment(getBigDecimal(10));
        assertEquals(getBigDecimal(0.83), bd2);

        BigDecimal bd3 = SalaryUtils.getMonthlyPayment(getBigDecimal(0));
        assertEquals(getBigDecimal(0.00), bd3);

        BigDecimal bd4 = SalaryUtils.getMonthlyPayment(getBigDecimal(10154.5454));
        assertEquals(getBigDecimal(846.21), bd4);
    }

    private BigDecimal getBigDecimal(double d) {
        return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
    }
}
