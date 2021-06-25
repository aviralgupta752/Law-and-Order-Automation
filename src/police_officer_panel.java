package kanoon_ke_haath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

import kanoon_ke_haath.update_status;
public class police_officer_panel
{
	static JFrame frame;
	static JPanel mainpanel, panel;
	static JLabel lblCase;
	static JButton btnBack;
        static JButton btnUpdateStatus, btnSubmit;
	static JTable table;
	static DefaultTableModel model;
	
        static Connection con;
        static String connectionString = "jdbc:hsqldb:file:db_data/database;hsqldb.lock_file=false";
        static String officer_name_arg;
	public static void display(String officer_name) throws SQLException, ClassNotFoundException
	{
            officer_name_arg = officer_name;
            
            System.out.println("Argument made it to police_officer_panel display " + officer_name);
		//**************************************************************************************************************
			frame = new JFrame("VIEW ALL APPOINTED CASES");
			mainpanel = new JPanel(new BorderLayout(10,10));
                        panel = new JPanel(new GridLayout(1,3,0,0));
		//**************************************************************************************************************

		//**************************************************************************************************************
		// ADD BUTTONS AND LABELS
                        
			lblCase = new JLabel("<HTML><h1><u>APPOINTED CASES</u></h1></HTML>", JLabel.CENTER);
			lblCase.setForeground(new Color(255,189,68));

                        btnUpdateStatus = new JButton("<HTML><h3>Update Status</h3></HTML>");
                        btnUpdateStatus.setBackground(new Color(1,145,135));

                        btnSubmit = new JButton("<HTML><h3>Submit</h3></HTML>");
                        btnSubmit.setBackground(new Color(1,145,135));
                        
                        btnUpdateStatus.addActionListener(new CustomActionListener());
                        btnSubmit.addActionListener(new CustomActionListener());
                        
			btnBack = new JButton("<HTML><h3>Back</h3></HTML>");
			btnBack.setBackground(new Color(1,145,135));
			btnBack.addActionListener(new CustomActionListener());

                        panel.add(btnBack);
                        panel.add(btnUpdateStatus);
                        panel.add(btnSubmit);
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
			model.addColumn("<HTML><h3>Police Station</h3></HTML>");
			model.addColumn("<HTML><h3>Department</h3></HTML>");
			model.addColumn("<HTML><h3>Description</h3></HTML>");
			model.addColumn("<HTML><h3>Status</h3></HTML>");
                        
                        // get array of assigned cases (FIR_ID)
                        ArrayList<String> assigned_fir = new ArrayList<String>();
                        String po_ps="";
                        String po_dep="";
                        String po_id="";
                        System.out.println("Attempting to contact DB ... ");

                        try {
//                          Class.forName("org.hsqldb.jdbc.JDBCDriver");
                          Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException e) {
                          throw e;
                        }

                        try {
//                            con = DriverManager.getConnection(connectionString, "SA", "");
                            con = DriverManager.getConnection("jdbc:mysql://65.1.1.8:3306/test","police","Policemgmt@7police");
                            // getting officer ps
                            String sql_ps="select PO_PS, PO_DEP, PO_ID from police_officer as t where t.PO_NAME=?";
                            PreparedStatement pst_ps = con.prepareStatement(sql_ps);

                            pst_ps.setString(1, officer_name);
                            ResultSet rs_ps=pst_ps.executeQuery();
                            if(rs_ps.next()){
                                po_ps = rs_ps.getString(1);
                                po_dep = rs_ps.getString(2);
                                po_id = rs_ps.getString(3);
                            }
                            System.out.println("Got po_ps and po_dep successfully " + po_ps + " " + po_dep);
                            
                            // Getting info of selected police department
                            String sql_fir="select FIR_ID from fir as t where t.FIR_PS=? and t.FIR_DEP=?";
                            PreparedStatement pst_fir = con.prepareStatement(sql_fir);

                            pst_fir.setString(1, po_ps);
                            pst_fir.setString(2, po_dep);
                            ResultSet rs_fir=pst_fir.executeQuery();
                            while(rs_fir.next()){
                                assigned_fir.add(rs_fir.getString(1));
                            }
                            // Setting FIR_PO_ID
                            for(String fir_id : assigned_fir){
                                String sql_po="update fir set FIR_PO_ID=? where FIR_ID=?";
                                PreparedStatement pst_po = con.prepareStatement(sql_po);
                                
                                pst_po.setString(1, po_id);
                                pst_po.setString(2, fir_id);
                               
                                pst_po.executeUpdate();
                            }
                            
                            for(String fir_id : assigned_fir){
                                // Getting fir details with FIR_ID stored in assigned_fir arraylist
                                String sql_det="select * from fir as t where t.FIR_ID=?";
                                PreparedStatement pst_det = con.prepareStatement(sql_det);

                                pst_det.setString(1, fir_id);
                                ResultSet rs_det=pst_det.executeQuery();
                                while(rs_det.next()){
                                    String stat="";
                                    if(rs_det.getInt(10) == 0){
                                        stat = "Unsolved";
                                    }
                                    else{
                                        stat = "Solved";
                                    }
                                    String[] options = new String[] {"Unsolved", "Solved"};
                                    model.addRow(new Object[]{rs_det.getString(1), rs_det.getString(2),rs_det.getString(3),rs_det.getString(4),rs_det.getString(5),rs_det.getString(6),rs_det.getString(7),rs_det.getString(8),rs_det.getString(9),stat});
                                }
                            }
                        }
                            catch (SQLException e) {
                            throw e;
                          } finally {
                            con.close();
                          }
                        
                        table = new JTable(model);
                        mainpanel.add(new JScrollPane(table), BorderLayout.CENTER);
			
		//**************************************************************************************************************
		
                
		frame.add(mainpanel);
		frame.setSize(1920,1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static class CustomActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object msg = e.getSource();
                            if(msg.equals(btnUpdateStatus)){
                                System.out.println("officer_name_arg is working if: " + officer_name_arg);
			 	update_status.main(new String[]{officer_name_arg});
                            }
                            else if(msg.equals(btnSubmit)){
                            try {
                                send_email.init();
                            } catch (IOException ex) {
                                Logger.getLogger(police_officer_panel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(police_officer_panel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(police_officer_panel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                            else if(msg.equals(btnBack)){
                                frame.setVisible(false);
                            }
		}
	}
	public static void init(String officer_name) throws SQLException, ClassNotFoundException{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		police_officer_panel obj = new police_officer_panel();
		obj.display(officer_name);
	}

	public static void main(String []args) throws SQLException, ClassNotFoundException
	{
                String officer_name = args[0];
                System.out.println("Argument made it to police_officer_panel main " + officer_name);
		init(officer_name);
	}	
}
