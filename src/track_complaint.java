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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class track_complaint{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2;

//	static JTextArea txtArea;
	
	static JLabel lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPS, lblDepartment, lblDesc, lblStatus;
	static JLabel txtName, txtFatherName, txtEmail, txtDOI, txtPS, txtContact, txtDepartment, txtArea, txtStatus;
//	static JComboBox txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
        
        static String name, fname, contact, email, doi, ps, dep, desc, status;
        static int stat;
        static Connection con;
        static UUID idOne;
	public static void user_details(String uid) throws ClassNotFoundException, SQLException
	{   
                System.out.println("uid in user_details: " + uid);
		frame = new JFrame("FILL DETAILS");
		panel = new JPanel(new GridLayout(12,1,10,10));
		panel1 = new JPanel(new GridLayout(10,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		//**************************************************************************************************************
		// USER DETAILS
			lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
			lblFatherName = new JLabel("<HTML><h3>Father's / Husband's Name: </h3></HTML>", JLabel.CENTER);
			lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
			lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
			lblDOI = new JLabel("<HTML><h3>Date of Issue: </h3></HTML>", JLabel.CENTER);
			lblPS = new JLabel("<HTML><h3>Name of police station </h3></HTML>", JLabel.CENTER);
			lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
			lblDesc  = new JLabel("<HTML><h3>Description: </h3></HTML>", JLabel.CENTER);
                        lblStatus  = new JLabel("<HTML><h3>Status of complaint: </h3></HTML>", JLabel.CENTER);
                        
			btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
			btnCancel.setBackground(new Color(255,92,96));

//			btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
//			btnSubmit.setBackground(new Color(52,119,235));
			
			btnCancel.addActionListener(new CustomActionListener());
//			btnSubmit.addActionListener(new CustomActionListener());

//			txtArea = new JTextArea(5, 20);
//			JScrollPane scrollPane = new JScrollPane(txtArea); 
//			txtArea.setEditable(true);

//			String txtDepartmentOptions[] = {"Women Protection", "Cybercrime", "Traffic & Control", "Law and Order"};
//			txtDepartment = new JComboBox(txtDepartmentOptions);
//			txtDepartment.setSelectedIndex(0);
                        txtName = new JLabel("test");
			txtFatherName = new JLabel("test");
			txtEmail = new JLabel("test");
			txtContact = new JLabel("test");
			txtDOI = new JLabel("test");
			txtPS = new JLabel("test");
                        txtArea = new JLabel("test");
                        txtDepartment = new JLabel("test");
                        txtStatus = new JLabel("test");

			panel2.add(new JLabel("<HTML><h1>What is a police complaint?</h1><h3>A Police Complaint is initial reporting of any crime or offence. "
                                + "It is a narration of facts about the incident in layman’s words.</h3><h1>When can a police complaint be filed?</h1><h3>One should "
                                + "ideally file a police complaint immediately after the incident/crime have taken place. In certain cases of sexual harassment, "
                                + "domestic violence, rape, etc. women usually take time to come out and report the crime/incident due to mental trauma. "
                                + "In such cases, even if there is a delay one can file a police complaint.</h3><h1>Who can file a Police Complaint?</h1><h3>Anyone "
                                + "can file a police complaint. The victim of the crime, victim’s family members, friends or any witness to the crime can file a police "
                                + "complaint.</h3></HTML>", JLabel.CENTER));
//                        panel2.add(stat);
//                        panel2.add("jProgressBar", jProgressBar);
                        
                        ArrayList<String> values = get_values(uid);
                        
			panel.add(lblName);			panel1.add(txtName);
			panel.add(lblFatherName);	panel1.add(txtFatherName);
			panel.add(lblEmail);		panel1.add(txtEmail);
			panel.add(lblContact);		panel1.add(txtContact);
			panel.add(lblDOI);			panel1.add(txtDOI);
			panel.add(lblPS);			panel1.add(txtPS);
			panel.add(lblDepartment);	panel1.add(txtDepartment);
			panel.add(lblDesc);             panel1.add(txtArea);
                        panel.add(lblStatus);           panel1.add(txtStatus);                  
			panel.add(btnCancel);		
			panel2.add(panel);
                        
			panel2.add(panel1);
			// panel.setBackground(new Color(45, 45, 45));
			panel2.setBackground(new Color(255,189,68));

			frame.setContentPane(panel2);
                        
			frame.setSize(1920,1080);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        
            for(String x : values){
                System.out.println(x);
            }
                        txtName.setText(values.get(0));
			txtFatherName.setText(values.get(1));
			txtEmail.setText(values.get(2));
			txtContact.setText(values.get(3));
			txtDOI.setText(values.get(4));
			txtPS.setText(values.get(5));
                        txtArea.setText(values.get(6));
                        txtDepartment.setText(values.get(7));
                        txtStatus.setText(values.get(8));
		//**************************************************************************************************************
	}
        
        static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
                        if(source == btnCancel){
                            frame.setVisible(false);
                        }
		}
	}
        
        public static ArrayList<String> get_values(String uid) throws ClassNotFoundException, SQLException {
            System.out.println("Attempting to contact DB ... ");
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
              throw e;
            }
            try {
                con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test","police","Policemgmt@7police");
                System.out.println("Got uid: " + uid);
                String sql_fir="select FIR_NAME, FIR_FNAME, FIR_EMAIL, FIR_CONTACT, FIR_DOI, FIR_PS, FIR_DEP, FIR_DESC, FIR_STAT from fir where FIR_TRACK=?";
                PreparedStatement pst_fir = con.prepareStatement(sql_fir);
                pst_fir.setString(1, uid);
                
                ResultSet rs_fir = pst_fir.executeQuery();
                if(rs_fir.next()){
                    name = rs_fir.getString(1);
                    fname = rs_fir.getString(2);
                    email = rs_fir.getString(3);
                    contact = rs_fir.getString(4);
                    doi = rs_fir.getString(5);
                    ps = rs_fir.getString(6);
                    dep = rs_fir.getString(7);
                    desc = rs_fir.getString(8);
                    stat = rs_fir.getInt(9);
                }
                System.out.println("Got name: " + name);
              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
                ArrayList<String> values = new ArrayList<String>();
                values.add(name);
                values.add(fname);
                values.add(email);
                values.add(contact);
                values.add(doi);
                values.add(ps);
                values.add(dep);
                values.add(desc);
                if(stat == 1){
                    status = "Solved";
                }
                else{
                    status = "Unsolved";
                }
                values.add(status);
                System.out.println("values in get_values method");
                for(String x : values){
                System.out.println(x);
            }
                return values;
        }
	static void init(String uid) throws ClassNotFoundException, SQLException {
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		track_complaint obj = new track_complaint();
		obj.user_details(uid);
	}
	public static void main(String []args) throws ClassNotFoundException, SQLException
	{   
            String uid = args[0];
            System.out.println("uid in main: " + uid);
            init(uid);
	}
}