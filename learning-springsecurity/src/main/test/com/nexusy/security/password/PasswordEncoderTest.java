package com.nexusy.security.password;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lanhuidong
 * @since 2016-04-30
 */
public class PasswordEncoderTest {

    @Test
    public void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("111111");
        System.out.println(encodedPassword);
        System.out.println(passwordEncoder.matches("111111", encodedPassword));
    }
}
