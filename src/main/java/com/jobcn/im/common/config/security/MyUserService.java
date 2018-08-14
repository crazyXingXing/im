package com.jobcn.im.common.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 根据用户名从数据库中取数据后，交给MyAuthenticationProvider做校验
 * 因为用户校验在web已完成，所以这里不校验，直接返回。
 */
@Component
public class MyUserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User detail = null;
        //判断username是否为null
        if(username != null){
            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_MODELER"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_ANALYST"));
            //可以加入其他判断逻辑，以及根据username获取密码的方法。

            //由于是Demo，就直接将密码写死为"w5566"，权限直接设置成"USER"
            detail = new User(username, "w5566", authorities);
        }

        return detail;
    }
}