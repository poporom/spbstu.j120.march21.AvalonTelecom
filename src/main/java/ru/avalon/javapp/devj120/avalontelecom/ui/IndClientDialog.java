/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.avalon.javapp.devj120.avalontelecom.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;
import ru.avalon.javapp.devj120.avalontelecom.models.IndClientInfo;

/**
 *
 * @author rompop
 */
public class IndClientDialog extends ClientDialog {

    private JTextField birthDate;

    public IndClientDialog(Frame owner) {
        super(owner);

        Container contentPane = super.getContentPane();
        JPanel p;
        JLabel lbl;

        birthDate = new JTextField(40);
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl = new JLabel("Birthdate");
        lbl.setLabelFor(birthDate);
        p.add(lbl);
        p.add(birthDate);
        contentPane.add(p);

        super.addOkCancelButton();

    }

    public String getBirhDate() {
        return birthDate.getText();
    }

    public void prepareForEdit(ClientInfo clientInfo) {
        birthDate.setText(((IndClientInfo) clientInfo).getBirthDate());
        super.prepareForEdit(clientInfo);
    }

    //@Override
    public void saveAttributes(ClientInfo ci) {

        IndClientInfo ici = (IndClientInfo) ci;

        ici.setName(getClientName());
        ici.setAddress(getAddress());

        Date bd = new Date();

        try {
            bd = new SimpleDateFormat("dd/MM/yyyy").parse(getBirhDate());
        } catch (Exception ex) {

        }

        ici.setBirthDate(bd);

    }

}
