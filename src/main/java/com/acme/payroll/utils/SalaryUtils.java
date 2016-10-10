package com.acme.payroll.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class SalaryUtils {
    private static final int GROUP_SIZE = 3;
    private static final int SCALE_SIZE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static BigDecimal convertSalary(String amount, String rate) {
        return new BigDecimal(amount)
                .multiply(new BigDecimal(rate))
                .setScale(SCALE_SIZE, ROUNDING_MODE);
    }

    public static String formatValue(BigDecimal value, Locale locale) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(locale));
        decimalFormat.setGroupingSize(GROUP_SIZE);
        decimalFormat.setGroupingUsed(true);
        return decimalFormat.format(value.doubleValue());
    }

    public static BigDecimal getMonthlyPayment(BigDecimal yearlyAmount) {
        return yearlyAmount.divide(new BigDecimal(12), SCALE_SIZE, ROUNDING_MODE);
    }
}
