package com.acme.xxlightingcontrol.lib.net.http;

/**
 * @author jx9@msn.com
 */
public class XPagination {

    private int currentPageIndex;

    private int endRecordIndex;

    private int pageSize;

    private int startRecordIndex;

    private int totalItemCount;

    private int totalPageCount;

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public int getEndRecordIndex() {
        return endRecordIndex;
    }

    public void setEndRecordIndex(int endRecordIndex) {
        this.endRecordIndex = endRecordIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRecordIndex() {
        return startRecordIndex;
    }

    public void setStartRecordIndex(int startRecordIndex) {
        this.startRecordIndex = startRecordIndex;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
