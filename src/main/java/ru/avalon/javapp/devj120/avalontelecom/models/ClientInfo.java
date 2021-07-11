package ru.avalon.javapp.devj120.avalontelecom.models;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class ClientInfo implements Serializable {
    private final PhoneNumber phoneNumber;
    private final LocalDate regDate;
    private String name;
    private String address;

    public ClientInfo(PhoneNumber phoneNumber, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.now();
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getRegDate() {
        return regDate;
    }
    
    public abstract String getDetails(); 
    
}
