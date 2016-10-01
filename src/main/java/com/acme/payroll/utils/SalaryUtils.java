package com.acme.payroll.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class SalaryUtils {
    public static final int GROUP_SIZE = 3;

    public static double convertSalary(double amount, double rate) {
        return rate * amount;
    }

    public static String formatValue(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.UK));
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(GROUP_SIZE);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(value);
    }

    public static double getMonthlyPayment(double yearlyAmount) {
        return yearlyAmount / 12f;
    }
}
