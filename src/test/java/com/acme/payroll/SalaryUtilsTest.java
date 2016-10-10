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

    private BigDecimal getBigDecimal(double d) {
        return new BigDecimal(d).setScale(2, RoundingMode.HALF_UP);
    }
}
