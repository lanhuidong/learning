package com.nexusy.oauth.server.service;

import com.nexusy.oauth.server.vo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author lan
 * @since 2016-05-19
 */
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername("user");
        user.setPassword("111111");
        return user;
    }
}
