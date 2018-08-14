package com.jobcn.im.common.config.security;

import com.jobcn.im.common.service.OpenfireService;
import com.jobcn.im.common.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 登出的handler
 * 这里登出后下线，跳转到/login
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    OpenfireService openfireService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user=(User)authentication.getPrincipal();
        String username =user.getUsername();
        if (openfireService==null)
            openfireService= SpringUtils.getBean(OpenfireService.class);
        //openfire下线
        openfireService.offline(username);
        //跳转回登录页面
        response.sendRedirect("/login");
    }
}
