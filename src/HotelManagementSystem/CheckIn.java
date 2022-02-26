package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CheckIn extends JDialog implements ActionListener, ItemListener {
    JTextField customerIDNumberField, customerNameField, customerCountryField,roomPriceField, roomDepositField;
    JButton addCustomerButton, receptionButton;
    JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    JComboBox idComboBox, allocatedRoomComboBox, bedTypeComboBox;

    CheckIn(){
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10,10,100,30);
        idLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(idLabel);

        idComboBox = new JComboBox(new String[] {"PASSPORT","AADHAR CARD","DRIVING LICENSE","VOTER ID"});
        idComboBox.setBounds(150,10,135,30);
        idComboBox.setBackground(Color.white);
        add(idComboBox);

        JLabel idNumberLabel = new JLabel("ID NUMBER");
        idNumberLabel.setBounds(10,50,100,30);
        idNumberLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(idNumberLabel);

        customerIDNumberField = new JTextField();
        customerIDNumberField.setBounds(150,50,135,30);
        add(customerIDNumberField);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(10,90,100,30);
        nameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(nameLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(150,90,135,30);
        add(customerNameField);

        JLabel customerGenderLabel = new JLabel("GENDER");
        customerGenderLabel.setBounds(10,130,100,30);
        customerGenderLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(customerGenderLabel);

        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(145,130,35,30);
        maleRadioButton.setBackground(new Color(242,179,162));
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(190,130,35,30);
        femaleRadioButton.setBackground(new Color(242,179,162));
        add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(235,130,65,30);
        otherRadioButton.setBackground(new Color(242,179,162));
        add(otherRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        JLabel countryLabel = new JLabel("COUNTRY");
        countryLabel.setBounds(10,170,100,30);
        countryLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(countryLabel);

        customerCountryField = new JTextField();
        customerCountryField.setBounds(150,170,135,30);
        add(customerCountryField);

        JLabel bedTypeLabel = new JLabel("BED TYPE ?");
        bedTypeLabel.setBounds(10,210,100,30);
        bedTypeLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(bedTypeLabel);

        bedTypeComboBox = new JComboBox(new String[] {"","SINGLE BED","DOUBLE BED"});
        bedTypeComboBox.setBounds(150,210,135,30);
        bedTypeComboBox.setBackground(Color.white);
        bedTypeComboBox.addItemListener(this);
        add(bedTypeComboBox);

        JLabel roomLabel = new JLabel("ALLOCATED ROOM");
        roomLabel.setBounds(10,250,130,30);
        roomLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomLabel);

        allocatedRoomComboBox = new JComboBox();
        allocatedRoomComboBox.setBounds(150,250,135,30);
        allocatedRoomComboBox.setBackground(Color.white);
        allocatedRoomComboBox.addItemListener(this);
        add(allocatedRoomComboBox);

        JLabel priceLabel = new JLabel("PRICE");
        priceLabel.setBounds(10,290,100,30);
        priceLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(priceLabel);

        roomPriceField = new JTextField();
        roomPriceField.setBounds(150,290,135,30);
        roomPriceField.setEditable(false);
        add(roomPriceField);

        JLabel depositLabel = new JLabel("DEPOSIT");
        depositLabel.setBounds(10,330,100,30);
        depositLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(depositLabel);

        roomDepositField = new JTextField();
        roomDepositField.setBounds(150,330,135,30);
        add(roomDepositField);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/checkin1.jpg"));
        Image img2 = img1.getImage().getScaledInstance(383,400,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imagLabel = new JLabel(img3);
        imagLabel.setBounds(310,0,383,400);
        add(imagLabel);

        addCustomerButton = new JButton("CHECK IN");
        addCustomerButton.setBackground(Color.black);
        addCustomerButton.setForeground(Color.white);
        addCustomerButton.setBounds(5,370,140,30);
        add(addCustomerButton);
        addCustomerButton.addActionListener(this);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.black);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(155,370,140,30);
        add(receptionButton);
        receptionButton.addActionListener(this);

        setLayout(null);
        getContentPane().setBackground(new Color(242,179,162));
        setTitle("NEW CUSTOMER DETAILS");
        setModal(true);
        pack();
        setSize(720,460);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new CheckIn().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCustomerButton) {
            String id = (String) idComboBox.getSelectedItem();
            String idnumber = customerIDNumberField.getText();
            String name = customerNameField.getText();
            String gender = null;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()){
                gender = "Female";
            }else if (otherRadioButton.isSelected()){
                gender = "Other";
            }
            String country = customerCountryField.getText();
            String room = (String) allocatedRoomComboBox.getSelectedItem();
            String deposit = roomDepositField.getText();
            DatabaseConnect con = new DatabaseConnect();
            try {
                con.stat.executeUpdate("insert into customer values('" + id + "','" + idnumber + "','" + name + "','" + gender + "','" +country + "','" + room + "','YES','" + deposit + "')");
                con.stat.executeUpdate("update room set AVAILABILITY = 'OCCUPIED' where ROOMNUMBER = '"+room+"'");
                JOptionPane.showMessageDialog(null, "New Customer Added");
                this.setVisible(false);
                new CheckIn().setVisible(true);
            } catch (Exception c) {
                System.out.println(c);
            }
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == bedTypeComboBox){
            String bedType = (String) bedTypeComboBox.getSelectedItem();
            allocatedRoomComboBox.removeAllItems();
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet roomResult = con.stat.executeQuery( "Select * from room where AVAILABILITY = 'AVAILABLE' and bedType = '"+ bedType+"' order by roomNumber");
                while (roomResult.next()) {
                    allocatedRoomComboBox.addItem(roomResult.getString("ROOMNUMBER"));
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        }else if (e.getSource() == allocatedRoomComboBox){
            String allocatedRoom = (String) allocatedRoomComboBox.getSelectedItem();
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet roomResult = con.stat.executeQuery( "Select * from room where ROOMNUMBER = '"+allocatedRoom+"'");
                while (roomResult.next()) {
                    roomPriceField.setText(roomResult.getString("PRICE"));
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        }
    }
}
