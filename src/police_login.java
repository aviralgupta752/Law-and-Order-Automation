package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class police_login
{
    static JFrame frame;
    static JLabel lblWelcome, lblPass, lblUsername;
    static JTextField txtUser;
    static JPasswordField txtPass;
    static JButton btnLogin, btnAdmin, btnSecurity;
    static JCheckBox show_pass;
    
    static Connection con;
    static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";
    static void display()
    {
        frame = new JFrame("POLICE LOGIN");
        lblWelcome = new JLabel("<HTML><h2>Welcome Officer!</h2></HTML>", JLabel.CENTER);
        lblUsername = new JLabel("<HTML><h3>Username: </h3></HTML>");
        lblPass = new JLabel("<HTML><h3>Password:</h3></HTML>");

        txtUser = new JTextField(60);
        txtPass = new JPasswordField(60);

        show_pass = new JCheckBox("Show password");
        show_pass.setForeground(new Color(255,189,68));

        show_pass.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.SELECTED){
                    txtPass.setEchoChar((char)0);
                }
                else{
                    txtPass.setEchoChar('*');
                }
            }
        });

        lblWelcome.setForeground(new Color(255,189,68));
        lblUsername.setForeground(new Color(255,189,68));
        lblPass.setForeground(new Color(255,189,68));

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(1, 145, 135));
        btnLogin.addActionListener(new CustomActionListener());

        frame.add(lblWelcome);
        frame.add(lblUsername);
        frame.add(txtUser);
        frame.add(lblPass);
        frame.add(txtPass);
        frame.add(show_pass);
        frame.add(btnLogin);

        frame.getContentPane().setBackground(new Color(45,45,45));
        frame.setSize(550,350);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(8,1));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public static void test_values() throws SQLException, ClassNotFoundException {
            System.out.println("Attempting to contact DB ... ");

            try {
              Class.forName("org.hsqldb.jdbc.JDBCDriver");
            } catch (ClassNotFoundException e) {
              throw e;
            }
            
            try {
                // will create DB if does not exist
                // "SA" is default user with hypersql
                con = DriverManager.getConnection(connectionString, "SA", "");
                
                ResultSet rs = con.createStatement().executeQuery("select * from fir");
                System.out.println("In FIR: ");
                while (rs.next()) {
                  System.out.println(rs.getString(1));
                  System.out.println(rs.getString(2));
                  System.out.println(rs.getString(3));
                  System.out.println(rs.getString(4));
                  System.out.println(rs.getString(5));
                  System.out.println(rs.getString(6));
                  System.out.println(rs.getString(7));
                  System.out.println(rs.getString(8));
                  System.out.println(rs.getString(9));
                }
                

              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
    }
    static class CustomActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String username = txtUser.getText();
            String password = txtPass.getText();
            if (username.trim().equals("test") && password.trim().equals("pass1")){
                try {
                    test_values();
                } catch (SQLException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(false);
                // adminpage.init();                
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Password");
            }
        }
    }
    
    static void init()
    {
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        police_login obj = new police_login();
        obj.display();
    }
    public static void main(String []args)
    {
        init();
    }
}

