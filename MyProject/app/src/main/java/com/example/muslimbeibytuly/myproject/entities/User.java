package com.example.muslimbeibytuly.myproject.entities;

/**
 * Created by muslimbeibytuly on 12/1/17.
 */

public class User {

    private String email;
    public User(){
    }
    public User(String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
