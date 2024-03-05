package com.example.tdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String input) {
        if (input == null || input == "") {
            return PasswordStrength.INVALID;
        }

        int metCounts = getMetCriteriaCounts(input);

        if (metCounts <= 1) {
            return PasswordStrength.WEAK;
        }
        if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String input) {
        int metCounts = 0;
        if (input.length() >= 8) {
            metCounts++;
        }
        if (meetsContainingNumberCriteria(input)) {
            metCounts++;
        }
        if (meetsContainingUppercaseCriteria(input)) {
            metCounts++;
        }
        return metCounts;
    }

    private boolean meetsContainingNumberCriteria(String input) {
        for (char ch : input.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String input) {
        for (char ch : input.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

}
