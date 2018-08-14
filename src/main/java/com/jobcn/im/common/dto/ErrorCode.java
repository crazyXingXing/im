package com.jobcn.im.common.dto;

public interface ErrorCode {
    int SUCCESS = 0;    // 请求成功

    int USERNAME_ERROR = 1000;    // 用户名错误
    int PASSWORD_ERROR = 1001;    // 用户密码错误

    int RESOURCE_EXPIRE = 2000;    // 资源过期
    int RESOURCE_NOTFOUND = 2001;    // 资源不存在

}
