package com.example.tdd.chap02;

public class PasswordStrengthMeter {

    public PasswordStrength meter(String input) {
        if (input == null || input == "") {
            return PasswordStrength.INVALID;
        }

        if (input.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean containsNum = meetsContainingNumberCriteria(input);
        if(!containsNum) {
            return PasswordStrength.NORMAL;
        }

        boolean containsUpp = meetsContainingUppercaseCriteria(input);
        if(!containsUpp) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
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
