package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ManageEmployee extends JDialog implements ActionListener, ItemListener {
    JTextField nameField, ageField, salaryField, phoneField, aadharField, emailCarField;
    JLabel emailCarLabel;
    JRadioButton maleRadioButton, femaleRadioButton, otherRadioButton;
    JComboBox jobComboBox;
    JButton addButton, showEmployeeButton;

    ManageEmployee(){
        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        nameLabel.setBounds(10,10,120,30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100,10, 150,30);
        add(nameField);

        JLabel ageLabel = new JLabel("AGE");
        ageLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        ageLabel.setBounds(10,50,120,30);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100,50, 150,30);
        add(ageField);

        JLabel genderLabel = new JLabel("GENDER");
        genderLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        genderLabel.setBounds(10,90,120,30);
        add(genderLabel);

        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(100,90,35,30);
        maleRadioButton.setBackground(new Color(254,227,200));
        add(maleRadioButton);

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(140,90,35,30);
        femaleRadioButton.setBackground(new Color(254,227,200));
        add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        otherRadioButton.setBounds(180,90,65,30);
        otherRadioButton.setBackground(new Color(254,227,200));
        add(otherRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        JLabel jobLabel = new JLabel("JOB");
        jobLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        jobLabel.setBounds(10,130,120,30);
        add(jobLabel);

        jobComboBox = new JComboBox(new String[] {"Hotel Receptionist","Driver","Manager","Accountant","Kitchen Staff","Waiter/Waitress","House Keeping","Maintenance Technician","Parking Attendant","Porter"});
        jobComboBox.setBounds(100,130,150,30);
        jobComboBox.setBackground(Color.white);
        jobComboBox.addItemListener(this);
        add(jobComboBox);

        JLabel salaryLabel = new JLabel("SALARY");
        salaryLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        salaryLabel.setBounds(10,170,120,30);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(100,170, 150,30);
        add(salaryField);

        JLabel phoneLabel = new JLabel("PHONE|+91");
        phoneLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        phoneLabel.setBounds(10,210,120,30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(100,210, 150,30);
        add(phoneField);

        JLabel aadharLabel = new JLabel("AADHAR");
        aadharLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        aadharLabel.setBounds(10,250,120,30);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(100,250, 150,30);
        add(aadharField);

        emailCarLabel = new JLabel("E-MAIL");
        emailCarLabel.setFont(new Font("Tahoma",Font.PLAIN,15));
        emailCarLabel.setBounds(10,290,120,30);
        add(emailCarLabel);

        emailCarField = new JTextField();
        emailCarField.setBounds(100,290, 150,30);
        add(emailCarField);

        addButton = new JButton("ADD ");
        addButton.setBackground(Color.black);
        addButton.setForeground(Color.white);
        addButton.setBounds(5,340,100,30);
        add(addButton);
        addButton.addActionListener(this);

        showEmployeeButton = new JButton("SHOW EMPLOYEES");
        showEmployeeButton.setBackground(Color.black);
        showEmployeeButton.setForeground(Color.white);
        showEmployeeButton.setBounds(120,340,150,30);
        add(showEmployeeButton);
        showEmployeeButton.addActionListener(this);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("HotelManagementSystem/images/Emp1.jpg"));
        Image img2 = img1.getImage().getScaledInstance(318,280,Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel imageLabel = new JLabel(img3);
        imageLabel.setBounds(280,70,318,280);
        add(imageLabel);

        setLayout(null);
        getContentPane().setBackground(new Color(254,227,200));
        setModal(true);
        setTitle("ADD EMPLOYEE DETAILS");
        pack();
        setSize(590,430);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new ManageEmployee().setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String age = ageField.getText();
            String salary = salaryField.getText();
            String phone = phoneField.getText();
            String aadhar = aadharField.getText();
            String emailCar = emailCarField.getText();
            String gender = null;
            if (maleRadioButton.isSelected()) {
                gender = "Male";
            } else if (femaleRadioButton.isSelected()){
                gender = "Female";
            }else if (otherRadioButton.isSelected()){
                gender = "Other";
            }
            String job = (String) jobComboBox.getSelectedItem();
            if (job == "Driver"){
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("insert into driver values('" + name + "','" + age + "','" + gender + "','" + salary + "','" + phone + "','" + aadhar + "','" + emailCar + "','AVAILABLE','','')");
                    JOptionPane.showMessageDialog(null, "New Driver Added");
                    this.setVisible(false);
                    new ManageEmployee().setVisible(true);
                } catch (Exception ae) {
                    System.out.println(ae);
                }
            }else {
                DatabaseConnect con = new DatabaseConnect();
                try {
                    con.stat.executeUpdate("insert into employee values('" + name + "','" + age + "','" + gender + "','" + job + "','" + salary + "','" + phone + "','" + aadhar + "','" + emailCar + "')");
                    JOptionPane.showMessageDialog(null, "New Employee Added");
                    this.setVisible(false);
                    new ManageEmployee().setVisible(true);
                } catch (Exception ae) {
                    System.out.println(ae);
                }
            }
        }else if (e.getSource() == showEmployeeButton){
            this.setVisible(false);
            new AllEmployeeInfo().setVisible(true);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == jobComboBox){
            String job = (String) jobComboBox.getSelectedItem();
            if (job == "Driver"){
                emailCarLabel.setText("CAR");
            }else {
                emailCarLabel.setText("E-MAIL");
            }
        }
    }
}
