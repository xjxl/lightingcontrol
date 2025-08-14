package com.acme.xxlightingcontrol.lib.net.http;

/**
 * @author jx9@msn.com
 */
public enum XResponseStatus {
    FAILURE(0, "失败"),
    SUCCESS(200, "成功"),
    UNKNOWN(2, "未知");

    private int code;
    private String msg;

    XResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "XResponseStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}