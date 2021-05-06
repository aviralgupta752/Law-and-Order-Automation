package kanoon_ke_haath;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import kanoon_ke_haath.track_complaint;
public class track_complaint_verification
{
    static JFrame frame;
    static JLabel lblWelcome, lblPass;
    static JTextField txtUser;
    static JPasswordField txtPass;
    static JButton btnLogin, btnAdmin, btnSecurity;
    static JCheckBox show_pass;
    static String uid;
    static Connection con;
    static void display()
    {
        frame = new JFrame("Track Complaint");
        lblWelcome = new JLabel("<HTML><h2>Welcome!</h2></HTML>", JLabel.CENTER);
        lblPass = new JLabel("<HTML><h3>Enter your unique tracking id:</h3></HTML>");
        txtPass = new JPasswordField(60);

        show_pass = new JCheckBox("Show id");
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
        lblPass.setForeground(new Color(255,189,68));

        btnLogin = new JButton("Submit");
        btnLogin.setBackground(new Color(1, 145, 135));
        btnLogin.addActionListener(new CustomActionListener());

        frame.add(lblWelcome);
        frame.add(lblPass);
        frame.add(txtPass);
        frame.add(show_pass);
        frame.add(btnLogin);

        frame.getContentPane().setBackground(new Color(45,45,45));
        frame.setSize(550,350);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(6,1));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(btnLogin);
    }
    
    static int check_uid(String uid) throws ClassNotFoundException, SQLException{
        System.out.println("Attempting to contact DB ... ");

            try {
              Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
              throw e;
            }
            
            try {
                con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test","police","Policemgmt@7police");
                // Getting info of selected police department
                String sql_track="select * from fir as t where t.FIR_TRACK=?";
                PreparedStatement pst_track = con.prepareStatement(sql_track);
                pst_track.setString(1, uid);
                
                ResultSet rs_track = pst_track.executeQuery();
                
                if(rs_track.next()){
                    return 1;
                }
                else{
                    return 0;
                }
              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
    }
    static class CustomActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String password = txtPass.getText();
            uid  = password;
            try {
                if (check_uid(password) == 1){
                    frame.setVisible(false);
                    track_complaint.main(new String[]{uid});
                }
                else{
                    JOptionPane.showMessageDialog(null, "Tracking id doesn't exist.");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(track_complaint_verification.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(track_complaint_verification.class.getName()).log(Level.SEVERE, null, ex);
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

        track_complaint_verification obj = new track_complaint_verification();
        obj.display();
    }
    public static void main(String []args)
    {
        init();
    }
}
