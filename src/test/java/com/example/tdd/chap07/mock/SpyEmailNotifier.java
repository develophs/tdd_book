package com.example.tdd.chap07.mock;

import com.example.tdd.chap07.EmailNotifier;

public class SpyEmailNotifier implements EmailNotifier {

    private boolean send;
    private String toEmail;

    public boolean isSend() {
        return send;
    }

    public String getEmail() {
        return toEmail;
    }

    @Override
    public void sendRegisterEmail(final String email) {
        this.send = true;
        this.toEmail = email;
    }
}
