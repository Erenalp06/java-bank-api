package com.teksen.bank_api.DTOs;
import java.util.Objects;

public class UserDetails {
    private Long userId;
    private String name;
    private String email;


    public UserDetails() {
    }

    public UserDetails(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public UserDetails name(String name) {
        setName(name);
        return this;
    }

    public UserDetails email(String email) {
        setEmail(email);
        return this;
    }    

    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
    
}
