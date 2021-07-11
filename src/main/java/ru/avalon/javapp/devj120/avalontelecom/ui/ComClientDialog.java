/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj120.avalontelecom.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.ComClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.IndClientInfo;

/**
 *
 * @author rompop
 */
public class ComClientDialog extends ClientDialog {

    private JTextField dirName;
    private JTextField contactName;

    public ComClientDialog(Frame owner) {
        super(owner);

        Container contentPane = super.getContentPane();
        JPanel p;
        JLabel lbl;

        dirName = new JTextField(40);
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl = new JLabel("Director name");
        lbl.setLabelFor(dirName);
        p.add(lbl);
        p.add(dirName);
        contentPane.add(p);

        contactName = new JTextField(40);
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl = new JLabel("Contact name");
        lbl.setLabelFor(contactName);
        p.add(lbl);
        p.add(contactName);
        contentPane.add(p);

        super.addOkCancelButton();

    }

    public String getContactName() {
        return contactName.getText();
    }

    public String getDirName() {
        return dirName.getText();
    }

    public void prepareForEdit(ClientInfo clientInfo) {
        dirName.setText(((ComClientInfo) clientInfo).getDirName());
        contactName.setText(((ComClientInfo) clientInfo).getContactName());
        super.prepareForEdit(clientInfo);
    }

    public void saveAttributes(ClientInfo ci) {

        ComClientInfo cci = (ComClientInfo) ci;

        cci.setName(getClientName());
        cci.setAddress(getAddress());

        cci.setDirName(getDirName());
        cci.setContactName(getContactName());

    }

}
