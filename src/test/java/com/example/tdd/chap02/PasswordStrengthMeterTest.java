package com.example.tdd.chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 패스워드 등급 강도 : 모두만족(강함), 2개(보통), 1개이하(약함)
 * 1. 패스워드는 8글자 이상
 * 2. 숫자 포함
 * 3. 영어 대문자 포함
 */
public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야한다.")
    @Test
    void strong() {
        PasswordStrength result = meter.meter("ab12!@AB");
        assertThat(result).isEqualTo(PasswordStrength.STRONG);

        PasswordStrength result2 = meter.meter("abc1!Add");
        assertThat(result2).isEqualTo(PasswordStrength.STRONG);
    }

    @DisplayName("길이가 8글자 미만이고 나머지 조건을 충족하면 보통이다.")
    @Test
    void short_password_normal() {
        PasswordStrength result = meter.meter("ab12!@A");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);

        PasswordStrength result2 = meter.meter("Ab12!c");
        assertThat(result2).isEqualTo(PasswordStrength.NORMAL);
    }

    @DisplayName("숫자를 포함하지 않고 나머지 조건을 충족하면 보통이다.")
    @Test
    void non_number_normal() {
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @DisplayName("테스트 코드 중복 방지를 위한 파라미터라이즈 테스트")
    @ParameterizedTest
    @CsvSource({"ab12!@AB,STRONG", "abc1!Add,STRONG", "ab12!@A,NORMAL", "Ab12!c,NORMAL", "ab!@ABqwer,NORMAL"})
    void strengthTest(String input, PasswordStrength expect){
        PasswordStrength result = meter.meter(input);
        assertThat(result).isEqualTo(expect);
    }

    @DisplayName("값이 null인 경우 유효하지 않다.")
    @Test
    void null_input() {
        PasswordStrength result2 = meter.meter(null);
        assertThat(result2).isEqualTo(PasswordStrength.INVALID);
    }

    @DisplayName("값이 null인 경우 유효하지 않다.")
    @Test
    void empty_input() {
        PasswordStrength result2 = meter.meter("");
        assertThat(result2).isEqualTo(PasswordStrength.INVALID);
    }

    @DisplayName("대문자를 포함하지 않고 나머지 조건을 충족하면 보통이다.")
    @Test
    void except_for_uppercase() {
        PasswordStrength result = meter.meter("ab12!@df");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }
}
