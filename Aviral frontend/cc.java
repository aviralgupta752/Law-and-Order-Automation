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
    static JPanel panel, panel1, panel2;
    static JLabel lblWelcome, lblPass;
    static JTextField txtUser;
    static JPasswordField txtPass;
    static JButton btnLogin, btnAdmin, btnSecurity;
    static JCheckBox show_pass;
	
	public static void user_details()
	{
		frame = new JFrame("ADMIN LOGIN");
		panel = new JPanel(new GridLayout(10,1,10,10));
		// panel = new JPanel(new GridLayout(10,1,10,10));
		panel2 = new JPanel(new GridLayout(1,3,0,0));

		lblWelcome = new JLabel("<HTML><h2>Welcome Back!</h2></HTML>", JLabel.CENTER);
        lblPass = new JLabel("<HTML><h3>Enter Admin Password:</h3></HTML>");
        txtPass = new JPasswordField(60);

        show_pass = new JCheckBox("Show password");
        // show_pass.setForeground(new Color(52,119,235));
        
        show_pass.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.SELECTED){
                    txtPass.setEchoChar((char)0);
                }
                else{
                    txtPass.setEchoChar('*');
                }
            }
        });

        // lblWelcome.setForeground(new Color(52,119,235));
        // lblPass.setForeground(new Color(52,119,235));

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(52,119,235));
        btnLogin.addActionListener(new CustomActionListener());

        panel2.add(new JLabel("<HTML><h1>ADMIN LOGIN</h1></HTML>", JLabel.CENTER));
        panel.add(lblWelcome);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(show_pass);
        panel.add(btnLogin);
		panel2.add(panel);
		// panel2.add(new JLabel("    ", JLabel.CENTER));
		panel2.setBackground(new Color(255,189,68));

		frame.setContentPane(panel2);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//**************************************************************************************************************
	}
	static class CustomActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String password = txtPass.getText();
            if (password.trim().equals("pass")){
                frame.setVisible(false);
                // adminpage.init();                
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Password");
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
		cc obj = new cc();
		obj.user_details();
	}
	public static void main(String []args)
	{
		init();
	}
}