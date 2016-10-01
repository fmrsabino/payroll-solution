package com.acme.payroll.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SalaryUtils {
    public static final int GROUP_SIZE = 3;

    public static double convertSalary(double amount, double rate) {
        return rate * amount;
    }

    public static String formatSalary(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(GROUP_SIZE);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(amount);
    }
}
