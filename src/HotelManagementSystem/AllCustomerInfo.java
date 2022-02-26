package HotelManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class AllCustomerInfo extends JDialog implements ActionListener {
    JTable table;
    JButton loadDataButton, receptionButton;
    AllCustomerInfo(){
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5,10,900,450);
        add(scrollPane);

        loadDataButton = new JButton("LOAD INFO");
        loadDataButton.setBounds(300,490,100,30);
        loadDataButton.setForeground(Color.white);
        loadDataButton.setBackground(Color.black);
        loadDataButton.addActionListener(this);
        add(loadDataButton);

        receptionButton = new JButton("RECEPTION");
        receptionButton.setBounds(500,490,100,30);
        receptionButton.setForeground(Color.white);
        receptionButton.setBackground(Color.black);
        receptionButton.addActionListener(this);
        add(receptionButton);

        setLayout(null);
        setModal(true);
        setTitle("CUSTOMER INFORMATION");
        getContentPane().setBackground(new Color(254,227,200));
        pack();
        setSize(920,570);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadDataButton) {
            try {
                DatabaseConnect con = new DatabaseConnect();
                ResultSet result = con.stat.executeQuery("select ID,IDNUMBER, NAME, GENDER,COUNTRY, ROOMNUMBER, STATUS, DEPOSIT from customer");
                table.setModel(DbUtils.resultSetToTableModel(result));
                table.getColumn("GENDER").setMaxWidth(70);
                table.getColumn("ROOMNUMBER").setMaxWidth(110);
                table.getColumn("COUNTRY").setMaxWidth(110);
                table.getColumn("STATUS").setMaxWidth(70);
                table.getColumn("DEPOSIT").setMaxWidth(70);
            }catch (Exception ae){
                System.out.println(ae);
            }
        } else if (e.getSource() == receptionButton) {
            this.setVisible(false);
            new Reception().setVisible(true);
        }
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        new AllCustomerInfo().setVisible(true);
    }
}
