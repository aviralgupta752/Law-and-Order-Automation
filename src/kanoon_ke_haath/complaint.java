package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class complaint {
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2;

	static JTextArea txtArea;

	static JLabel lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPS, lblDepartment, lblDesc;
	static JTextField txtName, txtFatherName, txtEmail, txtDOI, txtPS, txtContact;
	static JComboBox txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);

	static Connection con;
	static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";

	public static void user_details() {
		frame = new JFrame("FILL DETAILS");
		panel = new JPanel(new GridLayout(10, 1, 10, 10));
		panel1 = new JPanel(new GridLayout(10, 1, 10, 10));
		panel2 = new JPanel(new GridLayout(1, 3, 0, 0));

		// **************************************************************************************************************
		// USER DETAILS
		lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
		lblFatherName = new JLabel("<HTML><h3>Father's / Husband's Name: </h3></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblDOI = new JLabel("<HTML><h3>Date of Issue: </h3></HTML>", JLabel.CENTER);
		lblPS = new JLabel("<HTML><h3>Name of police station </h3></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
		lblDesc = new JLabel("<HTML><h3>Description: </h3></HTML>", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
		btnCancel.setBackground(new Color(255, 92, 96));

		btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
		btnSubmit.setBackground(new Color(52, 119, 235));

		btnCancel.addActionListener(new CustomActionListener());
		btnSubmit.addActionListener(new CustomActionListener());

		txtName = new JTextField(60);
		txtFatherName = new JTextField(60);
		txtEmail = new JTextField(60);
		txtContact = new JTextField(60);
		txtDOI = new JTextField(60);
		txtPS = new JTextField(60);

		txtArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		txtArea.setEditable(true);

		String txtDepartmentOptions[] = { "Women Protection", "Cybercrime", "Traffic & Control", "Law and Order" };
		txtDepartment = new JComboBox(txtDepartmentOptions);
		txtDepartment.setSelectedIndex(0);

		txtName.setInputVerifier(new PassVerifier());
		txtFatherName.setInputVerifier(new PassVerifier());
		txtEmail.setInputVerifier(new PassVerifier());
		txtContact.setInputVerifier(new PassVerifier());
		txtDOI.setInputVerifier(new PassVerifier());
		txtPS.setInputVerifier(new PassVerifier());

		panel2.add(new JLabel(
				"<HTML><h1>What is a police complaint?</h1><h3>A Police Complaint is initial reporting of any crime or offence. "
						+ "It is a narration of facts about the incident in layman’s words.</h3><h1>When can a police complaint be filed?</h1><h3>One should "
						+ "ideally file a police complaint immediately after the incident/crime have taken place. In certain cases of sexual harassment, "
						+ "domestic violence, rape, etc. women usually take time to come out and report the crime/incident due to mental trauma. "
						+ "In such cases, even if there is a delay one can file a police complaint.</h3><h1>Who can file a Police Complaint?</h1><h3>Anyone "
						+ "can file a police complaint. The victim of the crime, victim’s family members, friends or any witness to the crime can file a police "
						+ "complaint.</h3></HTML>",
				JLabel.CENTER));
		panel.add(lblName);
		panel1.add(txtName);
		panel.add(lblFatherName);
		panel1.add(txtFatherName);
		panel.add(lblEmail);
		panel1.add(txtEmail);
		panel.add(lblContact);
		panel1.add(txtContact);
		panel.add(lblDOI);
		panel1.add(txtDOI);
		panel.add(lblPS);
		panel1.add(txtPS);
		panel.add(lblDepartment);
		panel1.add(txtDepartment);
		panel.add(lblDesc);
		panel1.add(scrollPane);
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
		// **************************************************************************************************************
	}

	static class PassVerifier extends InputVerifier {
		public boolean verify(JComponent input) {
			if (input.equals(txtName) || input.equals(txtFatherName) || input.equals(txtPS)) {
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
			} else if (input.equals(txtDOI)) {
				String text = ((JTextField) input).getText();
				if (text.matches("[0-9]+")) {
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Number."));
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
				JOptionPane.showMessageDialog(null, "Complaint Registered");
				frame.setVisible(false);
			} else if (source == btnCancel) {
				frame.setVisible(false);
			}
		}
	}

	public static void add_values() throws ClassNotFoundException, SQLException {
		System.out.println("Attempting to contact DB ... ");

		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			throw e;
		}
		try {
			// will create DB if does not exist
			// "SA" is default user with hypersql
			con = DriverManager.getConnection(connectionString, "SA", "");
			stmt = con.createStatement();

			String name = txtName.getText();
			String fname = txtFatherName.getText();
			String contact = txtContact.getText();
			String email = txtEmail.getText();
			String doi = txtDOI.getText();
			String ps = txtPS.getText();
			String dep = String.valueOf(txtDepartment.getSelectedItem());
			String desc = txtArea.getText();
			int stat = 0;

			String sql = "INSERT INTO fir (FIR_NAME, FIR_FNAME, FIR_EMAIL, FIR_CONTACT, FIR_DOI, FIR_PS, FIR_DEP, FIR_DESC, FIR_STAT, FIR_EMAIL_SENT, FIR_PO_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, name);
			pst.setString(2, fname);
			pst.setString(3, email);
			pst.setString(4, contact);
			pst.setString(5, doi);
			pst.setString(6, ps);
			pst.setString(7, dep);
			pst.setString(8, desc);
			pst.setInt(9, stat);
			pst.setInt(10, stat);
			pst.setString(11, "");

			pst.executeUpdate();
			System.out.println("fir_po_id working fine");
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
		complaint obj = new complaint();
		obj.user_details();
	}

	public static void main(String[] args) {
		init();
	}
}
