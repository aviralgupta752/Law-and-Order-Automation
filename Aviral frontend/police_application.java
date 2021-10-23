import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class police_application{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;
	static JPanel panel, panel1, panel2, mainpanel;
	static JLabel lblspace, lblDrugs, lblAids, lblName, lblLenses, lblDOB, lblEmail, lblContact, lblAddress, lblGender, lblDepartment, lblheading;
	static JTextField txtName, txtDOB, txtEmail, txtAddress, txtContact;
	static JComboBox txtGender, txtDrugs, txtAids, txtLenses, txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	static Border blackline = BorderFactory.createLineBorder(new Color(235,235,235));
	static Font font1 = new Font("Calibri", Font.BOLD, 24);

	public static void user_details()
	{
		frame = new JFrame("POLICE APPLICATION");
		mainpanel = new JPanel(new BorderLayout(10,10));
		panel = new JPanel(new GridLayout(11,1,0,0));
		panel1 = new JPanel(new GridLayout(11,1,0,0));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		//**************************************************************************************************************
		// USER DETAILS
		lblheading = new JLabel("<HTML><h1>POLICE APPLICATION</h1></HTML>", JLabel.CENTER);
		lblheading.setForeground(new Color(255,189,68));

		lblName = new JLabel("<HTML><h2>Name: </h2></HTML>", JLabel.CENTER);
		lblDOB = new JLabel("<HTML><h2>DOB: </h2></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h2>Email: </h2></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h2>Phone: </h2></HTML>", JLabel.CENTER);
		lblAddress = new JLabel("<HTML><h2>Address: </h2></HTML>", JLabel.CENTER);
		lblGender = new JLabel("<HTML><h2>Gender: </h2></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h2>Department: </h2></HTML>", JLabel.CENTER);
		lblDrugs = new JLabel("<HTML><h2>Dependant on drugs or alcohol?</h2></HTML>", JLabel.CENTER);
		lblAids = new JLabel("<HTML><h2>Do you use hearing aids?</h2></HTML>", JLabel.CENTER);
		lblLenses = new JLabel("<HTML><h2>Do you use glasses for your eyesight?</h2></HTML>", JLabel.CENTER);
		lblspace = new JLabel("				    ", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h2>Back</h2></HTML>");
		btnCancel.setBackground(new Color(1, 145, 135));

		btnSubmit = new JButton("<HTML><h2>Submit</h2></HTML>");
		btnSubmit.setBackground(new Color(1, 145, 135));
		
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

		txtName.setInputVerifier(new PassVerifier());
		txtEmail.setInputVerifier(new PassVerifier());
		txtContact.setInputVerifier(new PassVerifier());


		lblName.setBorder(blackline);
		lblDOB.setBorder(blackline);
		lblEmail.setBorder(blackline);
		lblContact.setBorder(blackline);
		lblAddress.setBorder(blackline);
		lblGender.setBorder(blackline);
		lblDepartment.setBorder(blackline);
		lblDrugs.setBorder(blackline);
		lblAids.setBorder(blackline);
		lblLenses.setBorder(blackline);

		txtName.setBorder(blackline);
		txtDOB.setBorder(blackline);
		txtEmail.setBorder(blackline);
		txtContact.setBorder(blackline);
		txtAddress.setBorder(blackline);

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
		panel2.add(panel);
		panel2.add(panel1);
		// panel.setBackground(new Color(45, 45, 45));
		panel2.setBackground(new Color(255,189,68));

		mainpanel.add(lblheading, BorderLayout.NORTH);
		mainpanel.add(panel2, BorderLayout.CENTER);
		mainpanel.add(btnCancel, BorderLayout.LINE_START);
		mainpanel.add(lblspace, BorderLayout.SOUTH);
		mainpanel.add(btnSubmit, BorderLayout.LINE_END);
		mainpanel.setBackground(new Color(45,45,45));

		frame.setContentPane(mainpanel);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//**************************************************************************************************************
	}
	static class PassVerifier extends InputVerifier {
		public boolean verify(JComponent input)
		{
			if(input.equals(txtName)) 
			{
				String text = ((JTextField) input).getText();
				if (text.matches("[a-zA-Z ]+")) // Reads: "Any of a-z or A-Z or space one or more times (together, not each)" ---> blank field or field containing anything other than those will return false.
				{
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Uppercase and Lowercase letters only."));
				return false;
			}
			else if(input.equals(txtEmail))
			{
				String text = ((JTextField) input).getText();
				if (text.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
				{
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Email."));
				return false;
			}
			else if(input.equals(txtContact))
			{
				String text = ((JTextField) input).getText();
				if (text.matches("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$"))
				{
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact."));
				return false;
			}
			return true;
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
		police_application obj = new police_application();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}