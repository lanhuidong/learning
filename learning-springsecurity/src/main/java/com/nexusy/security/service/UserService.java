package com.nexusy.security.service;

import com.nexusy.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author lanhuidong
 * @since 2016-04-24
 */
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        if ("user".equals(username)) {
            user.setPassword("111111");
        } else if ("admin".equals(username)) {
            user.setPassword("123456");
        } else {
            user = null;
        }
        return user;
    }
}
