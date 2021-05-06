package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.io.FileUtils;

public class register_criminal {
  static JFrame frame;
  static JButton btnSubmit, btnCancel;

  static JPanel panel, panel1, panel2;

  static JTextArea txtArea;

  static JLabel lblName, lblAge, lblAddress, lblContact, lblDOC, lblDesc, lblDepartment, lblGender, lblPS;
  static JTextArea txtDesc;
  static JTextField txtName, txtAge, txtAddress, txtContact, txtDOC, txtPS;
  static JComboBox txtDepartment, txtGender;
  static Border redline = BorderFactory.createLineBorder(Color.RED);

  static Connection con;

  public static void user_details() {
    frame = new JFrame("FILL DETAILS");
    panel = new JPanel(new GridLayout(10, 1, 10, 10));
    panel1 = new JPanel(new GridLayout(10, 1, 10, 10));
    panel2 = new JPanel(new GridLayout(1, 3, 0, 0));

    // **************************************************************************************************************
    // USER DETAILS
    lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
    lblAge = new JLabel("<HTML><h3>Age: </h3></HTML>", JLabel.CENTER);
    lblGender = new JLabel("<HTML><h3>Gender: </h3></HTML>", JLabel.CENTER);
    lblAddress = new JLabel("<HTML><h3>Address: </h3></HTML>", JLabel.CENTER);
    lblDOC = new JLabel("<HTML><h3>Date of Crime: </h3></HTML>", JLabel.CENTER);
    lblDesc = new JLabel("<HTML><h3>Crime: </h3></HTML>", JLabel.CENTER);
    lblDepartment = new JLabel("<HTML><h3>Case department: </h3></HTML>", JLabel.CENTER);
    lblPS = new JLabel("<HTML><h3>Police Station: </h3></HTML>", JLabel.CENTER);
    lblContact = new JLabel("<HTML><h3>Emergency contact: </h3></HTML>", JLabel.CENTER);

    btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
    btnCancel.setBackground(new Color(255, 92, 96));

    btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
    btnSubmit.setBackground(new Color(52, 119, 235));

    btnCancel.addActionListener(new CustomActionListener());
    btnSubmit.addActionListener(new CustomActionListener());

    txtName = new JTextField(60);
    txtAge = new JTextField(60);
    txtAddress = new JTextField(100);
    txtContact = new JTextField(60);
    txtDOC = new JTextField(60);
    txtPS = new JTextField(60);

    txtDesc = new JTextArea(5, 20);
    JScrollPane scrollPane = new JScrollPane(txtArea);
    txtDesc.setEditable(true);

    String txtDepartmentOptions[] = { "Women Protection", "Cybercrime", "Traffic & Control", "Law and Order" };
    txtDepartment = new JComboBox(txtDepartmentOptions);
    txtDepartment.setSelectedIndex(0);

    String GenderOptions[] = { "Male", "Female", "Other" };
    txtGender = new JComboBox(GenderOptions);
    txtGender.setSelectedIndex(0);

    txtName.setInputVerifier(new PassVerifier());
    txtAge.setInputVerifier(new PassVerifier());
    txtContact.setInputVerifier(new PassVerifier());
    txtDOC.setInputVerifier(new PassVerifier());
    txtPS.setInputVerifier(new PassVerifier());
    txtAddress.setInputVerifier(new PassVerifier());

    panel2.add(new JLabel("<HTML><h1>Criminal resgistration</h1></HTML>", JLabel.CENTER));
    panel.add(lblName);
    panel1.add(txtName);
    panel.add(lblAge);
    panel1.add(txtAge);
    panel.add(lblGender);
    panel1.add(txtGender);
    panel.add(lblAddress);
    panel1.add(txtAddress);
    panel.add(lblDOC);
    panel1.add(txtDOC);
    panel.add(lblDesc);
    panel1.add(txtDesc);
    panel.add(lblDepartment);
    panel1.add(txtDepartment);
    panel.add(lblPS);
    panel1.add(txtPS);
    panel.add(lblContact);
    panel1.add(txtContact);
    panel.add(btnCancel);
    panel1.add(btnSubmit);
    panel2.add(panel);
    panel2.add(panel1);
    // panel.setBackground(new Color(45, 45, 45));
    panel2.setBackground(new Color(255, 189, 68));

    frame.setContentPane(panel2);
    frame.setSize(1920, 1080);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    frame.getRootPane().setDefaultButton(btnSubmit);
    // **************************************************************************************************************
  }

  static class PassVerifier extends InputVerifier {
    public boolean verify(JComponent input) {
      if (input.equals(txtName) || input.equals(txtPS)) {

        String text = ((JTextField) input).getText();

        if (text.matches("[a-zA-Z ]+")) // Reads: "Any of a-z or A-Z or space one or more times (together, not each)"
        // ---> blank field or field containing anything other than those will return
        // false.
        {
          ((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
          return true;
        }
        ((JTextField) input)
            .setBorder(BorderFactory.createTitledBorder(redline, "Uppercase and Lowercase letters only."));
        return false;
      } else if (input.equals(txtContact)) {
        String text = ((JTextField) input).getText();
        if (text.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")) {
          ((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
          return true;
        }
        ((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact."));
        return false;
      } else if (input.equals(txtDOC)) {
        String text = ((JTextField) input).getText();
        if (text.matches("[0-9]+")) {
          ((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
          return true;
        }
        ((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Date."));
        return false;
      } else if (input.equals(txtAge)) {
        String text = ((JTextField) input).getText();
        if (text.matches("[0-9]+") && text.length() < 4) {
          ((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
          return true;
        }
        ((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Age."));
        return false;
      }
      return true;
    }
  }

  static class CustomActionListener implements ActionListener {
    public void actionPerformed(ActionEvent ae) {
      Object source = ae.getSource();

      if (source == btnSubmit) {
        try {
          add_values();
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(register_criminal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
          Logger.getLogger(register_criminal.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Application Submitted");
        frame.setVisible(false);
      } else if (source == btnCancel) {
        frame.setVisible(false);
      }
    }
  }

  public static void add_values() throws ClassNotFoundException, SQLException {
    System.out.println("Attempting to contact DB ... ");

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw e;
    }

    try {
      con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test","police","Policemgmt@7police");

      String name = txtName.getText();
      String contact = txtContact.getText();
      int age = Integer.valueOf(txtAge.getText());
      String doc = txtDOC.getText();
      String desc = txtDesc.getText();
      String dep = String.valueOf(txtDepartment.getSelectedItem());
      String gend = String.valueOf(txtGender.getSelectedItem());
      String add = txtAddress.getText();
      String ps = txtPS.getText();

      String sql = "INSERT INTO criminal (CRIM_NAME, CRIM_AGE, CRIM_GENDER, CRIM_ADDRESS, CRIM_CONTACT, CRIM_DOC, CRIM_DESC, CRIM_CRIME_DEP, CRIM_PS) VALUES(?,?,?,?,?,?,?,?,?)";
      PreparedStatement pst = con.prepareStatement(sql);

      pst.setString(1, name);
      pst.setInt(2, age);
      pst.setString(3, gend);
      pst.setString(4, add);
      pst.setString(5, contact);
      pst.setString(6, doc);
      pst.setString(7, desc);
      pst.setString(8, dep);
      pst.setString(9, ps);

      pst.executeUpdate();

      System.out.println("Database updated, check it.");
    } catch (SQLException e) {
      throw e;
    } finally {
      con.close();
    }
  }

  static void init() {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    register_criminal obj = new register_criminal();
    obj.user_details();
  }

  public static void main(String[] args) {
    init();
  }
}
