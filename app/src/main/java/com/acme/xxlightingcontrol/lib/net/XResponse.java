package com.acme.xxlightingcontrol.lib.net;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jx9@msn.com
 */
public class XResponse<T> {

    public static String DEFAULT = "DEFAULT";

    private Integer code;

    @JsonProperty(value = "message")
    private String msg;

    private T data;

    public XResponse() {

    }

    public XResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public XResponse(Integer code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}