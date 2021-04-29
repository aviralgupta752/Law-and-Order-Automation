package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.lang.reflect.InvocationTargetException;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
public class test_police_officer_data{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;
	static JPanel panel, panel1, panel2;
	static JLabel lblDrugs, lblAids, lblName, lblLenses, lblDOB, lblEmail, lblContact, lblAddress, lblGender, lblDepartment;
	static JTextField txtName, txtDOB, txtEmail, txtAddress, txtContact;
	static JComboBox txtGender, txtDrugs, txtAids, txtLenses, txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	
        // Setting up connection
        static Connection con;
        static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";
        
	public static void user_details()
	{
		frame = new JFrame("POLICE APPLICATION");
		panel = new JPanel(new GridLayout(11,1,10,10));
		panel1 = new JPanel(new GridLayout(11,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		//**************************************************************************************************************
		// USER DETAILS
		lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
		lblDOB = new JLabel("<HTML><h3>DOB: </h3></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblAddress = new JLabel("<HTML><h3>Address: </h3></HTML>", JLabel.CENTER);
		lblGender = new JLabel("<HTML><h3>Gender: </h3></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
		lblDrugs = new JLabel("<HTML><h3>Have you ever been dependant on drugs or alcohol?</h3></HTML>", JLabel.CENTER);
		lblAids = new JLabel("<HTML><h3>Do you use hearing aids?</h3></HTML>", JLabel.CENTER);
		lblLenses = new JLabel("<HTML><h3>Do you use glasses for your eyesight?</h3></HTML>", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
		btnCancel.setBackground(new Color(255,92,96));

		btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
		btnSubmit.setBackground(new Color(52,119,235));
		
		// btnCancel.addActionListener(new CustomActionListener());
		// btnSubmit.addActionListener(new CustomActionListener());

		txtName = new JTextField(60);
		txtDOB = new JTextField(60);
		txtEmail = new JTextField(60);
		txtContact = new JTextField(60);
		txtAddress = new JTextField(60);

		String txtDepartmentOptions[] = {"Women Protection", "Cybercrime", "Traffic & Control", "Law and Order"};
		txtDepartment = new JComboBox(txtDepartmentOptions);
		txtDepartment.setSelectedIndex(0);

		String txtGenderOptions[] = {"Male", "Female"};
		txtGender = new JComboBox(txtGenderOptions);
		txtGender.setSelectedIndex(0);

		String yesandno[] = {"No", "Yes"};
		txtDrugs = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

		txtAids = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

		txtLenses = new JComboBox(yesandno);
		txtGender.setSelectedIndex(0);

//		txtName.setInputVerifier(new PassVerifier());
//		txtEmail.setInputVerifier(new PassVerifier());
//		txtContact.setInputVerifier(new PassVerifier());

		panel2.add(new JLabel("<HTML><h1>POLICE APPLICATION</h1></HTML>", JLabel.CENTER));
		panel.add(lblName);			panel1.add(txtName);
		panel.add(lblDOB);			panel1.add(txtDOB);
		panel.add(lblGender);		panel1.add(txtGender);
		panel.add(lblDepartment);		panel1.add(txtDepartment);
		panel.add(lblEmail);		panel1.add(txtEmail);
		panel.add(lblContact);		panel1.add(txtContact);
		panel.add(lblAddress);		panel1.add(txtAddress);
		panel.add(lblDrugs);		panel1.add(txtDrugs);
		panel.add(lblAids);			panel1.add(txtAids);
		panel.add(lblLenses);		panel1.add(txtLenses);
		panel.add(btnCancel);		panel1.add(btnSubmit);
		panel2.add(panel);
		panel2.add(panel1);
		// panel.setBackground(new Color(45, 45, 45));
		panel2.setBackground(new Color(255,189,68));

		frame.setContentPane(panel2);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//**************************************************************************************************************
	}
//	static class PassVerifier extends InputVerifier {
//		public boolean verify(JComponent input)
//		{
//			if(input.equals(txtName)) 
//			{
//				String text = ((JTextField) input).getText();
//				if (text.matches("[a-zA-Z ]+")) // Reads: "Any of a-z or A-Z or space one or more times (together, not each)" ---> blank field or field containing anything other than those will return false.
//				{
//					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
//					return true;
//				}
//				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Uppercase and Lowercase letters only."));
//				return false;
//			}
//			else if(input.equals(txtEmail))
//			{
//				String text = ((JTextField) input).getText();
//				if (text.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
//				{
//					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
//					return true;
//				}
//				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Email."));
//				return false;
//			}
//			else if(input.equals(txtContact))
//			{
//				String text = ((JTextField) input).getText();
//				if (text.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$"))
//				{
//					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
//					return true;
//				}
//				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact."));
//				return false;
//			}
//			return true;
//		}
//	}
        
        public static void setValues() throws Exception {
//            URL tables_url = null;
//            URL insertVal_url = null;
            
            String createTables = readToString("sql/tables.sql");
            String addValues = readToString("sql/insertVal.sql");
            
//            String createTables = tables_url.toString();
//            String addValues = insertVal_url.toString();
//            System.out.println(tables_url);
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

//                 create tables
                con.createStatement().executeUpdate(createTables);

                // add random values in tables
                con.createStatement().executeUpdate(addValues);

//                ResultSet rs = con.createStatement().executeQuery("select * from administrator");
//                System.out.println("In Administrator: ");
//                while (rs.next()) {
//                  System.out.println(rs.getString(1));
//                }
//
//                rs = con.createStatement().executeQuery("select * from police_dept");
//                System.out.println("In Police_DEPT: ");
//                while (rs.next()) {
//                  System.out.print(rs.getString(1) + " ");
//                  System.out.print(rs.getString(2) + " ");
//                  System.out.print(rs.getString(3) + " ");
//                  System.out.print(rs.getString(4) + " ");
//                  System.out.print(rs.getString(5) + " ");
//                  System.out.print(rs.getString(6) + " ");
//                  System.out.println(rs.getString(7));
//                }
//
//                rs = con.createStatement().executeQuery("select * from fir");
//                System.out.println("In FIR: ");
//                while (rs.next()) {
//                  System.out.print(rs.getString(1) + " ");
//                  System.out.print(rs.getString(2) + " ");
//                  System.out.print(rs.getString(3) + " ");
//                  System.out.println(rs.getString(4));
//                }

                ResultSet rs = con.createStatement().executeQuery("select * from police_officer");
                System.out.println("In Police_OFFICER: ");
                
                while(rs.next()){
                    txtName.setText(rs.getString(2));
                    txtDOB.setText(rs.getString(3));
                    txtEmail.setText(rs.getString(4));
                    txtContact.setText(rs.getString(5));
                    txtAddress.setText(rs.getString(6));
                    txtGender.setSelectedItem(rs.getString(7));
                    txtDepartment.setSelectedItem(rs.getString(8));
                }
                
//                txtName.setText(temp);
//                rs = con.createStatement().executeQuery("select * from criminal");
//                System.out.println("In CRIMINAL: ");
//                while (rs.next()) {
//                  System.out.print(rs.getString(1) + " ");
//                  System.out.print(rs.getString(2) + " ");
//                  System.out.print(rs.getString(3) + " ");
//                  System.out.print(rs.getString(4) + " ");
//                  System.out.print(rs.getString(5) + " ");
//                  System.out.println(rs.getString(6));
//                }
//
//                rs = con.createStatement().executeQuery("select * from applic");
//                System.out.println("In APPLIC: ");
//                while (rs.next()) {
//                  System.out.print(rs.getString(1) + " ");
//                  System.out.print(rs.getString(2) + " ");
//                  System.out.print(rs.getString(3) + " ");
//                  System.out.print(rs.getString(4) + " ");
//                  System.out.println(rs.getString(5));
//                }

              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
        }
	static void init() throws Exception {
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		test_police_officer_data obj = new test_police_officer_data();
		obj.user_details();
                obj.setValues();
	}
        
	public static void main(String []args) throws Exception
	{
		init();
	}
        
        public static String readToString(String fname) throws Exception {
            File file = new File(fname);
            String string = FileUtils.readFileToString(file, "utf-8");
            return string;
      }
}
