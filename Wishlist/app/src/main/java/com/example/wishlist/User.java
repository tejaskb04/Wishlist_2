package com.example.wishlist;

public class User {
    private String email;
    private String password;
    public User() {

    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setEmail(String s) {
        this.email = s;
    }
    public void setPassword(String s) {
        this.password = s;
    }
}
