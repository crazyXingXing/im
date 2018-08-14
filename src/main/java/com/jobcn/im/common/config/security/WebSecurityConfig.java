package com.jobcn.im.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()//根路径和/login路径不拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //2登陆页面路径为/login
                .defaultSuccessUrl("/chat") //3登陆成功转向chat页面
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessHandler(new MyLogoutSuccessHandler())
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    //4在内存中配置用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //登录校验
        auth.authenticationProvider(myAuthenticationProvider);
        //下面的是写的固定校验
//        auth.inMemoryAuthentication()
//                .withUser("winson").password("w5566").roles("USER")
//                .and()
//                .withUser("tom").password("w5566").roles("USER")
//                .and()
//                .withUser("mary").password("w5566").roles("USER");
    }

    //5忽略静态资源的拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}