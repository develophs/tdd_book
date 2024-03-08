package com.example.tdd.chap07.mock;

import com.example.tdd.chap07.WeakPasswordChecker;

public class StubWeakPasswordChecker implements WeakPasswordChecker {

    private boolean weak;

    public void setWeak(final boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(final String password) {
        return weak;
    }
}
