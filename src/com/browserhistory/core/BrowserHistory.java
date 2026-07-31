package com.browserhistory.core;

import com.browserhistory.core.exceptions.NoPreviousPageException;
import com.browserhistory.core.exceptions.NoForwardPageException;

import com.browserhistory.model.PageNode;
import java.util.HashMap;

public class BrowserHistory {

    private PageNode head;
    private PageNode current;
    private int size;
    private HashMap<String, PageNode> urlMap;

    public BrowserHistory() {
        this.head = null;
        this.current = null;
        this.size = 0;
        this.urlMap = new HashMap<>();
    }

    public void visit(String url, String title) {
        // Case 1: very first page ever visited
        if (current == null) {
            PageNode newNode = new PageNode(url, title);
            head = newNode;
            current = newNode;
            urlMap.put(url, newNode);
            size++;
            return;
        }

        // Case 2: if there is forward history, wipe it first
        if (current.getNext() != null) {
            PageNode temp = current.getNext();
            while (temp != null) {
                urlMap.remove(temp.getUrl());
                temp = temp.getNext();
                size--;
            }
            current.setNext(null);
        }

        // Case 3: create and link the new node
        PageNode newNode = new PageNode(url, title);
        newNode.setPrev(current);
        current.setNext(newNode);
        current = newNode;
        urlMap.put(url, newNode);
        size++;
    }

    public PageNode back() {
        if (current.getPrev() == null) {
            throw new NoPreviousPageException("No previous page in history");
        }
        current = current.getPrev();
        return current;
    }

    public PageNode forward() {
        if (current.getNext() == null) {
            throw new NoForwardPageException("No forward page in history");
        }
        current = current.getNext();
        return current;
    }

    public PageNode search(String url) {
        return urlMap.get(url);
    }

    public void refresh() {
        if (current == null) {
            throw new RuntimeException("No page currently loaded");
        }
        System.out.println("Refreshing: " + current);
    }

    public void showHistory() {
        if (head == null) {
            System.out.println("History is empty.");
            return;
        }
        PageNode temp = head;
        System.out.println("---- Browsing History ----");
        while (temp != null) {
            if (temp == current) {
                System.out.println("-> " + temp + "  [CURRENT]");
            } else {
                System.out.println("   " + temp);
            }
            temp = temp.getNext();
        }
        System.out.println("---------------------------");
    }

    public void clearHistory() {
        head = null;
        current = null;
        size = 0;
        urlMap.clear();
        System.out.println("History cleared.");
    }

    // Temporary helper method just for testing right now
    public PageNode getCurrent() {
        return current;
    }
}