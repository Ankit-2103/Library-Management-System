import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class LibrarianList {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static Statement stmt=null;
	String activeValue;
	
	public void showLibrarianList() {
		jl1=new JLabel("ALL LIBRARIAN DETAILS");
		jl1.setForeground(Color.BLUE);
		jl1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    jl1.setBounds(230,40,400,50);
		
		System.out.println("coming");
		
		jf1=new JFrame("Librarian List");
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Name","Email ID","Admin","Status"}, 0) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		JTable jt=new JTable(tableModel);  
		try {
		
			con=DataBaseConnection.getConnection();
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from users");
			while(rs.next()) {
				String name = rs.getString("name");
			    String email_id = rs.getString("email_id");
			    String isAdmin = rs.getString("isadmin");
			    int activeStatus = rs.getInt("active");
			    if(activeStatus == 1) {
			    	activeValue="Activated";
			    }
			    else {
			    	activeValue="Deactivated";
			    }
			    tableModel.addRow(new Object[]{name, email_id, isAdmin, activeValue});
			}
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		/*
		 * String data[][]={ {"101","Amit","670000"}, {"102","Jai","780000"},
		 * {"101","Sachin","700000"}};
		 */  
		//String column[]={"Email ID","NAME","Admin"};  
		jt.setModel(tableModel);
		jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
		jt.setBounds(30,140,600,400);
		JScrollPane sp=new JScrollPane(jt);
		sp.setBounds(30, 140, 600, 400);
		jb3=new JButton("Edit Details");  
	    jb3.setBounds(170,580,300,40);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		if(jt.getSelectedRow() != -1) {
		    		jf1.dispose();
		    		new LibrarianEdit().editLibrarian(jt.getModel().getValueAt(jt.getSelectedRow(), 1).toString(),jt.getModel().getValueAt(jt.getSelectedRow(), 3).toString());
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(jf1,"Please Select Row First","Alert",JOptionPane.WARNING_MESSAGE);
	    		}
	    		//System.out.println(jt.getModel().getValueAt(jt.getSelectedRow(), 1).toString());
	    	}
	    });
	    
	    jb1=new JButton("Back");  
	    jb1.setBounds(5,5,100,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jf1.dispose();
	    				//new AddLibrarian().addLibrarian();
	    				new AdminHome().adminHome();
	    	        }  
	    });
		
		jf1.add(sp);
		jf1.add(jb3);
		jf1.add(jb1);
		jf1.add(jl1);
		
		jf1.setSize(700,700);
		jf1.setLayout(null); 
	    jf1.setVisible(true);
	}
	
}
