package com.browserhistory.model;

public class PageNode {
    private String url;
    private String title;
    private long timestamp;
    private PageNode prev;
    private PageNode next;

    public PageNode(String url, String title) {
        this.url = url;
        this.title = title;
        this.timestamp = System.currentTimeMillis();
        this.prev = null;
        this.next = null;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public PageNode getPrev() {
        return prev;
    }

    public PageNode getNext() {
        return next;
    }

    public void setPrev(PageNode prev) {
        this.prev = prev;
    }

    public void setNext(PageNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return title + " (" + url + ")";
    }
}