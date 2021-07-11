package ru.avalon.javapp.devj120.avalontelecom.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.avalon.javapp.devj120.avalontelecom.io.ClientsStorage;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.ComClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.IndClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.PhoneNumber;

public class ClientsController {
    private List<ClientInfo> clients;
    private Set<PhoneNumber> numbers;
    
    public void add(PhoneNumber number, String name, String address, String dirName, String contactName) {
        if(numbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        clients.add(new ComClientInfo(number, name, address, dirName, contactName));
        numbers.add(number);
    }
    
    public void add(PhoneNumber number, String name, String address, Date bithDate) {
        if(numbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        clients.add(new IndClientInfo(number, name, address, bithDate));
        numbers.add(number);
    }
    
    
    
    public void remove(ClientInfo clientInfo) {
        clients.remove(clientInfo);
        numbers.remove(clientInfo.getPhoneNumber());
    }
    
    public int getClientsCount() {
        return clients.size();
    }
    
    public ClientInfo getClientInfo(int index) {
        return clients.get(index);
    }
    
    public IndClientInfo getIndClientInfo(int index) {
        return (IndClientInfo) clients.get(index);
    }
    
    public ComClientInfo getComClientInfo(int index) {
        return (ComClientInfo) clients.get(index);
    }
    
    public void loadData() throws Exception {
        this.clients = ClientsStorage.getInstance().load();
        this.numbers = new HashSet<>();
        for (ClientInfo client : clients) {
            numbers.add(client.getPhoneNumber());
        }
    }
    
    public void saveData() throws IOException {
        ClientsStorage.getInstance().save(clients);
    }
}
