package com.acme.payroll.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SalaryUtils {
    public static double convertSalary(double amount, double rate) {
        return rate * amount;
    }

    public static String formatSalary(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(amount);
    }
}
