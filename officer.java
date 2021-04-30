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
	static JPanel mainpanel;
	static JLabel lblCase;
	static JButton btnBack;
	static JTable table;
	static DefaultTableModel model;
		
	static void display()
	{
		//**************************************************************************************************************
			frame = new JFrame("VIEW ALL CASES");
			mainpanel = new JPanel(new BorderLayout(10,10));
		//**************************************************************************************************************

		//**************************************************************************************************************
		// ADD BUTTONS AND LABELS
			lblCase = new JLabel("<HTML><h1><u>CASES</u></h1></HTML>", JLabel.CENTER);
			lblCase.setForeground(new Color(255,189,68));

			btnBack = new JButton("<HTML><h2>Back</h2></HTML>");
			btnBack.setBackground(new Color(1,145,135));
			btnBack.addActionListener(new CustomActionListener());

			mainpanel.add(lblCase, BorderLayout.NORTH);
			mainpanel.add(btnBack, BorderLayout.LINE_START);

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
			model.addColumn("<HTML><h3>Solved</h3></HTML>");
			
			for(int i=0; i<10; i++)
				model.addRow(new Object[]{"1", "a", "b", "@", "33", "43", "all", "cc", "fssfsfsf", Boolean.TRUE});
		
			table = new JTable(model){
	            @Override
				public Class getColumnClass(int column) {
					switch (column) {
						case 9:
							return Boolean.class;
						default:
							return String.class;
					}
				}
				// @Override
				// public Class getValueAt(int row, int column) {
				// 	switch (column) {
				// 		case 9:
				// 			System.out.println(getValueAt(row, column));	// CALL THE MAIL FUNCTION USING THIS
				// 		default:
				// 			return String.class;
				// 	}
				// }
			};
			mainpanel.add(new JScrollPane(table), BorderLayout.CENTER);
			
		//**************************************************************************************************************
		
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