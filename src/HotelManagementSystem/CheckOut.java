package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CheckOut extends JDialog implements ActionListener, ItemListener {
    JButton  checkOutButton, receptionButton;
    JComboBox customerIdComboBox;
    JTextField roomNumberField, nameField, checkedInField, amountPaidField, pendingAmountField;
    CheckOut(){
        JLabel customerIdLabel = new JLabel("CUSTOMER ID");
        customerIdLabel.setBounds(10,10,130,30);
        customerIdLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(customerIdLabel);

        customerIdComboBox = new JComboBox(new String[] {""});
        try {
            DatabaseConnect con = new DatabaseConnect();
            ResultSet customerIdResultSet = con.stat.executeQuery("select * from customer");
            while (customerIdResultSet.next()){
                customerIdComboBox.addItem(customerIdResultSet.getString("idNumber"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        customerIdComboBox.setBounds(150,10,130,30);
        customerIdComboBox.addItemListener(this);
        add(customerIdComboBox);

        JLabel roomNumberLabel = new JLabel("ROOM NUMBER");
        roomNumberLabel.setBounds(10,50,130,30);
        roomNumberLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(150,50,130,30);
        roomNumberField.setEditable(false);
        add(roomNumberField);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setBounds(10,90,130,30);
        nameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150,90,130,30);
        nameField.setEditable(false);
        add(nameField);

        JLabel checkedInStatLabel = new JLabel("CHECKED IN?");
        checkedInStatLabel.setBounds(10,130,130,30);
        checkedInStatLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(checkedInStatLabel);

        checkedInField = new JTextField();
        checkedInField.setBounds(150,130,130,30);
        checkedInField.setEditable(false);
        add(checkedInField);

        JLabel amountPaidLabel = new JLabel("AMOUNT PAID");
        amountPaidLabel.setBounds(10,170,130,30);
        amountPaidLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(amountPaidLabel);

        amountPaidField = new JTextField();
        amountPaidField.setBounds(150,170,130,30);
        amountPaidField.setEditable(false);
        add(amountPaidField);

        JLabel pendingAmountLabel = new JLabel("PENDING AMOUNT");
        pendingAmountLabel.setBounds(10,210,130,30);
        pendingAmountLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(pendingAmountLabel);

        pendingAmountField = new JTextField();
        pendingAmountField.setBounds(150,210,130,30);
        pendingAmountField.setEditable(false);
        add(pendingAmountField);

        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setBackground(Color.BLACK);
        checkOutButton.setForeground(Color.white);
        checkOutButton.setBounds(10,260,120,30);
        checkOutButton.addActionListener(this);
        add(checkOutButton);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.BLACK);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(150,260,120,30);
        receptionButton.addActionListener(this);
        add(receptionButton);

        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/checkout2.jpg"));
        Image img5 = img4.getImage().getScaledInstance(311,350,Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);
        JLabel imageLabel = new JLabel(img6);
        imageLabel.setBounds(320,0,311,350);
        add(imageLabel);

        setLayout(null);
        setTitle("CHECK OUT");
        getContentPane().setBackground(new Color(242,179,162));
        setModal(true);
        pack();
        setSize(660,350);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new CheckOut().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == checkOutButton){
            try{
                DatabaseConnect con = new DatabaseConnect();
                String custIdNumber = (String) customerIdComboBox.getSelectedItem();
                String roomNumber = roomNumberField.getText();
                con.stat.executeUpdate("delete from customer where idNumber = '"+custIdNumber+"'");
                con.stat.executeUpdate("update room set availability = 'AVAILABLE' where roomNumber = '"+ roomNumber+"'");
                JOptionPane.showMessageDialog(null, "CHECK OUT DONE");
                this.setVisible(false);
                new Reception().setVisible(true);
            }catch(Exception ee){
                System.out.println(ee);
            }
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == customerIdComboBox){
            String roomNumber = null;
            String deposit = null;
            int pendingAmount;
            String price;
            try {
                String idNumber = (String) customerIdComboBox.getSelectedItem();
                DatabaseConnect con = new DatabaseConnect();
                ResultSet customerResultSet = con.stat.executeQuery("select * from customer where idNumber = '" + idNumber+"'");
                while (customerResultSet.next()){
                    roomNumberField.setText(customerResultSet.getString("roomNumber"));
                    nameField.setText(customerResultSet.getString("name"));
                    checkedInField.setText(customerResultSet.getString("status"));
                    amountPaidField.setText(customerResultSet.getString("deposit"));
                    roomNumber = customerResultSet.getString("roomNumber");
                    deposit = customerResultSet.getString("deposit");
                }ResultSet pendingAmountResultSet = con.stat.executeQuery("select * from room where roomNumber = '"+roomNumber+"'");
                while (pendingAmountResultSet.next()){
                    price = pendingAmountResultSet.getString("price");
                    pendingAmount = Integer.parseInt(price) - Integer.parseInt(deposit);
                    pendingAmountField.setText(Integer.toString(pendingAmount));
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        }
    }
}
