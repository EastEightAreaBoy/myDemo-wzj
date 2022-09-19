package com;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author HP
 */
public class PasswordTest {

    @Test
    public void test01 () {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        //$2a$10$eTZwFG0bsEqg1qhuEFjJkuCBS/fOsfqTDOhnT4GTG3bVOlVV.Spg.
        System.out.println(encode);
    }

}
