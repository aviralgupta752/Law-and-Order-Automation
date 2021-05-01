package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import kanoon_ke_haath.police_officer_panel;
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
    
    public static void test_values(String username) throws SQLException, ClassNotFoundException {
            System.out.println("Attempting to contact DB ... ");

            try {
              Class.forName("org.hsqldb.jdbc.JDBCDriver");
            } catch (ClassNotFoundException e) {
              throw e;
            }
            
            try {
                String department="";
                // will create DB if does not exist
                // "SA" is default user with hypersql
                con = DriverManager.getConnection(connectionString, "SA", "");
                // Getting current officer's department
                String sql_dep="select PO_DEP from police_officer as t where t.PO_NAME=?";
                PreparedStatement pst_dep = con.prepareStatement(sql_dep);
                
                pst_dep.setString(1, username);
                ResultSet rs_dep=pst_dep.executeQuery();
                if(rs_dep.next()){
                    department=rs_dep.getString("PO_DEP");
                }
                // Getting all complaints with this department
                String sql_fir="select * from fir as t where t.FIR_DEP=?";
                PreparedStatement pst_fir = con.prepareStatement(sql_fir);
                
                pst_fir.setString(1, department);
                
                ResultSet rs_fir=pst_fir.executeQuery();
                while(rs_fir.next()){
                    System.out.println(rs_fir.getString(1));
                    System.out.println(rs_fir.getString(2));
                    System.out.println(rs_fir.getString(3));
                    System.out.println(rs_fir.getString(4));
                    System.out.println(rs_fir.getString(5));
                    System.out.println(rs_fir.getString(6));
                    System.out.println(rs_fir.getString(7));
                    System.out.println(rs_fir.getString(8));
                    System.out.println(rs_fir.getString(9));
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
            try {
              Class.forName("org.hsqldb.jdbc.JDBCDriver");
            } catch (ClassNotFoundException ae) {
                try {
                    throw ae;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            try {
                con = DriverManager.getConnection(connectionString, "SA", "");
                // Getting current officer's department
                String sql="select * from police_officer_list where USERNAME=? and PASSWORD=?";
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    try {
                        System.out.println("Logging in successfully");
//                    test_values(username);
                        police_officer_panel.main(new String[]{username});
                } catch (SQLException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame.setVisible(false);
                }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Password");
            }
            }  
            catch (SQLException ee) {
                try {
                    throw ee;
                } catch (SQLException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
              } finally {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
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

