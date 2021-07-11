package ru.avalon.javapp.devj120.avalontelecom.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import ru.avalon.javapp.devj120.avalontelecom.controllers.ClientsController;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.PhoneNumber;

public class ClientsTableModel implements TableModel {
    private final String[] columnHeaders = new String[] {
        "Phone number",
        "Registration date",
        "Client name",
        "Client address",
        "Details"
    };
    
    private final ClientsController controller;
    private final List<TableModelListener> modelListeners
            = new ArrayList<>();

    public ClientsTableModel(ClientsController controller) {
        this.controller = controller;
    }
    
    @Override
    public int getRowCount() {
        return controller.getClientsCount();
    }

    @Override
    public int getColumnCount() {
        return columnHeaders.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnHeaders[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        ClientInfo ci = controller.getClientInfo(row);
        switch(col) {
            case 0: return ci.getPhoneNumber().toString();
            case 1: return ci.getRegDate().toString();
            case 2: return ci.getName();
            case 3: return ci.getAddress();
            case 4: return ci.getDetails();
            default: throw new Error("Unreachable place.");
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        /* Nothing to do, since isCellEditable is always false. */
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
        modelListeners.add(tl);
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
        modelListeners.remove(tl);
    }
    
    public void clientAdded(PhoneNumber number, String name, String address, 
            String dirName, String contactName) {
        controller.add(number, name, address, dirName, contactName);
        int rowNdx = controller.getClientsCount() - 1;
        fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
    }
    
    public void clientAdded(PhoneNumber number, String name, String address, Date birthDate) {
        controller.add(number, name, address, birthDate);
        int rowNdx = controller.getClientsCount() - 1;
        fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
    }
    
    
    public void clientChanged(int ndx) {
        fireTableModelEvent(ndx, TableModelEvent.UPDATE);
    }
    
    public void clientDeleted(int ndx) {
        controller.remove(controller.getClientInfo(ndx));
        fireTableModelEvent(ndx, TableModelEvent.DELETE);
    }
    
    private void fireTableModelEvent(int rowNdx, int evtType) {
        TableModelEvent tme = new TableModelEvent(this, rowNdx, rowNdx, 
                TableModelEvent.ALL_COLUMNS, evtType);
        for (TableModelListener l : modelListeners) {
            l.tableChanged(tme);
        }
    }
}
