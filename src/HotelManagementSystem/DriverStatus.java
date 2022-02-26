package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class DriverStatus extends JDialog implements ActionListener, ItemListener {
    JComboBox statusComboBox, driveInUseComboBox;
    JTextField roomNumberField, locationField;
    JButton updateButton, receptionButton;

    DriverStatus(){

        JLabel driverUsageLabel = new JLabel("DRIVER ACTIVITY");
        driverUsageLabel.setBounds(10,20,130,30);
        driverUsageLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(driverUsageLabel);

        statusComboBox = new JComboBox(new String[] {"","AVAILABLE","BUSY"});
        statusComboBox.setBounds(180,20,120,30);
        statusComboBox.addItemListener(this);
        add(statusComboBox);

        JLabel driverInUseLabel = new JLabel("DRIVER IN USE");
        driverInUseLabel.setBounds(10,70,140,30);
        driverInUseLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(driverInUseLabel);

        driveInUseComboBox = new JComboBox(new String[]{""});
        driveInUseComboBox.setBounds(180,70,120,30);
        add(driveInUseComboBox);

        JLabel roomNumberLabel = new JLabel("ROOM NUMBER");
        roomNumberLabel.setBounds(10,120,120,30);
        roomNumberLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomNumberLabel);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(180,120,120,30);
        add(roomNumberField);

        JLabel locationLabel = new JLabel("LOCATION");
        locationLabel.setBounds(10,170,120,30);
        locationLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(180,170,120,30);
        add(locationField);

        updateButton = new JButton("UPDATE");
        updateButton.setBackground(Color.black);
        updateButton.setForeground(Color.white);
        updateButton.setBounds(40,230,100,30);
        add(updateButton);
        updateButton.addActionListener(this);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.black);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(170,230,120,30);
        add(receptionButton);
        receptionButton.addActionListener(this);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/hotelpickup.jpg"));
        Image img2 = img1.getImage().getScaledInstance(300,340,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(260,-60,300,398);
        add(imageLabel);

        setLayout(null);
        setResizable(false);
        setModal(true);
        setTitle("PICKUP/DROP-OFF");
        getContentPane().setBackground(new Color(119,177,165));
        pack();
        setSize(570,320);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new DriverStatus().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton){
            if (statusComboBox.getSelectedItem() == "BUSY"){

                String driver = (String) driveInUseComboBox.getSelectedItem();
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("update driver set AVAILABLE = 'AVAILABLE', location = '', PICKUP = '' where name = '"+driver+"'");
                    JOptionPane.showMessageDialog(null,"DRIVER STATUS UPDATED");
                }catch (Exception ae){
                    System.out.println(ae);
                }
                this.setVisible(false);
                new DriverStatus().setVisible(true);
            }else if (statusComboBox.getSelectedItem() =="AVAILABLE"){

                String driver = (String) driveInUseComboBox.getSelectedItem();
                String location = locationField.getText();
                String roomNumber = roomNumberField.getText();
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("update driver set AVAILABLE = 'BUSY', location = '"+location+"', PICKUP = '"+roomNumber+"' where name = '"+driver+"'");
                    JOptionPane.showMessageDialog(null,"DRIVER STATUS UPDATED");
                }catch (Exception ae){
                    System.out.println(ae);
                }
                this.setVisible(false);
                new DriverStatus().setVisible(true);
            }
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
            String status = (String) statusComboBox.getSelectedItem();
            driveInUseComboBox.removeAllItems();
            roomNumberField.setText("");
            locationField.setText("");
        try {
            DatabaseConnect con = new DatabaseConnect();
            ResultSet result = con.stat.executeQuery("select * from driver where AVAILABLE = '"+status+"'");
            while (result.next()){
                driveInUseComboBox.addItem(result.getString("name"));
                roomNumberField.setText(result.getString("PICKUP"));
                locationField.setText(result.getString("LOCATION"));
            }
        }catch (Exception ae){
            System.out.println(ae);
        }
    }
}
