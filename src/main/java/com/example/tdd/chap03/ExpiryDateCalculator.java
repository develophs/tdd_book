package com.example.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        long addedMonth = payData.getPayAmount() >= 100_000 ? 12 : payData.getPayAmount() / 10_000;

        if (addedMonth == 12) {
            addedMonth += (payData.getPayAmount() - 100_000) / 10_000 == 10 ? 12 : (payData.getPayAmount() - 100_000) / 10_000;
        }

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonth);
        }
        return payData.getBillingDate().plusMonths(addedMonth);
    }

    private LocalDate expiryDateUsingFirstBillingDate(final PayData payData, final long addedMonth) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonth);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();

        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }
        return candidateExp;
    }
}
