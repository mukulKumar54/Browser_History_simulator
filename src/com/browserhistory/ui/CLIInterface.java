package com.browserhistory.ui;

import com.browserhistory.core.BrowserHistory;
import com.browserhistory.core.exceptions.NoForwardPageException;
import com.browserhistory.core.exceptions.NoPreviousPageException;
import com.browserhistory.model.PageNode;

import java.util.Scanner;

public class CLIInterface {

    private BrowserHistory history;
    private Scanner scanner;

    public CLIInterface() {
        this.history = new BrowserHistory();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleVisit();
                    break;
                case "2":
                    handleBack();
                    break;
                case "3":
                    handleForward();
                    break;
                case "4":
                    handleRefresh();
                    break;
                case "5":
                    handleSearch();
                    break;
                case "6":
                    history.showHistory();
                    break;
                case "7":
                    history.clearHistory();
                    break;
                case "8":
                    running = false;
                    System.out.println("Exiting Browser History Simulator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== Browser History Simulator =====");
        System.out.println("1. Visit new page");
        System.out.println("2. Back");
        System.out.println("3. Forward");
        System.out.println("4. Refresh");
        System.out.println("5. Search");
        System.out.println("6. Show full history");
        System.out.println("7. Clear history");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleVisit() {
        System.out.print("Enter URL: ");
        String url = scanner.nextLine().trim();
        System.out.print("Enter page title: ");
        String title = scanner.nextLine().trim();
        history.visit(url, title);
        System.out.println("Visited: " + title + " (" + url + ")");
    }

    private void handleBack() {
        try {
            PageNode result = history.back();
            System.out.println("Moved back to: " + result);
        } catch (NoPreviousPageException e) {
            System.out.println("Cannot go back: " + e.getMessage());
        }
    }

    private void handleForward() {
        try {
            PageNode result = history.forward();
            System.out.println("Moved forward to: " + result);
        } catch (NoForwardPageException e) {
            System.out.println("Cannot go forward: " + e.getMessage());
        }
    }

    private void handleRefresh() {
        try {
            history.refresh();
        } catch (RuntimeException e) {
            System.out.println("Cannot refresh: " + e.getMessage());
        }
    }

    private void handleSearch() {
        System.out.print("Enter URL to search: ");
        String url = scanner.nextLine().trim();
        PageNode result = history.search(url);
        if (result != null) {
            System.out.println("Found: " + result);
        } else {
            System.out.println("URL not found in history.");
        }
    }
}