package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.Border;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class police_application {
	static JFrame frame;
	static JButton btnSubmit, btnCancel;
	static JPanel panel, panel1, panel2;
	static JLabel lblDrugs, lblAid, lblName, lblLenses, lblDOB, lblEmail, lblContact, lblPoliceStation, lblGender,
			lblDepartment;
	static JTextField txtName, txtDOB, txtEmail, txtPoliceStation, txtContact;
	static JComboBox txtGender, txtDrugs, txtAid, txtLenses, txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);

	static Connection con;
	static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";

	public static void user_details() {
		frame = new JFrame("POLICE APPLICATION");
		panel = new JPanel(new GridLayout(11, 1, 10, 10));
		panel1 = new JPanel(new GridLayout(11, 1, 10, 10));
		panel2 = new JPanel(new GridLayout(1, 3, 0, 0));

		// **************************************************************************************************************
		// USER DETAILS
		lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
		lblDOB = new JLabel("<HTML><h3>DOB: </h3></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblPoliceStation = new JLabel("<HTML><h3>Name of police station: </h3></HTML>", JLabel.CENTER);
		lblGender = new JLabel("<HTML><h3>Gender: </h3></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
		lblDrugs = new JLabel("<HTML><h3>Have you ever been dependant on drugs or alcohol?</h3></HTML>", JLabel.CENTER);
		lblAid = new JLabel("<HTML><h3>Do you use hearing aids?</h3></HTML>", JLabel.CENTER);
		lblLenses = new JLabel("<HTML><h3>Do you use glasses for your eyesight?</h3></HTML>", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
		btnCancel.setBackground(new Color(255, 92, 96));

		btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
		btnSubmit.setBackground(new Color(52, 119, 235));

		btnCancel.addActionListener(new CustomActionListener());
		btnSubmit.addActionListener(new CustomActionListener());

		txtName = new JTextField(60);
		txtDOB = new JTextField(60);
		txtEmail = new JTextField(60);
		txtContact = new JTextField(60);
		txtPoliceStation = new JTextField(60);

		String txtDepartmentOptions[] = { "Women Protection", "Cybercrime", "Traffic & Control", "Law and Order" };
		txtDepartment = new JComboBox(txtDepartmentOptions);
		txtDepartment.setSelectedIndex(0);

		String txtGenderOptions[] = { "Male", "Female" };
		txtGender = new JComboBox(txtGenderOptions);
		txtGender.setSelectedIndex(0);

		String yesandno[] = { "No", "Yes" };
		txtDrugs = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

		txtAid = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

		txtLenses = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

		txtName.setInputVerifier(new PassVerifier());
		txtEmail.setInputVerifier(new PassVerifier());
		txtContact.setInputVerifier(new PassVerifier());

		panel2.add(new JLabel("<HTML><h1>POLICE APPLICATION</h1></HTML>", JLabel.CENTER));
		panel.add(lblName);
		panel1.add(txtName);
		panel.add(lblDOB);
		panel1.add(txtDOB);
		panel.add(lblGender);
		panel1.add(txtGender);
		panel.add(lblDepartment);
		panel1.add(txtDepartment);
		panel.add(lblEmail);
		panel1.add(txtEmail);
		panel.add(lblContact);
		panel1.add(txtContact);
		panel.add(lblPoliceStation);
		panel1.add(txtPoliceStation);
		panel.add(lblDrugs);
		panel1.add(txtDrugs);
		panel.add(lblAid);
		panel1.add(txtAid);
		panel.add(lblLenses);
		panel1.add(txtLenses);
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
			if (input.equals(txtName)) {
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
			} else if (input.equals(txtEmail)) {
				String text = ((JTextField) input).getText();
				if (text.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Email."));
				return false;
			} else if (input.equals(txtContact)) {
				String text = ((JTextField) input).getText();
				if (text.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$")) {
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact."));
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
					Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
				} catch (SQLException ex) {
					Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
				}
				JOptionPane.showMessageDialog(null, "Police Officer Registered");
				frame.setVisible(false);
			} else if (source == btnCancel) {
				frame.setVisible(false);
			}
		}
	}

	public static void add_values() throws ClassNotFoundException, SQLException {
		System.out.println("Attempting to contact DB ... ");
		try {
			// Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw e;
		}
		try {
			// will create DB if does not exist
			// "SA" is default user with hypersql
			// con = DriverManager.getConnection(connectionString, "SA", "");
			con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test", "police", "Policemgmt@7police");
			// Creating police officer entry
			String name = txtName.getText();
			String dob = txtDOB.getText();
			String gender = String.valueOf(txtGender.getSelectedItem());
			String email = txtEmail.getText();
			String contact = txtContact.getText();
			String police_station = txtPoliceStation.getText();
			String dep = String.valueOf(txtDepartment.getSelectedItem());
			String drugs = String.valueOf(txtDrugs.getSelectedItem());
			String aid = String.valueOf(txtAid.getSelectedItem());
			String lenses = String.valueOf(txtLenses.getSelectedItem());
			// String query = "INSERT INTO fir (PO_NAME, PO_DOB, PO_GENDER, PO_DEP,
			// PO_EMAIL, PO_CONTACT, PO_ADDR, PO_DRUGS, PO_AID, PO_LENSES)
			// VALUES('"+name+"','"+dob+"','"+gender+"','"++email+"','"+contact+"','"+address+"','"+dep+"','"+desc+"');";
			// result = stmt.executeUpdate(query);

			String sql_po = "INSERT INTO police_officer (PO_NAME, PO_DOB, PO_GENDER, PO_DEP, PO_EMAIL, PO_CONTACT, PO_PS, PO_DRUGS, PO_AID, PO_LENSES) VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst_po = con.prepareStatement(sql_po);

			pst_po.setString(1, name);
			pst_po.setString(2, dob);
			pst_po.setString(3, gender);
			pst_po.setString(4, dep);
			pst_po.setString(5, email);
			pst_po.setString(6, contact);
			pst_po.setString(7, police_station);
			pst_po.setString(8, drugs);
			pst_po.setString(9, aid);
			pst_po.setString(10, lenses);

			pst_po.executeUpdate();

			// Creating police officer creds
			String sql_creds = "INSERT INTO police_officer_list (USERNAME, PASSWORD) VALUES(?,?)";
			PreparedStatement pst_creds = con.prepareStatement(sql_creds);
			pst_creds.setString(1, name);
			pst_creds.setString(2, name);

			pst_creds.executeUpdate();

			// Creating
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
		police_application obj = new police_application();
		obj.user_details();
	}

	public static void main(String[] args) {
		init();
	}
}
