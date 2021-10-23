import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class complaint{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2, mainpanel;

	static JTextArea txtArea;
	
	static JLabel lblspace, lblheading, lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPOI, lblDepartment, lblDesc;
	static JTextField txtName, txtFatherName, txtEmail, txtDOI, txtPOI, txtContact;
	static JComboBox txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	static Border blackline = BorderFactory.createLineBorder(new Color(235,235,235));

	public static void user_details()
	{
		frame = new JFrame("FILL DETAILS");
		mainpanel = new JPanel(new BorderLayout(10,10));
		panel = new JPanel(new GridLayout(10,1,0,0));
		panel1 = new JPanel(new GridLayout(10,1,0,0));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		lblheading = new JLabel("<HTML><h1>FIR</h1></HTML>", JLabel.CENTER);
		lblheading.setForeground(new Color(255,189,68));
		
		lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
		lblFatherName = new JLabel("<HTML><h3>Father's / Husband's Name: </h3></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
		lblDOI = new JLabel("<HTML><h3>Date of Issue: </h3></HTML>", JLabel.CENTER);
		lblPOI = new JLabel("<HTML><h3>Place of Issue: </h3></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
		lblDesc  = new JLabel("<HTML><h3>Description: </h3></HTML>", JLabel.CENTER);
		lblspace = new JLabel("				    ", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h2>Back</h2></HTML>");
		btnCancel.setBackground(new Color(1, 145, 135));

		btnSubmit = new JButton("<HTML><h2>Submit</h2></HTML>");
		btnSubmit.setBackground(new Color(1, 145, 135));
		
		
		// btnCancel.addActionListener(new CustomActionListener());
		// btnSubmit.addActionListener(new CustomActionListener());

		txtName = new JTextField(60);
		txtFatherName = new JTextField(60);
		txtEmail = new JTextField(60);
		txtContact = new JTextField(60);
		txtDOI = new JTextField(60);
		txtPOI = new JTextField(60);

		txtArea = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(txtArea); 
		txtArea.setEditable(true);

		String txtDepartmentOptions[] = {"Women Protection", "Cybercrime", "Traffic & Control", "Law and Order"};
		txtDepartment = new JComboBox(txtDepartmentOptions);
		txtDepartment.setSelectedIndex(0);

		txtName.setInputVerifier(new PassVerifier());
		txtFatherName.setInputVerifier(new PassVerifier());
		txtEmail.setInputVerifier(new PassVerifier());
		txtContact.setInputVerifier(new PassVerifier());
		txtDOI.setInputVerifier(new PassVerifier());
		txtPOI.setInputVerifier(new PassVerifier());

		lblName.setBorder(blackline);
		lblFatherName.setBorder(blackline);
		lblEmail.setBorder(blackline);
		lblContact.setBorder(blackline);
		lblDOI.setBorder(blackline);
		lblPOI.setBorder(blackline);
		lblDepartment.setBorder(blackline);
		lblDesc.setBorder(blackline);

		txtName.setBorder(blackline);
		txtFatherName.setBorder(blackline);
		txtEmail.setBorder(blackline);
		txtContact.setBorder(blackline);
		txtDOI.setBorder(blackline);
		txtPOI.setBorder(blackline);

		panel2.add(new JLabel("<HTML><h1>FILE A COMPLAINT</h1></HTML>", JLabel.CENTER));
		panel.add(lblName);			panel1.add(txtName);
		panel.add(lblFatherName);	panel1.add(txtFatherName);
		panel.add(lblEmail);		panel1.add(txtEmail);
		panel.add(lblContact);		panel1.add(txtContact);
		panel.add(lblDOI);			panel1.add(txtDOI);
		panel.add(lblPOI);			panel1.add(txtPOI);
		panel.add(lblDepartment);	panel1.add(txtDepartment);
		panel.add(lblDesc);			panel1.add(scrollPane);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class PassVerifier extends InputVerifier {
		public boolean verify(JComponent input)
		{
			if(input.equals(txtName) || input.equals(txtFatherName) || input.equals(txtPOI)) 
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
			else if(input.equals(txtDOI))
			{
				String text = ((JTextField) input).getText();
				if (text.matches("[0-9]+"))
				{
					((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
				}
				((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Number."));
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
		complaint obj = new complaint();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}