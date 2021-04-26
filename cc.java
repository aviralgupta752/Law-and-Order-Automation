import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class cc{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2;

	static JTextArea txtArea;
	
	static JLabel lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPOI, lblDepartment, lblDesc;
	static JTextField txtName, txtFatherName, txtEmail, txtDOI, txtPOI, txtContact;
	static JComboBox txtDepartment;

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
			lblPOI = new JLabel("<HTML><h3>Place of Issue: </h3></HTML>", JLabel.CENTER);
			lblDepartment = new JLabel("<HTML><h3>Department: </h3></HTML>", JLabel.CENTER);
			lblDesc  = new JLabel("<HTML><h3>Description: </h3></HTML>", JLabel.CENTER);

			btnCancel = new JButton("<HTML><h3>Cancel</h3></HTML>");
			btnCancel.setBackground(new Color(255,92,96));

			btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
			btnSubmit.setBackground(new Color(52,119,235));
			
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

			panel2.add(new JLabel("<HTML><h1>FILL DETAILS</h1></HTML>", JLabel.CENTER));
			panel.add(lblName);			panel1.add(txtName);
			panel.add(lblFatherName);	panel1.add(txtFatherName);
			panel.add(lblEmail);		panel1.add(txtEmail);
			panel.add(lblContact);		panel1.add(txtContact);
			panel.add(lblDOI);			panel1.add(txtDOI);
			panel.add(lblPOI);			panel1.add(txtPOI);
			panel.add(lblDepartment);	panel1.add(txtDepartment);
			panel.add(lblDesc);			panel1.add(scrollPane);
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
	static void init() {
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		cc obj = new cc();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}