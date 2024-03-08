package com.example.tdd.chap07;

import com.example.tdd.chap07.common.WeakPasswordException;
import com.example.tdd.chap07.common.DupIdException;
import com.example.tdd.chap07.mock.MemoryUserRepository;
import com.example.tdd.chap07.mock.SpyEmailNotifier;
import com.example.tdd.chap07.mock.StubWeakPasswordChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserRegisterTest {

    private UserRegister userRegister;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeUserRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeUserRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        // given
        stubWeakPasswordChecker.setWeak(true);

        // when, then
        assertThatThrownBy(()->{
            userRegister.register("id", "pw", "email");
        }).isInstanceOf(WeakPasswordException.class).hasMessage("패스워드 강도가 약합니다.");
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExist() {
        // given
        final User user = new User("id", "pw", "email");
        fakeUserRepository.save(user);

        // when, then
        assertThatThrownBy(()->{
            userRegister.register("id", "pw", "email");
        }).isInstanceOf(DupIdException.class).hasMessage("이미 존재하는 사용자 ID입니다.");
    }

    @DisplayName("같은 ID가 없으면 회원가입에 성공한다.")
    @Test
    void noDupId_RegisterSuccess() {
        // given
        String id = "id";
        String pw = "pw";
        String email = "email";
        userRegister.register(id, pw, email);

        // when
        final User user = fakeUserRepository.getById(id);

        // then
        assertThat(user.getId()).isEqualTo(id);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @DisplayName("회원가입에 성공하면 이메일을 전송한다.")
    @Test
    void whenRegisterThenSendEmail() {
        // given
        String id = "id";
        String pw = "pw";
        String email = "email";
        userRegister.register(id, pw, email);

        // when
        boolean isSend = spyEmailNotifier.isSend();
        String toEmail = spyEmailNotifier.getEmail();

        // then
        assertThat(isSend).isTrue();
        assertThat(toEmail).isEqualTo(email);
    }
    
}
