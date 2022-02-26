package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class AllEmployeeInfo extends JDialog implements ActionListener {
    JTable table;
    JButton loadDataButton, backButton;
    JComboBox jobListComboBox;
    AllEmployeeInfo(){
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5,10,970,450);
        add(scrollPane);

        jobListComboBox = new JComboBox(new String[] {"All","Hotel Receptionist","Driver","Manager","Accountant","Kitchen Staff","Waiter/Waitress","House Keeping","Maintenance Technician","Parking Attendant","Porter"});
        jobListComboBox.setBounds(100,475,170,30);
        jobListComboBox.setBackground(Color.white);
        add(jobListComboBox);

        loadDataButton = new JButton("LOAD INFO");
        loadDataButton.setBounds(330,470,120,40);
        loadDataButton.setForeground(Color.white);
        loadDataButton.setBackground(Color.black);
        loadDataButton.addActionListener(this);
        add(loadDataButton);

        backButton = new JButton("ADD EMPLOYEE");
        backButton.setBounds(500,470,140,40);
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.black);
        backButton.addActionListener(this);
        add(backButton);

        setLayout(null);
        setModal(true);
        getContentPane().setBackground(new Color(254,227,200));
        setTitle("ALL EMPLOYEE INFORMATION");
        pack();
        setSize(1000,570);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton) {
            try {
                String job = (String) jobListComboBox.getSelectedItem();
                if (job == "All") {
                    DatabaseConnect con = new DatabaseConnect();
                    ResultSet employeeResultSet = con.stat.executeQuery("select * from employee");
                    table.setModel(DbUtils.resultSetToTableModel(employeeResultSet));
                    table.getColumn("AGE").setMaxWidth(40);
                    table.getColumn("GENDER").setMaxWidth(65);
                    table.getColumn("SALARY").setMaxWidth(60);
                }else if (job == "Driver"){
                    try {
                        DatabaseConnect con = new DatabaseConnect();
                        ResultSet driverResultSet = con.stat.executeQuery("select NAME,AGE,GENDER,SALARY,PHONE,AADHAR,CAR from driver");
                        table.setModel(DbUtils.resultSetToTableModel(driverResultSet));
                        table.getColumn("AGE").setMaxWidth(50);
                        table.getColumn("GENDER").setMaxWidth(65);
                    }catch (Exception ae){
                        System.out.println(ae);
                    }
                }else {
                    DatabaseConnect con = new DatabaseConnect();
                    ResultSet employeeResultSet = con.stat.executeQuery("select * from employee where job = '"+job+"'");
                    table.setModel(DbUtils.resultSetToTableModel(employeeResultSet));
                    table.getColumn("age").setMaxWidth(50);
                    table.getColumn("gender").setMaxWidth(60);
                    table.getColumn("salary").setMaxWidth(60);
                }
            }catch (Exception ae){
                System.out.println(ae);
            }
        } else if (e.getSource() == backButton) {
            this.setVisible(false);
            new ManageEmployee().setVisible(true);
        }
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new AllEmployeeInfo().setVisible(true);
    }
}
