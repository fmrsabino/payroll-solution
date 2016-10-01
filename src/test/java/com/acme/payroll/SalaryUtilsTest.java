package com.acme.payroll;


import com.acme.payroll.utils.SalaryUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SalaryUtilsTest {
    @Test
    public void testFormatSalary() {
        double v1 = 1f;
        String s1 = SalaryUtils.formatSalary(v1);
        assertEquals(s1, "1.00");

        double v2 = 1000f;
        String s2 = SalaryUtils.formatSalary(v2);
        assertEquals(s2, "1,000.00");

        double v3 = 1.051f;
        String s3 = SalaryUtils.formatSalary(v3);
        assertEquals(s3, "1.05");

        double v4 = 1.056f;
        String s4 = SalaryUtils.formatSalary(v4);
        assertEquals(s4, "1.06");
    }
}
