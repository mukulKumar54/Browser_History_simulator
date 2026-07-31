package com.browserhistory;

import com.browserhistory.core.BrowserHistory;
import com.browserhistory.model.PageNode;

import com.browserhistory.core.exceptions.NoForwardPageException;
import com.browserhistory.core.exceptions.NoPreviousPageException;

public class Main {
    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();

        history.visit("google.com", "Google");
        history.visit("youtube.com", "YouTube");
        history.visit("wikipedia.org", "Wikipedia");

        history.showHistory();

        history.back();
        System.out.println("\nAfter back(): " + history.getCurrent());
        history.showHistory();

        // test search
        PageNode found = history.search("google.com");
        System.out.println("\nSearch google.com -> " + found);

        PageNode notFound = history.search("reddit.com");
        System.out.println("Search reddit.com -> " + notFound);

        // test refresh
        history.refresh();

        // visit new page after going back -> should wipe forward history
        history.visit("github.com", "GitHub");
        System.out.println("\nAfter visiting new page:");
        history.showHistory();

        // wikipedia.org should now be gone from search since it was wiped
        PageNode wiped = history.search("wikipedia.org");
        System.out.println("\nSearch wikipedia.org (should be null, was wiped) -> " + wiped);

        // test clearHistory
        history.clearHistory();
        history.showHistory();

        // Test custom exceptions
        BrowserHistory testHistory = new BrowserHistory();
        testHistory.visit("test1.com", "Test1");

        try {
            testHistory.back();
        } catch (NoPreviousPageException e) {
            System.out.println("\nCaught NoPreviousPageException: " + e.getMessage());
        }

        try {
            testHistory.forward();
        } catch (NoForwardPageException e) {
            System.out.println("Caught NoForwardPageException: " + e.getMessage());
        }
    }
}