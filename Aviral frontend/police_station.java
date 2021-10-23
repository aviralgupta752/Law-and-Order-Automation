import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class police_station{
	static JFrame frame;
	static JButton btnSubmit, btnCancel;

	static JPanel panel, panel1, panel2, mainpanel;
	
	static JLabel lblPSName, lblLocation, lblCity, lblArea, lblPhone, lblRemarks, lblspace, lblheading;
	static JTextField txtPSName, txtLocation, txtCity, txtArea, txtPhone, txtRemarks;
	static Border redline = BorderFactory.createLineBorder(Color.RED);
	static Border blackline = BorderFactory.createLineBorder(new Color(235,235,235));

	public static void user_details()
	{
		frame = new JFrame("POLICE STATION DETAILS");
		mainpanel = new JPanel(new BorderLayout(10,10));
		panel = new JPanel(new GridLayout(7,1,0,0));
		panel1 = new JPanel(new GridLayout(7,1,0,0));
		panel2 = new JPanel(new GridLayout(1,2,0,0));

		lblheading = new JLabel("<HTML><h1>FILL DETAILS</h1></HTML>", JLabel.CENTER);
		lblheading.setForeground(new Color(255,189,68));

		lblPSName = new JLabel("<HTML><h2>PS Name: </h2></HTML>", JLabel.CENTER);
		lblLocation = new JLabel("<HTML><h2>Location: </h2></HTML>", JLabel.CENTER);
		lblCity = new JLabel("<HTML><h2>City: </h2></HTML>", JLabel.CENTER);
		lblArea = new JLabel("<HTML><h2>Area: </h2></HTML>", JLabel.CENTER);
		lblPhone = new JLabel("<HTML><h2>Phone: </h2></HTML>", JLabel.CENTER);
		lblRemarks = new JLabel("<HTML><h2>Remarks: </h2></HTML>", JLabel.CENTER);
		lblspace = new JLabel("				    ", JLabel.CENTER);

		btnCancel = new JButton("<HTML><h2>Back</h2></HTML>");
		btnCancel.setBackground(new Color(1, 145, 135));

		btnSubmit = new JButton("<HTML><h2>Submit</h2></HTML>");
		btnSubmit.setBackground(new Color(1, 145, 135));
		
		// btnCancel.addActionListener(new CustomActionListener());
		// btnSubmit.addActionListener(new CustomActionListener());

		txtPSName = new JTextField(60);
		txtLocation = new JTextField(60);
		txtCity = new JTextField(60);
		txtArea = new JTextField(60);
		txtPhone = new JTextField(60);
		txtRemarks = new JTextField(60);

		lblPSName.setBorder(blackline);
		lblLocation.setBorder(blackline);
		lblCity.setBorder(blackline);
		lblArea.setBorder(blackline);
		lblPhone.setBorder(blackline);
		lblRemarks.setBorder(blackline);
		// lblspace.setBorder(blackline);

		txtPSName.setBorder(blackline);
		txtLocation.setBorder(blackline);
		txtCity.setBorder(blackline);
		txtArea.setBorder(blackline);
		txtPhone.setBorder(blackline);
		txtRemarks.setBorder(blackline);

		txtPhone.setInputVerifier(new PassVerifier());
		
		panel2.add(new JLabel("<HTML><h1>POLICE STATION</h1></HTML>", JLabel.CENTER));
		panel.add(lblPSName);		panel1.add(txtPSName);
		panel.add(lblLocation);		panel1.add(txtLocation);
		panel.add(lblCity);			panel1.add(txtCity);
		panel.add(lblArea);			panel1.add(txtArea);
		panel.add(lblPhone);		panel1.add(txtPhone);
		panel.add(lblRemarks);		panel1.add(txtRemarks);
		// panel.add(btnCancel);		panel1.add(btnSubmit);
		panel2.add(panel);
		panel2.add(panel1);
		
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