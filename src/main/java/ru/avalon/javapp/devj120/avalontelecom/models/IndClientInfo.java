/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj120.avalontelecom.models;

import java.util.Date;

/**
 *
 * @author rompop
 */
public class IndClientInfo extends ClientInfo{
    private Date birthDate;

    public IndClientInfo(PhoneNumber phoneNumber, String name, String address, Date bithDate) {
        super(phoneNumber, name, address);
        
        this.birthDate = bithDate;
        
    }

    public String getBirthDate() {
        return birthDate.toString();
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    
    @Override
    public String getDetails() {
        return "Birthdate: " + birthDate; 
       }
    
    
    
}
