package ru.avalon.javapp.devj120.avalontelecom.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import ru.avalon.javapp.devj120.avalontelecom.controllers.ClientsController;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.ComClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.IndClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.PhoneNumber;

public class MainFrame extends JFrame {

    private ClientsController controller;
    private JTable clientsTable;
    private ClientsTableModel clientsTableModel;

    public MainFrame(ClientsController controller) {
        super("Avalon Telecom: clients list");
        setBounds(300, 200, 900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.controller = controller;
        Container contentPane = getContentPane();
        clientsTableModel = new ClientsTableModel(controller);
        clientsTable = new JTable(clientsTableModel);
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(clientsTable.getTableHeader(), BorderLayout.NORTH);
        contentPane.add(new JScrollPane(clientsTable), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu operations = new JMenu("Operations");
        operations.setMnemonic('O');
        menuBar.add(operations);

        addMenuItemTo(operations, "Add individual", 'A',
                KeyStroke.getKeyStroke('A', InputEvent.ALT_DOWN_MASK),
                this::addIndClient);

        addMenuItemTo(operations, "Add company", 'B',
                KeyStroke.getKeyStroke('B', InputEvent.ALT_DOWN_MASK),
                this::addComClient);

        addMenuItemTo(operations, "Change", 'C',
                KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK),
                this::changeClient);

        addMenuItemTo(operations, "Delete", 'D',
                KeyStroke.getKeyStroke('D', InputEvent.ALT_DOWN_MASK),
                this::dropClient);

        setJMenuBar(menuBar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitWithConfirm();
            }
        });
    }

    private void addMenuItemTo(JMenu parent, String text, char mnemonic,
            KeyStroke accelerator, ActionListener al) {
        JMenuItem mi = new JMenuItem(text, mnemonic);
        mi.setAccelerator(accelerator);
        mi.addActionListener(al);
        parent.add(mi);
    }

    private void addIndClient(ActionEvent e) {
        IndClientDialog cd = new IndClientDialog(this);
        cd.prepareForNew();
        cd.setVisible(true);
        Date bd = new Date();

        try {
            bd = new SimpleDateFormat("dd/MM/yyyy").parse(cd.getBirhDate());
        } catch (Exception ex) {

        }

        while (cd.isOkPressed()) {
            try {
                clientsTableModel.clientAdded(
                        new PhoneNumber(cd.getAreaCode(), cd.getLocalNum()),
                        cd.getClientName(),
                        cd.getAddress(),
                        bd
                );
                break;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error adding client", JOptionPane.ERROR_MESSAGE);
                cd.setVisible(true);
            }
        }
    }

    private void addComClient(ActionEvent e) {
        ComClientDialog cd = new ComClientDialog(this);
        cd.prepareForNew();
        cd.setVisible(true);
        while (cd.isOkPressed()) {
            try {
                clientsTableModel.clientAdded(
                        new PhoneNumber(cd.getAreaCode(), cd.getLocalNum()),
                        cd.getClientName(),
                        cd.getAddress(),
                        cd.getDirName(),
                        cd.getContactName()
                );
                break;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error adding client", JOptionPane.ERROR_MESSAGE);
                cd.setVisible(true);
            }
        }
    }

    private void changeClient(ActionEvent e) {

        int rowNdx = clientsTable.getSelectedRow();
        if (rowNdx == -1) {
            return;
        }

        ClientInfo ci = controller.getClientInfo(rowNdx);
        ClientDialog cd;
        if (ci instanceof IndClientInfo)
            cd = new IndClientDialog(this);
        else
            cd = new ComClientDialog(this);

        cd.prepareForEdit(ci);
        cd.setVisible(true);
        if (cd.isOkPressed()) {
            ci.setName(cd.getClientName());
            ci.setAddress(cd.getAddress());
            /*
            if(ci instanceof IndClientInfo) {
                
            } else {
                
            }
            */
            cd.saveAttributes(ci);
            
            clientsTableModel.clientChanged(rowNdx);
        }
    }

    private void dropClient(ActionEvent e) {
        int rowNdx = clientsTable.getSelectedRow();
        if (rowNdx == -1) {
            return;
        }
        ClientInfo ci = controller.getClientInfo(rowNdx);
        if (JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete client with number "
                + ci.getPhoneNumber() + "?",
                "Delete confirm", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            clientsTableModel.clientDeleted(rowNdx);
        }
    }

    private void exitWithConfirm() {
        if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
                "Exit confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION) {
            return;
        }

        try {
            controller.saveData();
        } catch (IOException ex) {
            if (JOptionPane.showConfirmDialog(this,
                    "Error happened while the data was saved: " + ex.getMessage() + ".\n"
                    + "Are you sure you want to exit and loose your data (Yes),"
                    + "or you want to keep working while trying to fix the problem (No)?",
                    "Exit confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE) == JOptionPane.NO_OPTION) {
                return;
            }
        }

        dispose();
    }
}
