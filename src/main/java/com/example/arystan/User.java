package com.example.arystan;

public abstract class User {
    private String name;
    private String surname;
    private String who;
    private String username;
    private String password;

    public User() {
    }

    public User(String name, String surname, String who, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.who = who;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
