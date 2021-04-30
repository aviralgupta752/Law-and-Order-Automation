import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.*;

public class officer
{
	static JFrame frame;
	static JPanel mainpanel, panel;
	static JLabel lblCase;
	static JButton btnBack, btnSave, btnForm;
	static JTable table;
	static DefaultTableModel model;
		
	static void display()
	{
		
		frame = new JFrame("VIEW ALL CASES");
		mainpanel = new JPanel(new BorderLayout(10,10));
		panel = new JPanel(new GridLayout(1,3,0,0));
		lblCase = new JLabel("<HTML><h1>CASES</h1></HTML>", JLabel.CENTER);
		lblCase.setForeground(new Color(255,189,68));

		btnBack = new JButton("<HTML><h2>Back</h2></HTML>");
		btnBack.setBackground(new Color(1,145,135));
		btnBack.addActionListener(new CustomActionListener());

		btnSave = new JButton("<HTML><h2>Save</h2></HTML>");
		btnSave.setBackground(new Color(1,145,135));
		btnSave.addActionListener(new CustomActionListener());

		btnForm = new JButton("<HTML><h2>Form</h2></HTML>");
		btnForm.setBackground(new Color(1,145,135));
		btnForm.addActionListener(new CustomActionListener());

		panel.add(btnBack);
		panel.add(btnForm);
		panel.add(btnSave);

		mainpanel.add(lblCase, BorderLayout.NORTH);
		mainpanel.add(panel, BorderLayout.SOUTH);

		mainpanel.setBackground(new Color(45, 45, 45));
	
		model = new DefaultTableModel();
		model.addColumn("<HTML><h3>Case ID</h3></HTML>");
		model.addColumn("<HTML><h3>Name</h3></HTML>");
		model.addColumn("<HTML><h3>Father's / Husband's Name</h3></HTML>");
		model.addColumn("<HTML><h3>Email</h3></HTML>");
		model.addColumn("<HTML><h3>Phone</h3></HTML>");
		model.addColumn("<HTML><h3>Date of Issue</h3></HTML>");
		model.addColumn("<HTML><h3>Place of Issue</h3></HTML>");
		model.addColumn("<HTML><h3>Department</h3></HTML>");
		model.addColumn("<HTML><h3>Description</h3></HTML>");
		
		for(int i=0; i<10; i++)
			model.addRow(new Object[]{"1", "a", "b", "@", "33", "43", "all", "cc", "fssfsfsf"});
	
		table = new JTable(model);
		mainpanel.add(new JScrollPane(table), BorderLayout.CENTER);
			
		frame.add(mainpanel);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// Object msg = e.getSource();
			// 			if(msg.equals(btnBack)){
			// 				frame.setVisible(false);
			// 			}
		}
	}
	public static void init(){
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		officer obj = new officer();
		obj.display();
	}

	public static void main(String []args)
	{
		init();
	}	
}