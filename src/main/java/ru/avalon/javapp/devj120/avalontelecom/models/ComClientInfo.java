/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj120.avalontelecom.models;

/**
 *
 * @author rompop
 */
public class ComClientInfo extends ClientInfo{
    private String dirName;
    private String contactName;

    public ComClientInfo(PhoneNumber phoneNumber, String name, String address, String dirName, String contactName) {
        super(phoneNumber, name, address);
        
        this.contactName = contactName;
        this.dirName = dirName;
        
    }

    public String getDirName() {
        return dirName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    
    @Override
    public String getDetails() {
        return "Dir: " + dirName + ", contact: "+ contactName; 
       }
    
    
}
