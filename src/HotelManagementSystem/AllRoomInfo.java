package HotelManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AllRoomInfo extends JDialog implements ActionListener {
    JTable table;
    JComboBox bedTypeComboBox;
    JCheckBox isAvailableCheckBox;
    JButton loadDataButton, addRoomsButton, receptionButton ;
    AllRoomInfo(){
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5,10,500,350);
        add(scrollPane);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/bedtype.jpg"));
        Image img2 = img1.getImage().getScaledInstance(300,168,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imagelabel = new JLabel(img3);
        imagelabel.setBounds(515,10,300,168);
        add(imagelabel);

        JLabel bedTypeLabel = new JLabel("ROOM BEDTYPE");
        bedTypeLabel.setBounds(520,190,120,30);
        bedTypeLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(bedTypeLabel);

        bedTypeComboBox = new JComboBox(new String[] {"ALL","SINGLE BED","DOUBLE BED"});
        bedTypeComboBox.setBounds(650,190,120,30);
        add(bedTypeComboBox);

        isAvailableCheckBox = new JCheckBox("ONLY DISPLAY AVAILABLE");
        isAvailableCheckBox.setBounds(520,230,250,30);
        isAvailableCheckBox.setBackground(new Color(215,220,157));
        isAvailableCheckBox.setFont(new Font("Tahoma",Font.PLAIN,15));
        add(isAvailableCheckBox);


        loadDataButton = new JButton("SEARCH ROOMS");
        loadDataButton.setBackground(Color.black);
        loadDataButton.setForeground(Color.white);
        loadDataButton.setBounds(520,280,135,30);
        loadDataButton.addActionListener(this);
        add(loadDataButton);

        addRoomsButton = new JButton("ADD ROOMS");
        addRoomsButton.setBackground(Color.black);
        addRoomsButton.setForeground(Color.white);
        addRoomsButton.setBounds(660,280,130,30);
        addRoomsButton.addActionListener(this);
        add(addRoomsButton);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBackground(Color.black);
        receptionButton.setForeground(Color.white);
        receptionButton.setBounds(600,320,130,30);
        receptionButton.addActionListener(this);
        add(receptionButton);

        setLayout(null);
        setTitle("ALL ROOM INFO");
        getContentPane().setBackground(new Color(215,220,157));
        setModal(true);pack();
        setSize(820,400);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new AllRoomInfo().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton){
            String bedType = (String) bedTypeComboBox.getSelectedItem();
            if (bedType == "ALL"){
                if (isAvailableCheckBox.isSelected()){
                    DatabaseConnect con = new DatabaseConnect();
                    try {
                        ResultSet result = con.stat.executeQuery("select * from room  where availability ='AVAILABLE' order by roomNumber");
                        table.setModel(DbUtils.resultSetToTableModel(result));
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                }else {
                    DatabaseConnect con = new DatabaseConnect();
                    try {
                        ResultSet result = con.stat.executeQuery("select * from room order by roomNumber");
                        table.setModel(DbUtils.resultSetToTableModel(result));
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                }
            }else if (bedType == "DOUBLE BED" || bedType == "SINGLE BED"){
                if (isAvailableCheckBox.isSelected()){
                    DatabaseConnect con = new DatabaseConnect();
                    try {
                        ResultSet result = con.stat.executeQuery("select * from room where bedType = '"+bedType+"' and availability ='AVAILABLE' order by roomNumber");
                        table.setModel(DbUtils.resultSetToTableModel(result));
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                }else {
                    DatabaseConnect con = new DatabaseConnect();
                    try {
                        ResultSet result = con.stat.executeQuery("select * from room where bedType = '"+bedType+"' order by roomNumber");
                        table.setModel(DbUtils.resultSetToTableModel(result));
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                }
            }
        }else if (e.getSource() == addRoomsButton){
            this.setVisible(false);
            new  ManageRooms().setVisible(true);
        }else if (e.getSource() == receptionButton){
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }
}
