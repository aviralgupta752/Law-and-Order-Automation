package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class register_application{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2;

	static JTextArea txtArea;
	
	static JLabel lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPan, lblDepartment, lblAadhar;
	static JTextField txtName, txtFatherName, txtEmail, txtDOI, txtPan, txtContact, txtAadhar;
	static JComboBox txtDepartment;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	
	public static void user_details()
	{
		frame = new JFrame("FILL DETAILS");
		panel = new JPanel(new GridLayout(10,1,10,10));
		panel1 = new JPanel(new GridLayout(10,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		//**************************************************************************************************************
		// USER DETAILS
			lblName = new JLabel("<HTML><h3>Name: </h3></HTML>", JLabel.CENTER);
			lblFatherName = new JLabel("<HTML><h3>Father's / Husband's Name: </h3></HTML>", JLabel.CENTER);
			lblEmail = new JLabel("<HTML><h3>Email: </h3></HTML>", JLabel.CENTER);
			lblContact = new JLabel("<HTML><h3>Phone: </h3></HTML>", JLabel.CENTER);
			lblDOI = new JLabel("<HTML><h3>Date of Issue: </h3></HTML>", JLabel.CENTER);
			lblPan = new JLabel("<HTML><h3>PAN number: </h3></HTML>", JLabel.CENTER);
			lblDepartment = new JLabel("<HTML><h3>Type of license: </h3></HTML>", JLabel.CENTER);
			lblAadhar = new JLabel("<HTML><h3>Aadhar number: </h3></HTML>", JLabel.CENTER);

			btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
			btnCancel.setBackground(new Color(255,92,96));

			btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
			btnSubmit.setBackground(new Color(52,119,235));
			
			btnCancel.addActionListener(new CustomActionListener());
			btnSubmit.addActionListener(new CustomActionListener());

			txtName = new JTextField(60);
			txtFatherName = new JTextField(60);
			txtEmail = new JTextField(60);
			txtContact = new JTextField(60);
			txtDOI = new JTextField(60);
			txtPan = new JTextField(60);

			txtAadhar = new JTextField(60);

			String txtDepartmentOptions[] = {"Arms and ammunition", "Loud speaker", "Hotel/lodge", "Browsing center", "mass meetings"};
			txtDepartment = new JComboBox(txtDepartmentOptions);
			txtDepartment.setSelectedIndex(0);

			txtName.setInputVerifier(new PassVerifier());
			txtFatherName.setInputVerifier(new PassVerifier());
			txtEmail.setInputVerifier(new PassVerifier());
			txtContact.setInputVerifier(new PassVerifier());
			txtDOI.setInputVerifier(new PassVerifier());
			txtPan.setInputVerifier(new PassVerifier());
                        txtAadhar.setInputVerifier(new PassVerifier());

			panel2.add(new JLabel("<HTML><h1>Write something here</h1></HTML>", JLabel.CENTER));
			panel.add(lblName);			panel1.add(txtName);
			panel.add(lblFatherName);	panel1.add(txtFatherName);
			panel.add(lblEmail);		panel1.add(txtEmail);
			panel.add(lblContact);		panel1.add(txtContact);
			panel.add(lblDOI);			panel1.add(txtDOI);
			panel.add(lblPan);			panel1.add(txtPan);
			panel.add(lblDepartment);	panel1.add(txtDepartment);
			panel.add(lblAadhar);			panel1.add(txtAadhar);
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
			if(input.equals(txtName) || input.equals(txtFatherName)) 
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
                        else if(input.equals(txtAadhar))
                        {
                            String text = ((JTextField) input).getText();
                            if(text.matches("\\b\\d{12}\\b"))
                            {
                                ((JTextField) input).setBorder(BorderFactory.createLineBorder(Color.GRAY));
					return true;
                            }
                            ((JTextField) input).setBorder(BorderFactory.createTitledBorder(redline, "Invalid Contact."));
                            return false;
                        }
                        else if(input.equals(txtPan))
                        {
                            String text = ((JTextField) input).getText();
                            if(text.matches("[A-Z]{5}\\d{4}[A-Z]{1}"))
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
        
        static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();

			if(source == btnSubmit){
                            
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
		register_application obj = new register_application();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}