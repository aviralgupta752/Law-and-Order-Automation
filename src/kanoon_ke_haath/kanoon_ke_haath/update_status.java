package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class update_status {
    static String officer_name_arg;
    static JFrame frame;
    static JLabel lblWelcome, lblPass, lblUsername;
    static JTextField txtUser;
    static JPasswordField txtPass;
    static JButton btnLogin, btnAdmin, btnSecurity;
    static JCheckBox show_pass;
    static JComboBox status;
    static String stat;
    static Connection con;
    static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";

    static void display(String officer_name) {
        officer_name_arg = officer_name;
        frame = new JFrame("UPDATE STATUS");
        lblWelcome = new JLabel("<HTML><h2>Welcome Officer!</h2></HTML>", JLabel.CENTER);
        lblUsername = new JLabel("<HTML><h3>Case ID: </h3></HTML>");
        lblPass = new JLabel("<HTML><h3>Password: </h3></HTML>");

        txtUser = new JTextField(60);
        txtPass = new JPasswordField(60);

        show_pass = new JCheckBox("Show password");
        show_pass.setForeground(new Color(255, 189, 68));

        show_pass.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtPass.setEchoChar((char) 0);
                } else {
                    txtPass.setEchoChar('*');
                }
            }
        });
        String choices[] = { "Unsolved", "Solved" };
        status = new JComboBox(choices);
        status.setSelectedIndex(0);

        lblWelcome.setForeground(new Color(255, 189, 68));
        lblUsername.setForeground(new Color(255, 189, 68));
        lblPass.setForeground(new Color(255, 189, 68));

        btnLogin = new JButton("Update");
        btnLogin.setBackground(new Color(1, 145, 135));
        btnLogin.addActionListener(new CustomActionListener());

        frame.add(lblWelcome);
        frame.add(lblUsername);
        frame.add(txtUser);
        frame.add(lblPass);
        frame.add(txtPass);
        frame.add(show_pass);
        frame.add(status);
        frame.add(btnLogin);

        frame.getContentPane().setBackground(new Color(45, 45, 45));
        frame.setSize(550, 350);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(9, 1));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getRootPane().setDefaultButton(btnLogin);
    }

    public static void test_values(String fir_id) throws SQLException, ClassNotFoundException {
        System.out.println("Attempting to contact DB ... ");

        try {
            // Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw e;
        }

        try {
            // con = DriverManager.getConnection(connectionString, "SA", "");
            con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test", "police", "Policemgmt@7police");

            // Updating value of fir_id status
            String stat = String.valueOf(status.getSelectedItem());
            System.out.println("Got selected status " + stat);
            int stat_int = 0;
            if (stat == "Solved") {
                stat_int = 1;
            }
            String sql_fir = "update fir set FIR_STAT=? where FIR_ID=?";
            PreparedStatement pst_fir = con.prepareStatement(sql_fir);

            pst_fir.setInt(1, stat_int);
            pst_fir.setString(2, fir_id);

            pst_fir.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            con.close();
        }
    }

    static class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = txtUser.getText();
            String password = txtPass.getText();
            try {
                // Class.forName("org.hsqldb.jdbc.JDBCDriver");
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ae) {
                try {
                    throw ae;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                // con = DriverManager.getConnection(connectionString, "SA", "");
                con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test", "police", "Policemgmt@7police");
                // Getting current officer's department
                String sql = "select * from police_officer_list where USERNAME=? and PASSWORD=?";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, officer_name_arg);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    try {
                        System.out.println("Logging in successfully");
                        test_values(username);
                    } catch (SQLException ex) {
                        Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(police_login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                }
            } catch (SQLException ee) {
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

    public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if (e.getSource() == status) {
            stat = status.getSelectedItem().toString();

        }
    }

    static void init(String officer_name) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        update_status obj = new update_status();
        obj.display(officer_name);
    }

    public static void main(String[] args) {
        String officer_name = args[0];
        System.out.println("Argument made it to police_officer_panel main " + officer_name);
        init(officer_name);
    }
}
