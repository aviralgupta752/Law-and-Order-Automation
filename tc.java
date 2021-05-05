import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class tc{
	static JFrame frame;
	static JButton btnCancel;

	static JPanel panel, panel1, panel2, mainpanel;

	static JLabel lblFatherName, lblName, lblEmail, lblContact, lblDOI, lblPS, lblDepartment, lblDesc, lblStatus, lblspace, lblheading;
	static JLabel txtName, txtFatherName, txtEmail, txtDOI, txtPS, txtContact, txtStatus, txtArea, txtDepartment;
	static Border blackline = BorderFactory.createLineBorder(new Color(235,235,235));
	public static void display()
	{
		frame = new JFrame("TRACK COMPLAINT");
		mainpanel = new JPanel(new BorderLayout(10,10));
		panel = new JPanel(new GridLayout(9,1,0,0));
		panel1 = new JPanel(new GridLayout(9,1,0,0));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		lblheading = new JLabel("<HTML><h1>TRACK COMPLAINT </h1></HTML>", JLabel.CENTER);
		lblheading.setForeground(new Color(255,189,68));
		lblName = new JLabel("<HTML><h2>Name: </h2></HTML>", JLabel.CENTER);
		lblFatherName = new JLabel("<HTML><h2>Father's / Husband's Name: </h2></HTML>", JLabel.CENTER);
		lblEmail = new JLabel("<HTML><h2>Email: </h2></HTML>", JLabel.CENTER);
		lblContact = new JLabel("<HTML><h2>Phone: </h2></HTML>", JLabel.CENTER);
		lblDOI = new JLabel("<HTML><h2>Date of Issue: </h2></HTML>", JLabel.CENTER);
		lblPS = new JLabel("<HTML><h2>Name of Police Station: </h2></HTML>", JLabel.CENTER);
		lblDepartment = new JLabel("<HTML><h2>Department: </h2></HTML>", JLabel.CENTER);
		lblDesc  = new JLabel("<HTML><h2>Description: </h2></HTML>", JLabel.CENTER);
		lblStatus  = new JLabel("<HTML><h2>Status of complaint: </h2></HTML>", JLabel.CENTER);
		lblspace = new JLabel("				    ", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h2>Back</h2></HTML>");
		btnCancel.setBackground(new Color(1, 145, 135));

		// btnSubmit = new JButton("<HTML><h2>Submit</h2></HTML>");
		// btnSubmit.setBackground(new Color(52,119,235));
		
		btnCancel.addActionListener(new CustomActionListener());
		// btnSubmit.addActionListener(new CustomActionListener());

		txtName = new JLabel("<HTML><h2>" + "Aviral" + "</h2></HTML>");
		txtFatherName = new JLabel("<HTML><h2>" + "HC Gupta" + "</h2></HTML>");
		txtEmail = new JLabel("<HTML><h2>" + "aaaa@gmail.com" + "</h2></HTML>");
		txtContact = new JLabel("<HTML><h2>" + "7897678987" + "</h2></HTML>");
		txtDOI = new JLabel("<HTML><h2>" + "344343" + "</h2></HTML>");
		txtPS = new JLabel("<HTML><h2>" + "Sirsa PS" + "</h2></HTML>");
		txtArea = new JLabel("<HTML><h2>" + "Sirsa" + "</h2></HTML>");
		txtDepartment = new JLabel("<HTML><h2>" + "CC" + "</h2></HTML>");
		txtStatus = new JLabel("<HTML><h2>" + "Nhi Maloom" + "</h2></HTML>");

		// panel2.add(lblspace);
		panel.add(lblName);			panel1.add(txtName);
		panel.add(lblFatherName);	panel1.add(txtFatherName);
		panel.add(lblEmail);		panel1.add(txtEmail);
		panel.add(lblContact);		panel1.add(txtContact);
		panel.add(lblDOI);			panel1.add(txtDOI);
		panel.add(lblPS);			panel1.add(txtPS);
		panel.add(lblDepartment);	panel1.add(txtDepartment);
		panel.add(lblDesc);			panel1.add(txtArea);
		panel.add(lblStatus);		panel1.add(txtStatus);	

		lblName.setBorder(blackline);			txtName.setBorder(blackline);
		lblFatherName.setBorder(blackline);	txtFatherName.setBorder(blackline);
		lblEmail.setBorder(blackline);		txtEmail.setBorder(blackline);
		lblContact.setBorder(blackline);		txtContact.setBorder(blackline);
		lblDOI.setBorder(blackline);			txtDOI.setBorder(blackline);
		lblPS.setBorder(blackline);			txtPS.setBorder(blackline);
		lblDepartment.setBorder(blackline);	txtDepartment.setBorder(blackline);
		lblDesc.setBorder(blackline);			txtArea.setBorder(blackline);
		lblStatus.setBorder(blackline);		txtStatus.setBorder(blackline);
		// txtName.setBorder(blackline);
		// panel.setBorder(blackline);
		panel2.add(panel);
		panel2.add(panel1);
		// panel2.add(new JLabel("		 ", JLabel.CENTER));
		// panel2.add(lblspace);

		mainpanel.add(lblheading, BorderLayout.NORTH);
		mainpanel.add(panel2, BorderLayout.CENTER);
		mainpanel.add(btnCancel, BorderLayout.LINE_START);
		mainpanel.add(lblspace, BorderLayout.SOUTH);
		mainpanel.add(new JLabel("		 ", JLabel.CENTER), BorderLayout.LINE_END);

		
		mainpanel.setBackground(new Color(45,45,45));
		frame.setContentPane(mainpanel);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class CustomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			Object source = ae.getSource();
			if(source == btnCancel){
				frame.setVisible(false);
		  }
		}
	}
	static void init()
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		tc obj = new tc();
		obj.display();
	}
	public static void main(String []args)
	{
		init();
	}
}