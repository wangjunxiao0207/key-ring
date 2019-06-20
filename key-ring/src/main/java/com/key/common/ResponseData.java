package com.key.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


// 不序列化值为null的属性
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private String msg;
    private T data;

    private ResponseData(int status) {
        this.status = status;
    }

    private ResponseData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseData(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ResponseData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("status", status);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    public static <T> ResponseData<T> success() {
        return new ResponseData<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ResponseData<T> success(String msg, T data) {
        return new ResponseData<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResponseData<T> successMsg(String msg) {
        ResponseData result = new ResponseData<T>(ResponseCode.SUCCESS.getCode());
        result.msg = msg;
        return result;
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData result = new ResponseData<T>(ResponseCode.SUCCESS.getCode());
        result.data = data;
        return result;
    }

    public static <T> ResponseData<T> error() {
        ResponseData result = new ResponseData<T>(ResponseCode.ERROR.getCode());
        result.msg = ResponseCode.ERROR.getDesc();
        return result;
    }

    public static <T> ResponseData<T> error(String errorMsg) {
        ResponseData result = new ResponseData<T>(ResponseCode.ERROR.getCode());
        result.msg = errorMsg;
        return result;
    }

    public static <T> ResponseData<T> error(int errorCode, String errorMsg) {
        ResponseData result = new ResponseData<T>(errorCode);
        result.msg = errorMsg;
        return result;
    }
}
