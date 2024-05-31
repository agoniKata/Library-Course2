package com.example.arystan;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String who;
    private String quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Book() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Book(String title, String author, String isbn, String who, String quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.who = who;
        this.quantity = quantity;
    }
}
