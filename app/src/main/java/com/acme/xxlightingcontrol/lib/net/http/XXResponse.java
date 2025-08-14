package com.acme.xxlightingcontrol.lib.net.http;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jx9@msn.com
 */
public class XXResponse {
    private Integer code;

    @JsonProperty(value = "msg")
    private String msg;

    @JsonProperty(value = "msgText")
    private String msgText;

    public XXResponse() {

    }

    public XXResponse(Integer code, String msgText) {
        this.code = code;
        this.msgText = msgText;
    }

    public XXResponse(String msg, String msgText) {
        this.msg = msg;
        this.msgText = msgText;
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

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }
}