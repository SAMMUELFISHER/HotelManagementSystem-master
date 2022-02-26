package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelManagementSystem extends JFrame implements ActionListener {
    HotelManagementSystem() {

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/newhotel.jpg"));
        Image img2 = img1.getImage().getScaledInstance(600,416,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(0, 0, 600,416);
        add(imageLabel);

        JLabel projectNameLabel = new JLabel("Hotel Management System");
        projectNameLabel.setBounds(10, 330, 400, 50);
        projectNameLabel.setForeground(Color.BLACK);
        projectNameLabel.setFont(new Font("serif", Font.BOLD, 30));
        imageLabel.add(projectNameLabel);

        JLabel hotelTagLineLabel = new JLabel(" Delivering Luxurious Stays");
        hotelTagLineLabel.setBounds(40, 10, 600, 50);
        hotelTagLineLabel.setForeground(Color.white);
        hotelTagLineLabel.setFont(new Font( Font.SERIF,Font.BOLD, 40));
        imageLabel.add(hotelTagLineLabel);

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setBounds(450,340, 100,30);
        continueButton.addActionListener(this);
        imageLabel.add(continueButton);

        setTitle("WELCOME TO FULLERTON");
        setLayout(null);
        setResizable(false);
        pack();
        setSize( 600,416);
        setLocationRelativeTo(null);
        setVisible(true);

        while (true) {
            projectNameLabel.setVisible(false);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
            projectNameLabel.setVisible(true);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        new Login().setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new HotelManagementSystem();
    }
}
