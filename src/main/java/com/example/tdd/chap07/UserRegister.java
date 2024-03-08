package com.example.tdd.chap07;

import com.example.tdd.chap07.common.DupIdException;
import com.example.tdd.chap07.common.WeakPasswordException;

public class UserRegister {

    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;
    private final EmailNotifier emailNotifier;

    public UserRegister(final WeakPasswordChecker passwordChecker, final UserRepository userRepository, final EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(final String id, final String pw, final String email) {
        validatePassword(pw);
        User user = userRepository.getById(id);
        existUser(user);
        userRepository.save(new User(id, pw, email));
        emailNotifier.sendRegisterEmail(email);
    }

    private void existUser(final User user) {
        if (user != null) {
            throw new DupIdException("이미 존재하는 사용자 ID입니다.");
        }
    }

    private void validatePassword(final String pw) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException("패스워드 강도가 약합니다.");
        }
    }

}
