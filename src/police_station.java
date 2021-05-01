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

public class police_station{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2;
	
	static JLabel lblPSName, lblLocation, lblCity, lblArea, lblPhone, lblRemarks, lblspace;
	static JTextField txtPSName, txtLocation, txtCity, txtArea, txtPhone, txtRemarks;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	
        static Connection con;
        static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";
	public static void user_details()
	{
		frame = new JFrame("FILL DETAILS");
		panel = new JPanel(new GridLayout(7,1,10,10));
		panel1 = new JPanel(new GridLayout(7,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		lblPSName = new JLabel("<HTML><h3>PS Name: </h3></HTML>", JLabel.CENTER);
		lblLocation = new JLabel("<HTML><h3>Location: </h3></HTML>", JLabel.CENTER);
		lblCity = new JLabel("<HTML><h3>City: </h3></HTML>", JLabel.CENTER);
		lblArea = new JLabel("<HTML><h3>Area: </h3></HTML>", JLabel.CENTER);
		lblPhone = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblRemarks = new JLabel("<HTML><h3>Remarks: </h3></HTML>", JLabel.CENTER);
		lblspace = new JLabel("<HTML><h3>  : </h3></HTML>", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
		btnCancel.setBackground(new Color(255,92,96));

		btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
		btnSubmit.setBackground(new Color(52,119,235));
		
		 btnCancel.addActionListener(new CustomActionListener());
		 btnSubmit.addActionListener(new CustomActionListener());

		txtPSName = new JTextField(60);
		txtLocation = new JTextField(60);
		txtCity = new JTextField(60);
		txtArea = new JTextField(60);
		txtPhone = new JTextField(60);
		txtRemarks = new JTextField(60);

		txtPhone.setInputVerifier(new PassVerifier());
		
		panel2.add(new JLabel("<HTML><h1>FILL DETAILS</h1><h3><i><center>Wicked Is Good</center></i></h3></HTML>", JLabel.CENTER));
		panel.add(lblPSName);		panel1.add(txtPSName);
		panel.add(lblLocation);		panel1.add(txtLocation);
		panel.add(lblCity);			panel1.add(txtCity);
		panel.add(lblArea);			panel1.add(txtArea);
		panel.add(lblPhone);		panel1.add(txtPhone);
		panel.add(lblRemarks);		panel1.add(txtRemarks);
		panel.add(btnCancel);		panel1.add(btnSubmit);
		panel2.add(panel);
		panel2.add(panel1);
		
		panel2.setBackground(new Color(255,189,68));

		frame.setContentPane(panel2);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class PassVerifier extends InputVerifier {
		public boolean verify(JComponent input)
		{
			if(input.equals(txtPhone))
			{
				String text = ((JTextField) input).getText();
				if (text.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$"))
				{
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact"));
				return false;
			}
			return true;
		}
	}
        
        public static void add_values() throws ClassNotFoundException, SQLException {
            System.out.println("Attempting to contact DB ... ");
            
            Statement stmt = null;
            int result=0;
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
                
                String name = txtPSName.getText();
                String location = txtLocation.getText();
                String city = txtCity.getText();
                String area = txtArea.getText();
                String phone = txtPhone.getText();
                String remarks = txtRemarks.getText();
//                String query = "INSERT INTO police_dept (PD_LOC, PD_CITY, PD_AREA, PD_PHONE, PDREMARKS) VALUES('"+name+"','"+location+"','"+city+"','"+area+"','"+phone+"','"+remarks+"');";
//                result = stmt.executeUpdate(query);
//                con.commit();
                String sql="INSERT INTO police_dept (PD_NAME, PD_LOC, PD_CITY, PD_AREA, PD_PHONE, PD_REMARKS) VALUES(?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setString(1, name);
                pst.setString(2, location);
                pst.setString(3, city);
                pst.setString(4, area);
                pst.setString(5, phone);
                pst.setString(6, remarks);
                pst.executeUpdate();

              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
        }
        
        static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();

			if(source == btnSubmit){
                            try {
                                add_values();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Police Station Registered");
                            frame.setVisible(false);
                        }
                        else if(source == btnCancel){
                            frame.setVisible(false);
                        }
		}
	}
	static void init() {
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		police_station obj = new police_station();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}