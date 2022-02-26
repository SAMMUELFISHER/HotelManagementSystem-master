package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener  {
    JMenuBar menuBar;
    JMenu hotelManagementMenu, adminMenu, exitMenu;
    JMenuItem receptionItem, EmployeesManagementItem, ManageRoomsItem, logoutItem;

    Dashboard(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int width = (int) tk.getScreenSize().getWidth();
        int height = (int) tk.getScreenSize().getHeight();
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/dashboard.jpg"));
        Image img2 = img1.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(0,30 ,width,height);
        add(imageLabel);

        JLabel dashboardTagLineLabel = new JLabel("THE FULLERTON ENTERPRISE WELCOMES YOU ");
        dashboardTagLineLabel.setSize(850,50);
        dashboardTagLineLabel.setLocation((width - dashboardTagLineLabel.getWidth())/2,30);
        dashboardTagLineLabel.setForeground(Color.WHITE);
        dashboardTagLineLabel.setFont(new Font("",Font.BOLD, 35));
        imageLabel.add(dashboardTagLineLabel);

        menuBar = new JMenuBar();
        add(menuBar);
        menuBar.setBounds(0,0,width,40);

        hotelManagementMenu = new JMenu("HOTEL MANAGEMENT");
        hotelManagementMenu.setFont(new Font("Tahoma",Font.BOLD,15));
        hotelManagementMenu.setForeground(Color.RED);
        adminMenu = new JMenu("ADMIN");
        adminMenu.setFont(new Font("Tahoma",Font.BOLD,15));
        adminMenu.setForeground(Color.BLUE);
        exitMenu = new JMenu("EXIT");
        exitMenu.setFont(new Font("Tahoma",Font.BOLD,15));
        exitMenu.setForeground(Color.red);
        menuBar.add(hotelManagementMenu);
        menuBar.add(adminMenu);
        menuBar.add(exitMenu);

        receptionItem = new JMenuItem("RECEPTION");
        receptionItem.setFont(new Font("Tahoma",Font.PLAIN,15));
        receptionItem.addActionListener(this);
        EmployeesManagementItem = new JMenuItem("MANAGE EMPLOYEES");
        EmployeesManagementItem.setFont(new Font("Tahoma",Font.PLAIN,15));
        EmployeesManagementItem.addActionListener(this);
        ManageRoomsItem = new JMenuItem("MANAGE ROOMS");
        ManageRoomsItem.setFont(new Font("Tahoma",Font.PLAIN,15));
        ManageRoomsItem.addActionListener(this);
        logoutItem = new JMenuItem("LOGOUT");
        logoutItem.setFont(new Font("Tahoma",Font.PLAIN,15));
        logoutItem.addActionListener(this);
        hotelManagementMenu.add(receptionItem);
        adminMenu.add(EmployeesManagementItem);
        adminMenu.add(ManageRoomsItem);
        exitMenu.add(logoutItem);

        setTitle("DASHBOARD");
        setUndecorated(true);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new Dashboard().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "MANAGE EMPLOYEES" -> new ManageEmployee().setVisible(true);
            case "MANAGE ROOMS" -> new ManageRooms().setVisible(true);
            case "RECEPTION" -> new Reception().setVisible(true);
            case "LOGOUT" -> {
                int confirm = JOptionPane.showConfirmDialog(null,"ARE YOU SURE YOU WANT TO LOGOUT?");
                if (confirm == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        }
    }
}
