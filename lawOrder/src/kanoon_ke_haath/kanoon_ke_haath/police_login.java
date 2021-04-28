package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;

class police_login {
  static JFrame frame;
  static JLabel lblWelcome, lblPass, lblUsername;
  static JTextField txtUser;
  static JPasswordField txtPass;
  static JButton btnLogin, btnAdmin, btnSecurity;
  static JCheckBox show_pass;

  static void display() {
    frame = new JFrame("POLICE LOGIN");
    lblWelcome = new JLabel("<HTML><h2>Welcome Officer!</h2></HTML>", JLabel.CENTER);
    lblUsername = new JLabel("<HTML><h3>Username: </h3></HTML>");
    lblPass = new JLabel("<HTML><h3>Password:</h3></HTML>");

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

    lblWelcome.setForeground(new Color(255, 189, 68));
    lblUsername.setForeground(new Color(255, 189, 68));
    lblPass.setForeground(new Color(255, 189, 68));

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

    frame.getContentPane().setBackground(new Color(45, 45, 45));
    frame.setSize(550, 350);
    frame.setVisible(true);
    frame.setLayout(new GridLayout(8, 1));
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
  }

  static class CustomActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String username = txtUser.getText();
      String password = txtPass.getText();
      if (password.trim().equals("pass1")) {
        frame.setVisible(false);
        // adminpage.init();
      } else {
        JOptionPane.showMessageDialog(null, "Incorrect Password");
      }
    }
  }

  static void init() {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }

    police_login obj = new police_login();
    obj.display();
  }

  public static void main(String[] args) {
    init();
  }
}
