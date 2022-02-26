package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JDialog implements ActionListener {
    JButton checkInButton, driverStatusButton, allCustInfoButton, checkOutButton,searchRoomButton;
    Reception(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();

        checkInButton = new JButton("NEW CUSTOMER");
        checkInButton.setBackground(Color.black);
        checkInButton.setForeground(Color.white);
        checkInButton.setBounds(10,20,190,40);
        checkInButton.addActionListener(this);
        add(checkInButton);

        allCustInfoButton = new JButton("ALL CUSTOMER INFO");
        allCustInfoButton.setBackground(Color.black);
        allCustInfoButton.setForeground(Color.white);
        allCustInfoButton.setBounds(10,85,190,40);
        allCustInfoButton.addActionListener(this);
        add(allCustInfoButton);

        searchRoomButton = new JButton("SEARCH ROOM");
        searchRoomButton.setBackground(Color.black);
        searchRoomButton.setForeground(Color.white);
        searchRoomButton.setBounds(10,150,190,40);
        searchRoomButton.addActionListener(this);
        add(searchRoomButton);

        checkOutButton = new JButton("CHECK OUT");
        checkOutButton.setBackground(Color.black);
        checkOutButton.setForeground(Color.white);
        checkOutButton.setBounds(10,215,190,40);
        checkOutButton.addActionListener(this);
        add(checkOutButton);

        driverStatusButton = new JButton("DRIVER STATUS");
        driverStatusButton.setBackground(Color.black);
        driverStatusButton.setForeground(Color.white);
        driverStatusButton.setBounds(10,280,190,40);
        driverStatusButton.addActionListener(this);
        add(driverStatusButton);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/receptionnew.jpg"));
        Image img2 = img1.getImage().getScaledInstance(450,300,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imagelabel = new JLabel(img3);
        imagelabel.setBounds( 215,10,450,300);
        add(imagelabel);

        setLayout(null);
        setTitle("RECEPTION");
        setModal(true);
        getContentPane().setBackground(new Color(199, 178, 160));
        setSize(700,380);
        setLocation((width - 700)/2,130);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new Reception().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkInButton){
            this.setVisible(false);
            new CheckIn().setVisible(true);
        }else if (e.getSource() == allCustInfoButton){
            this.setVisible(false);
            new AllCustomerInfo().setVisible(true);
        }else if (e.getSource() == searchRoomButton){
            this.setVisible(false);
            new AllRoomInfo().setVisible(true);
        }else if (e.getSource() == driverStatusButton){
            this.setVisible(false);
            new DriverStatus().setVisible(true);
        }else if (e.getSource() == checkOutButton){
            this.setVisible(false);
            new CheckOut().setVisible(true);
        }
    }
}
