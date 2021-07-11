package ru.avalon.javapp.devj120.avalontelecom;

import ru.avalon.javapp.devj120.avalontelecom.controllers.ClientsController;
import ru.avalon.javapp.devj120.avalontelecom.io.ClientsStorage;
import ru.avalon.javapp.devj120.avalontelecom.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        ClientsController ccontr = new ClientsController();
        try {
            ccontr.loadData();
        } catch (Exception ex) {
            System.out.println("Error reading " 
                    + ClientsStorage.getInstance().getDataFile() 
                    + ": " + ex.getMessage());
            return;
        }
        
        MainFrame mf = new MainFrame(ccontr);
        mf.setVisible(true);
    }
}
