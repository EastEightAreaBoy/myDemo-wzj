package com.demo.security;

import lombok.Data;

/**
 * @author HP
 */
@Data
public class UserInfo {

    private Integer id;
    private String name;
    private String account;
    private String password;
    private Integer age;
    private String email;

}
