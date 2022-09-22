package com.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author HP
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("root",
                "$2a$10$ZZMRvr0JbpGwp8xYuJCNleGuMHDFoCuqA8O5THqrn.Mb9R7HOjHUC",
                AuthorityUtils.createAuthorityList("admin", "test"));
    }
}
