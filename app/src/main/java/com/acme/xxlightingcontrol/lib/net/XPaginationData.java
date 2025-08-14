package com.acme.xxlightingcontrol.lib.net;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public class XPaginationData<T> {

    private T data;

    private XPagination pagination;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public XPagination getPagination() {
        return pagination;
    }

    public void setPagination(XPagination pagination) {
        this.pagination = pagination;
    }
}