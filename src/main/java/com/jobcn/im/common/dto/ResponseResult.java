package com.jobcn.im.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * RESTFul API 返回封装
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {

    private boolean success;
    private String message;
    private T data;
    private int errorCode;

    /**
     * 请求成功的返回
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(String message,T data){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(message);
        result.setErrorCode(ErrorCode.SUCCESS);
        return result;
    }

    /**
     * 请求失败的返回
     * @param message
     * @param data
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(String message,T data,int errorCode){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setData(data);
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setMessage(message);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseResult() {
    }
}
