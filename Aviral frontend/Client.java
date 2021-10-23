import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.*;

class Client
{
	static JFrame frame;
	static JLabel lblWelcome, lblMsg, space;
	static JTextField txtMsg;
	static JButton btnSend;
	static JTextArea txtArea;
	static JPanel panel, panel1, panel2;
	static void display()
	{
		frame = new JFrame("CLIENT MACHINE");
		panel = new JPanel(new BorderLayout());
		panel1 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new BorderLayout());
		lblWelcome = new JLabel("<HTML><h2>Welcome User!</h2></HTML>", JLabel.CENTER);
		lblMsg = new JLabel("<HTML><h3>Type your message here:</h3></HTML>");
		space = new JLabel("    ");
		txtMsg = new JTextField(60);

		txtArea = new JTextArea(5, 20);
		// JScrollPane scrollPane = new JScrollPane(txtArea); 
		// txtArea.setEditable(true);

		lblWelcome.setForeground(new Color(255,189,68));
		lblMsg.setForeground(new Color(255,189,68));

		btnSend = new JButton("Send");
		btnSend.setBackground(new Color(1, 145, 135));
		btnSend.addActionListener(new CustomActionListener());

		panel.add(txtMsg, BorderLayout.CENTER);
		panel.add(btnSend, BorderLayout.LINE_END);
		panel2.add(txtArea, BorderLayout.CENTER);
		panel2.add(lblMsg, BorderLayout.SOUTH);
		panel1.add(panel2, BorderLayout.CENTER);
		panel1.add(panel, BorderLayout.SOUTH);
		panel1.add(lblWelcome, BorderLayout.NORTH);
		frame.add(panel1);
		
		panel.setBackground(new Color(45,45,45));
		panel1.setBackground(new Color(45,45,45));
		panel2.setBackground(new Color(45,45,45));
		panel1.add(space, BorderLayout.LINE_END);
		panel1.add(space, BorderLayout.LINE_START);
		frame.setSize(400,440);
		frame.setVisible(true);
		// frame.setLayout(new GridLayout(4,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// String password = txtPass.getText();
			// if (password.trim().equals("pass")){
			// 	frame.setVisible(false);
			// 	// adminpage.init();				
			// }
			// else{
			// 	JOptionPane.showMessageDialog(null, "Incorrect Password");
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

		Client obj = new Client();
		obj.display();
	}
	public static void main(String []args)
	{
		init();
	}
}


// public static void display()
// 	{
// 		EventQueue.invokeLater(new Runnable() {  
// 			public void run() {  
// 				new Client().setVisible(true);  
// 			}});

// 		try
// 		{
// 			sckt = new Socket("127.0.0.1", 1201);  
// 			dtinpt = new DataInputStream(sckt.getInputStream());  
// 			dtotpt = new DataOutputStream(sckt.getOutputStream());  
// 			String msgin = "";  
// 			while (!msgin.equals("Exit")) {  
// 				msgin = dtinpt.readUTF();  
// 				jTextArea1.setText(jTextArea1.getText().trim() + "\n Server:" + msgin);  
// 			}
// 		} catch (Exception e) {}

// 	}