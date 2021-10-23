import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

class mainpage
{
	static JFrame frame;
	static JPanel panel, panel1, panel2, mainpanel;
	static JLabel lblkkh, img, space;
	static JButton aboutBtn, quitBtn, admin_loginBtn, police_loginBtn, register_complaintBtn, register_applicationBtn, track_complaintBtn;

	public static void display()
	{
		frame = new JFrame("Main Screen");
		panel = new JPanel(new BorderLayout(10,10));
		panel1 = new JPanel(new GridLayout(8,1,10,10));
		panel2 = new JPanel(new GridLayout(1,2,10,10));
		mainpanel = new JPanel(new GridLayout(1,1,10,10));

		lblkkh = new JLabel("<HTML><h1>KANOON K HAATH</h1><h3><center><i>Delievering Excellent Service</i></center></h3></HTML>", JLabel.CENTER);
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
		quitBtn.addActionListener(new CustomActionListener());
		register_complaintBtn.addActionListener(new CustomActionListener());

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
            		image = ImageIO.read(new File("img.png"));
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
			// Object source = ae.getSource();

			// if(source == admin_login){
			// 	admin_login.init();
			// 	//new admin_login().f.setVisible(true);
			// }
			// else if(source == fill_details){
			// 	//code.init();
			// 	new_visitor.init();
			// }
			// else if(source == register_complaint){
			// 	user_login.init();
			// 	//new user_login().user_frame.setVisible(true);
			// }
			// else if(source == view_details){
			// 	user_login_2.init();
			// 	//viewpage.init();
			// }
			// else if(source == quit){
			// 	System.exit(0);
			// }
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
		mainpage obj = new mainpage();
		obj.display();
	}

	public static void main(String []args)
	{
		init();
	}
}
