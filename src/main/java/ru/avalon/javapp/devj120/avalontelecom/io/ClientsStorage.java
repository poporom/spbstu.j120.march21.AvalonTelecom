package ru.avalon.javapp.devj120.avalontelecom.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;

public class ClientsStorage {
    private static final String FILE_NAME = "clients.dat";
    
    private static final ClientsStorage instance = new ClientsStorage();
    
    private final File dataFile;
    
    private ClientsStorage() {
        this.dataFile = new File(System.getProperty("user.dir"), FILE_NAME);
    }

    public File getDataFile() {
        return dataFile;
    }
    
    public List<ClientInfo> load() throws IOException, ClassNotFoundException {
        if(dataFile.exists()) {
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
                return (List<ClientInfo>) ois.readObject();
            }
        } else {
            return new ArrayList<>();
        }
    }
    
    public void save(List<ClientInfo> clients) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(clients);
        }
    }

    public static ClientsStorage getInstance() {
        return instance;
    }
}
