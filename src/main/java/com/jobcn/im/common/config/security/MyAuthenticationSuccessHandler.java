package com.jobcn.im.common.config.security;

import com.jobcn.im.common.service.OpenfireService;
import com.jobcn.im.common.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录校验成功的handler
 * 这里登录校验成功后跳转到/chat
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    OpenfireService openfireService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user=(User)authentication.getPrincipal();
        String username =user.getUsername();
        if (openfireService==null)
            openfireService= SpringUtils.getBean(OpenfireService.class);
        //openfire上线
        openfireService.online(username);
        //跳转chat
        response.sendRedirect("/chat");
    }
}
