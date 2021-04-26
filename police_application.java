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
	static JPanel panel, panel1, panel2;
	static JLabel lblDrugs, lblAids, lblName, lblLenses, lblDOB, lblEmail, lblContact, lblAddress, lblGender;
	static JTextField txtName, txtDOB, txtEmail, txtAddress, txtContact;
	static JComboBox txtGender, txtDrugs, txtAids, txtLenses;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	
	public static void user_details()
	{
		frame = new JFrame("POLICE APPLICATION");
		panel = new JPanel(new GridLayout(10,1,10,10));
		panel1 = new JPanel(new GridLayout(10,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		//**************************************************************************************************************
		// USER DETAILS
		lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
		lblDOB = new JLabel("<HTML><h3>DOB: </h3></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblAddress = new JLabel("<HTML><h3>Address: </h3></HTML>", JLabel.CENTER);
		lblGender = new JLabel("<HTML><h3>Gender: </h3></HTML>", JLabel.CENTER);
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

		panel2.add(new JLabel("<HTML><h1>POLICE APPLICATION</h1></HTML>", JLabel.CENTER));
		panel.add(lblName);			panel1.add(txtName);
		panel.add(lblDOB);			panel1.add(txtDOB);
		panel.add(lblGender);		panel1.add(txtGender);
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