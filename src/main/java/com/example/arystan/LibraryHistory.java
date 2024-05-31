package com.example.arystan;

public class LibraryHistory {
    private String name;
    private String title;
    private String quantity;

    public LibraryHistory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public LibraryHistory(String name, String title, String quantity) {
        this.name = name;
        this.title = title;
        this.quantity = quantity;
    }
}
