package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import kanoon_ke_haath.admin_login;
import kanoon_ke_haath.about;
import kanoon_ke_haath.police_login;
import kanoon_ke_haath.complaint;
import kanoon_ke_haath.register_application;
import org.apache.commons.io.FileUtils;
class mainpage
{
	static JFrame frame;
	static JPanel panel, panel1, panel2, mainpanel;
	static JLabel lblkkh, img, space;
	static JButton aboutBtn, quitBtn, admin_loginBtn, police_loginBtn, register_complaintBtn, register_applicationBtn, track_complaintBtn;
        
        static Connection con;
//        static String connectionString = "jdbc:mysql://65.1.1.8:3306/test\",\"police\",\"Policemgmt@7police";
//        static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";

	public void display() throws IOException
	{
		frame = new JFrame("Main Screen");
		panel = new JPanel(new BorderLayout(10,10));
		panel1 = new JPanel(new GridLayout(9,1,10,10));
		panel2 = new JPanel(new GridLayout(1,2,10,10));
		mainpanel = new JPanel(new GridLayout(1,1,10,10));

		lblkkh = new JLabel("<HTML><h1>KANOON KE HAATH</h1><h3><center><i>Delievering Excellence</i></center></h3></HTML>", JLabel.CENTER);
		lblkkh.setForeground(new Color(255,189,68));

		aboutBtn = new JButton("<HTML><h2>About</h2></HTML>");
		quitBtn = new JButton("<HTML><h2>Quit</h2></HTML>");
		admin_loginBtn = new JButton("<HTML><h2>Admin Login</h2></HTML>");
		police_loginBtn = new JButton("<HTML><h2>Police Login</h2></HTML>");
		register_complaintBtn = new JButton("<HTML><h2>Register Complaint</h2></HTML>");
		register_applicationBtn = new JButton("<HTML><h2>Register Application</h2></HTML>");
		track_complaintBtn = new JButton("<HTML><h2>Track Complaint</h2></HTML>");

		space = new JLabel("    ");

		aboutBtn.addActionListener(new CustomActionListener());
		admin_loginBtn.addActionListener(new CustomActionListener());
                police_loginBtn.addActionListener(new CustomActionListener());
		quitBtn.addActionListener(new CustomActionListener());
		register_complaintBtn.addActionListener(new CustomActionListener());
                register_applicationBtn.addActionListener(new CustomActionListener());
                track_complaintBtn.addActionListener(new CustomActionListener());
                
		quitBtn.setBackground(new Color(255,92,96));
		
		
		panel1.add(aboutBtn);
		panel1.add(admin_loginBtn);
		panel1.add(police_loginBtn);
		panel1.add(register_complaintBtn);
		panel1.add(register_applicationBtn);
		panel1.add(track_complaintBtn);
		panel1.add(quitBtn);

		img = new JLabel(" ", JLabel.CENTER);
		BufferedImage image = null;

		try 
		{
                    image = ImageIO.read(getClass().getClassLoader().getResource("img.png"));

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		ImageIcon imageIcon = new ImageIcon(fitimage(image, 550, 620));
		img.setIcon(imageIcon);
		panel2.add(img);
		panel2.add(panel1);
					 
		panel.add(panel2, BorderLayout.CENTER);
		panel.add(lblkkh, BorderLayout.NORTH); 
		panel.add(space, BorderLayout.LINE_END);

		panel.setBackground(new Color(45, 45, 45));
		panel1.setBackground(new Color(45, 45, 45));
		panel2.setBackground(new Color(45, 45, 45));

		mainpanel.add(panel);
		frame.add(mainpanel);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static Image fitimage(Image img , int w , int h)
	{
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	}
	static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();

			if(source == admin_loginBtn){
                            admin_login.init();                         
			}
                        else if(source == aboutBtn){
                            try {
                                about.init();
                            } catch (IOException ex) {
                                Logger.getLogger(mainpage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(source == police_loginBtn){
                            police_login.init();
                        }
                        else if(source == register_complaintBtn){
                            complaint.init();
                        }
                        else if(source == register_applicationBtn){
                            register_application.init();
                        }
                        else if(source == track_complaintBtn){
                            track_complaint_verification.init();
                        }
			else if(source == quitBtn){
                            System.exit(0);
			}
		}
	}
	
        public static void set_up_database() throws ClassNotFoundException, Exception {
            
            System.out.println("Attempting to contact DB ... ");

            try {
//              Class.forName("org.hsqldb.jdbc.JDBCDriver");
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
              throw e;
            }
            
            try {
                con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test","police","Policemgmt@7police");

                // set up tables
                
                String sql_police_dept = "CREATE TABLE if not exists\n" +
"police_dept (\n" +
"		  PD_ID int(11) AUTO_INCREMENT,\n" +
"			PD_NAME varchar(45) DEFAULT NULL,\n" +
"			PD_LOC varchar(45) DEFAULT NULL,\n" +
"		  PD_CITY varchar(45) DEFAULT NULL,\n" +
"		  PD_AREA varchar(45) DEFAULT NULL,\n" +
"		  PD_PHONE varchar(45) DEFAULT NULL,\n" +
"		  PD_REMARKS varchar(45) DEFAULT NULL,\n" +
"			PRIMARY KEY (PD_ID)\n" +
");";
                PreparedStatement pst_police_dept = con.prepareStatement(sql_police_dept);
                
                String sql_fir = "CREATE TABLE if not exists\n" +
"fir (\n" +
"		 FIR_ID int(11) AUTO_INCREMENT,\n" +
"	   FIR_NAME varchar(45) DEFAULT NULL,\n" +
"	   FIR_FNAME varchar(45) DEFAULT NULL,\n" +
"	   FIR_EMAIL varchar(45) DEFAULT NULL,\n" +
"	   FIR_CONTACT varchar(45) DEFAULT NULL,\n" +
"	   FIR_DOI varchar(45) DEFAULT NULL,\n" +
"		 FIR_PS varchar(45) DEFAULT NULL,\n" +
"		 FIR_DEP varchar(45) DEFAULT NULL,\n" +
"		 FIR_DESC varchar(200) DEFAULT NULL,\n" +
"		 FIR_STAT INT DEFAULT NULL,\n" +
"		 FIR_EMAIL_SENT INT DEFAULT NULL,\n" +
"		 FIR_PO_ID varchar(45) DEFAULT NULL,\n" +
"		 FIR_TRACK varchar(45) DEFAULT NULL,\n" +                        
"		 PRIMARY KEY (FIR_ID)\n" +
"	);";
                PreparedStatement pst_fir = con.prepareStatement(sql_fir);
                
                String sql_police_officer = "CREATE TABLE if not exists police_officer (\n" +
"		 PO_ID int(11) AUTO_INCREMENT,\n" +
"	   PO_NAME varchar(45) DEFAULT NULL,\n" +
"	   PO_DOB varchar(45) DEFAULT NULL,\n" +
"	   PO_GENDER varchar(45) DEFAULT NULL,\n" +
"	   PO_DEP varchar(45) DEFAULT NULL,\n" +
"	   PO_EMAIL varchar(45) DEFAULT NULL,\n" +
"		 PO_CONTACT varchar(45) DEFAULT NULL,\n" +
"		 PO_PS varchar(45) DEFAULT NULL,\n" +
"		 PO_DRUGS varchar(45) DEFAULT NULL,\n" +
"		 PO_AID varchar(45) DEFAULT NULL,\n" +
"		 PO_LENSES varchar(45) DEFAULT NULL,\n" +
"		 PRIMARY KEY (PO_ID)\n" +
"	);";
                PreparedStatement pst_police_officer = con.prepareStatement(sql_police_officer);
                
                String sql_police_officer_list = "CREATE TABLE if not exists police_officer_list (\n" +
"		   USERNAME varchar(45) DEFAULT NULL,\n" +
"		   PASSWORD varchar(45) DEFAULT NULL\n" +
"	);";
                PreparedStatement pst_police_officer_list = con.prepareStatement(sql_police_officer_list);
                
                String sql_criminal = "CREATE TABLE if not exists criminal (\n" +
"	CRIM_ID int(11) AUTO_INCREMENT,\n" +
"	CRIM_NAME varchar(45) DEFAULT NULL,\n" +
"	CRIM_AGE int(11) DEFAULT NULL,\n" +
"	CRIM_GENDER varchar(45) DEFAULT NULL,\n" +
"	CRIM_ADDRESS varchar(45) DEFAULT NULL,\n" +
"	CRIM_CONTACT varchar(45) DEFAULT NULL,\n" +
"	CRIM_DOC varchar(45) DEFAULT NULL,\n" +
"	CRIM_DESC varchar(200) DEFAULT NULL,\n" +
"	CRIM_CRIME_DEP varchar(45) DEFAULT NULL,\n" +
"	CRIM_PS varchar(45) DEFAULT NULL,\n" +
"	PRIMARY KEY (CRIM_ID)\n" +
");";
                PreparedStatement pst_criminal = con.prepareStatement(sql_criminal);
                
                String sql_applic = "CREATE TABLE if not exists applic (\n" +
"	APP_ID int(11) AUTO_INCREMENT,\n" +
"	APP_TYPE varchar(45) DEFAULT NULL,\n" +
"	APP_NAM varchar(45) DEFAULT NULL,\n" +
"	APP_FNAME varchar(45) DEFAULT NULL,\n" +
"	APP_EMAIL varchar(45) DEFAULT NULL,\n" +
"	APP_CONTACT varchar(45) DEFAULT NULL,\n" +
"	APP_DOI varchar(45) DEFAULT NULL,\n" +
"	APP_PAN varchar(45) DEFAULT NULL,\n" +
"	APP_AADHAR varchar(45) DEFAULT NULL,\n" +
"	APP_PO_NAME varchar(45) DEFAULT NULL,\n" +
"	APP_PO_ID varchar(45) DEFAULT NULL,\n" +
"	APP_PS varchar(45) DEFAULT NULL,\n" +
"	PRIMARY KEY (APP_ID)\n" +
");";
                PreparedStatement pst_applic = con.prepareStatement(sql_applic);
                
                pst_police_dept.executeUpdate();
                pst_fir.executeUpdate();
                pst_police_officer.executeUpdate();
                pst_police_officer_list.executeUpdate();
                pst_criminal.executeUpdate();
                pst_applic.executeUpdate();
                
                String sql="SELECT * FROM police_dept;";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    System.out.println("Table is not empty and database is set already");
                }
                else{
                    System.out.println("Table is not set, set starting values");
                    // fill in details if the tables are empty
                    UUID idOne = UUID.randomUUID();
                    String uid = idOne.toString();
                    String sql_pd = "INSERT INTO police_dept (PD_NAME, PD_LOC, PD_CITY, PD_AREA, PD_PHONE, PD_REMARKS) VALUES ('Sirsa Mandi Police Station', 'Grid-4', 'Sirsa','Anaj Mandi Sirsa','9899898765','Some text about the police station');";
                    String sql_f = "INSERT INTO fir (FIR_NAME, FIR_FNAME, FIR_EMAIL, FIR_CONTACT, FIR_DOI, FIR_PS, FIR_DEP, FIR_DESC, FIR_STAT, FIR_EMAIL_SENT, FIR_PO_ID, FIR_TRACK) VALUES ('Test', 'FatherTest', 'visitormanagementsystem23@gmail.com', '9999999999', '12022001', 'Sirsa Mandi Police Station', 'Cybercrime', 'This complaint is registered with regard to a fraud bank payment on my SBI debit card.', 0, 0, '1',?);";
                    String sql_po = "INSERT INTO police_officer (PO_NAME, PO_DOB, PO_GENDER, PO_DEP, PO_EMAIL, PO_CONTACT, PO_PS, PO_DRUGS, PO_AID, PO_LENSES) VALUES ('TestPoliceOfficer', '12031987', 'Male', 'Cybercrime', 'test@gmail.com', '9898989897', 'Sirsa Mandi Police Station', 'No', 'No', 'Yes');";
                    String sql_pol = "INSERT INTO police_officer_list values ('TestPoliceOfficer', 'test');";
                    String sql_cri = "INSERT INTO criminal (CRIM_NAME, CRIM_AGE, CRIM_GENDER, CRIM_ADDRESS, CRIM_CONTACT, CRIM_DOC, CRIM_DESC, CRIM_CRIME_DEP, CRIM_PS) values ('rcb', '41', 'Male', 'bangalore','9087564321','211021','bank loot','Law and Order', 'Sirsa Mandi Police Station');";
                    String sql_app = "INSERT INTO applic (APP_TYPE, APP_NAM, APP_FNAME, APP_EMAIL, APP_CONTACT, APP_DOI, APP_PAN, APP_AADHAR, APP_PO_NAME,  APP_PO_ID, APP_PS) values ('Law and order', 'random', 'kapoor','rk@gmail.com','9234516789','211090','AABEE1234F','123456780987','bond',2,'Sirsa Mandi Police Station');";
                    PreparedStatement pst_pd = con.prepareStatement(sql_pd);
                    PreparedStatement pst_f = con.prepareStatement(sql_f);
                    pst_f.setString(1, uid);
                    PreparedStatement pst_po = con.prepareStatement(sql_po);
                    PreparedStatement pst_pol = con.prepareStatement(sql_pol);
                    PreparedStatement pst_cri = con.prepareStatement(sql_cri);
                    PreparedStatement pst_app = con.prepareStatement(sql_app);
                    
                    pst_pd.executeUpdate();
                    pst_f.executeUpdate();
                    pst_po.executeUpdate();
                    pst_pol.executeUpdate();
                    pst_cri.executeUpdate();
                    pst_app.executeUpdate();
                }

              } catch (SQLException e) {
                throw e;
              } finally {
                con.close();
              }
        }
        
        
	static void init() throws IOException, Exception
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mainpage obj = new mainpage();
		obj.display();
                obj.set_up_database();
	}

	public static void main(String []args) throws IOException, Exception
	{
		init();
	}
        
        public static ArrayList<String> readToString(String fname) throws Exception {
            File file = new File(fname);
            String string = FileUtils.readFileToString(file, "utf-8");
            String[] strSplit = string.split(";");
            
            ArrayList<String> sqlList = new ArrayList<String>(Arrays.asList(strSplit));
            
            return sqlList;
      }
}
