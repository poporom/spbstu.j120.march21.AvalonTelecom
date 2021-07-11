package ru.avalon.javapp.devj120.avalontelecom.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ru.avalon.javapp.devj120.avalontelecom.models.ClientInfo;

public abstract class ClientDialog extends JDialog {

    private JTextField areaCode;
    private JTextField localNum;
    private JTextField clientName;
    private JTextField address;

    private boolean okPressed;

    public ClientDialog(Frame owner) {
        super(owner, true);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel("Phone number (");
        p.add(lbl);
        areaCode = new JTextField(3);
        p.add(areaCode);
        lbl.setLabelFor(areaCode);
        p.add(new JLabel(") "));
        localNum = new JTextField(10);
        p.add(localNum);
        contentPane.add(p);

        clientName = new JTextField(40);
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl = new JLabel("Client name");
        lbl.setLabelFor(clientName);
        p.add(lbl);
        p.add(clientName);
        contentPane.add(p);

        address = new JTextField(40);
        p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl = new JLabel("Address");
        lbl.setLabelFor(address);
        p.add(lbl);
        p.add(address);
        contentPane.add(p);

        //addOkCancelButton();
    }

    public void addOkCancelButton() {

        Container contentPane = getContentPane();
        JPanel p;

        p = new JPanel();
        JButton btn = new JButton("OK");
        btn.addActionListener(e -> hideDialog(true));
        p.add(btn);
        btn = new JButton("Cancel");
        btn.addActionListener(e -> hideDialog(false));
        p.add(btn);
        contentPane.add(p);

    }

    public void prepareForNew() {
        setTitle("New client registration");

        areaCode.setText("");
        localNum.setText("");
        clientName.setText("");
        address.setText("");
        areaCode.setEditable(true);
        localNum.setEditable(true);

        prepareForShow();
    }

    public void prepareForEdit(ClientInfo clientInfo) {
        setTitle("Editing client details");

        areaCode.setText(clientInfo.getPhoneNumber().getAreaCode());
        localNum.setText(clientInfo.getPhoneNumber().getLocalNum());
        clientName.setText(clientInfo.getName());
        address.setText(clientInfo.getAddress());
        areaCode.setEditable(false);
        localNum.setEditable(false);

        prepareForShow();
    }

    public void prepareForShow() {
        okPressed = false;
        pack();
        setLocationRelativeTo(null);
    }

    private void hideDialog(boolean okPressed) {
        this.okPressed = okPressed;
        setVisible(false);
    }

    public boolean isOkPressed() {
        return okPressed;
    }

    public String getAreaCode() {
        return areaCode.getText();
    }

    public String getLocalNum() {
        return localNum.getText();
    }

    public String getClientName() {
        return clientName.getText();
    }

    public String getAddress() {
        return address.getText();
    }

    public abstract void saveAttributes(ClientInfo ci);

}
