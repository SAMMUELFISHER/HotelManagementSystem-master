package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageRooms extends JDialog implements ActionListener {
    JTextField roomNoField, priceField;
    JComboBox availableComboBox, bedTypeComboBox;
    JButton addRoomButton, showRoomButton;

    ManageRooms(){
        JLabel roomNoLabel = new JLabel("ROOM NUMBER");
        roomNoLabel.setBounds(10,20,120,30);
        roomNoLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(roomNoLabel);

        roomNoField =new JTextField();
        roomNoField.setBounds(150,20,110,30);
        add(roomNoField);

        JLabel availableLabel = new JLabel("AVAILABLE");
        availableLabel.setBounds(10,60,120,30);
        availableLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(availableLabel);

        availableComboBox =new JComboBox(new String[]  {"AVAILABLE","OCCUPIED"});
        availableComboBox.setBounds(150,60,110,30);
        availableComboBox.setBackground(Color.white);
        add(availableComboBox);

        JLabel priceLabel = new JLabel("PRICE");
        priceLabel.setBounds(10,100,120,30);
        priceLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(priceLabel);

        priceField =new JTextField();
        priceField.setBounds(150,100,110,30);
        add(priceField);

        JLabel bedTypeLabel = new JLabel("BED TYPE");
        bedTypeLabel.setBounds(10,140,120,30);
        bedTypeLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(bedTypeLabel);

        bedTypeComboBox =new JComboBox(new String[]  {"SINGLE BED","DOUBLE BED"});
        bedTypeComboBox.setBounds(150,140,110,30);
        bedTypeComboBox.setBackground(Color.white);
        add(bedTypeComboBox);

        addRoomButton = new JButton("ADD ROOM");
        addRoomButton.setBounds(10,200,120,30);
        addRoomButton.setBackground(Color.BLACK);
        addRoomButton.setForeground(Color.white);
        addRoomButton.addActionListener(this);
        add(addRoomButton);

        showRoomButton = new JButton("SHOW ROOMS");
        showRoomButton.setBounds(140,200,120,30);
        showRoomButton.setBackground(Color.BLACK);
        showRoomButton.setForeground(Color.white);
        showRoomButton.addActionListener(this);
        add(showRoomButton);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/bedtype1.jpg"));
        JLabel imageLabel = new JLabel(img1);
        imageLabel.setBounds(280,20,273,184);
        add(imageLabel);

        setTitle("ADD ROOM DETAILS(W/AC)");
        getContentPane().setBackground(new Color(190,193,226));
        setModal(true);
        setLayout(null);
        pack();
        setBounds(220,150,600,300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new ManageRooms().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addRoomButton){
            String roomno = roomNoField.getText();
            String available = (String) availableComboBox.getSelectedItem();
            String price = priceField.getText();
            String bedtype = (String) bedTypeComboBox.getSelectedItem();

            DatabaseConnect con = new DatabaseConnect();
            try {
                con.stat.executeUpdate("insert into room values('"+roomno+"','"+available+"','"+price+"','"+bedtype+"')");
                JOptionPane.showMessageDialog(null,"NEW ROOM ADDED");
                this.setVisible(false);
            }catch (Exception ae){
                System.out.println(ae);
            }
        }else if (e.getSource() == showRoomButton){
            this.setVisible(false);
            new AllRoomInfo().setVisible(true);
        }
    }
}