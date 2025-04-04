package com.stefanini.app.entity;

import jakarta.persistence.Entity;

@Entity
public class Location {

    private String Name;
    private String address;


    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
