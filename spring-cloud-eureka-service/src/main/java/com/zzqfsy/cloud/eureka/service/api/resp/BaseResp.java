package com.zzqfsy.cloud.eureka.service.api.resp;

import java.io.Serializable;

/**
 * Created by john on 16-7-6.
 */
public class BaseResp<T> implements Serializable {

    private String code;

    private T result;

    private Integer errorType;

    private String errorMsg;

    private boolean isSuccess = true;

    public BaseResp() {
    }

    public BaseResp(String code, T result, Integer errorType, String errorMsg, boolean isSuccess) {
        this.code = code;
        this.result = result;
        this.errorType = errorType;
        this.errorMsg = errorMsg;
        this.isSuccess = isSuccess;
    }

    public static BaseResp create(Object result){
        return new BaseResp("0", result, 0, "正常", true);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
