package com.example.notification.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;


public class Professor {
    
    private Long id;
    private String name;
    private String email;

    // getters/setters
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
