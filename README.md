# Browser History Simulator

A Java-based console application that replicates how modern browsers (Chrome, Firefox) manage browsing history — implemented using custom data structures rather than relying on Java's built-in Collections.

## Overview

This project demonstrates how a **Doubly Linked List**, a **HashMap**, and a **Stack** can work together to build an efficient, real-world navigation system. It supports visiting pages, moving backward/forward, searching history, closing/reopening tabs, and clearing history — all with the same behavior as a real browser, including the rule that visiting a new page after going back discards all forward history.

## Features

- **Visit** a new page (adds to history, wipes forward history if applicable)
- **Back / Forward** navigation in O(1) time
- **Refresh** the current page
- **Search** previously visited URLs in O(1) time via HashMap
- **View full browsing history** with the current page marked
- **Clear** all history
- **Close current tab** and **reopen last closed tab** (LIFO order via Stack)
- Custom exception handling for invalid operations (e.g., going back with no previous page)

## Data Structures Used

| Structure | Purpose |
|---|---|
| Custom Doubly Linked List | Stores browsing history; enables O(1) back/forward navigation |
| HashMap\<String, PageNode\> | Maps URLs to nodes for O(1) search |
| Stack\<PageNode\> | Tracks recently closed tabs in LIFO order |

## Project Structure
src/com/browserhistory/
├── model/
│ └── PageNode.java # Represents a single webpage (url, title, timestamp, prev/next links)
├── core/
│ ├── BrowserHistory.java # Core logic: visit, back, forward, search, close/reopen tabs, etc.
│ └── exceptions/
│ ├── NoPreviousPageException.java
│ ├── NoForwardPageException.java
│ └── NoClosedTabsException.java
├── ui/
│ └── CLIInterface.java # Console menu, user interaction
└── Main.java # Entry point

## How to Run

1. Clone the repository: git clone https://github.com/yourusername/browser-history-simulator.git
2. Open the project in IntelliJ IDEA (or any Java IDE)
3. Run `Main.java`
4. Follow the on-screen menu to interact with the simulator

**Requirements:** JDK 17 or higher

## Example Usage
===== Browser History Simulator =====

1. Visit new page
2. Back
3. Forward
4. Refresh
5. Search
6. Show full history
7. Clear history
8. Close current tab
9. Reopen last closed tab
10. Exit
Enter your choice: 1
Enter URL: google.com
Enter page title: Google
Visited: Google (google.com)

## Key Design Decisions

- **Doubly Linked List over ArrayList**: back/forward navigation needs O(1) movement in both directions without shifting elements — an array-based structure would require costly shifts or re-indexing.
- **HashMap alongside the linked list**: searching a linked list directly is O(n); maintaining a parallel HashMap of URL → node brings search down to O(1), at the cost of keeping both structures in sync during every visit/close operation.
- **Stack for closed tabs**: reopening tabs is inherently a Last-In-First-Out operation — the most recently closed tab should always be the first one restored, which maps naturally onto a stack.
- **Custom exceptions over generic ones**: `NoPreviousPageException`, `NoForwardPageException`, and `NoClosedTabsException` make error handling explicit and let the CLI layer respond with meaningful messages instead of guessing what went wrong.

## Future Enhancements

- Browsing statistics (most visited pages, total visits)
- Session persistence (save/load history to a file)
- Multiple browser tabs, each with independent history
- Java Swing GUI

## Author

Mukul Mishra