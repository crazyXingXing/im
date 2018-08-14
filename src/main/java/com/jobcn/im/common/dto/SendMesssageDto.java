package com.jobcn.im.common.dto;

/**
 * Web发送消息时传给服务器的的DTO
 */
public class SendMesssageDto {
    /**
     * 发送给用户
     */
    private String toUser;
    /**
     * 消息体
     */
    private String message;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}