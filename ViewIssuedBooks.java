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

public class ViewIssuedBooks {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static Statement stmt=null;
	String activeValue;
	
	public void viewIssuedBooks() {
		
		jl=new JLabel("ALL ISSUE BOOKS DETAIL");
		jl.setForeground(Color.BLUE);
		jl.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    jl.setBounds(220,40,400,50);
		
		jf1=new JFrame("Issued Book List");
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Book ID","Student ID","Issue Date","Return Upto","Status","Returend Date"}, 0) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		JTable jt=new JTable(tableModel);  
		try {
		
			con=DataBaseConnection.getConnection();
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from issuebook");
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
			    int student_id = rs.getInt("student_id");
			    String issue_date = rs.getString("issue_date");
			    String return_upto = rs.getString("return_upto");
			    String issued_status = rs.getString("issuebook_status");
			    String return_date = rs.getString("return_date");
			    tableModel.addRow(new Object[]{book_id, student_id, issue_date,return_upto,issued_status,return_date});
			}
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		 
		jt.setModel(tableModel);
		jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		
		
		jt.setBounds(30,100,600,400);
		JScrollPane sp=new JScrollPane(jt);
		sp.setBounds(30,100, 600, 400);
		/*
		 * jb3=new JButton("Edit Details"); jb3.setBounds(170,480,300,40);
		 * jb3.addActionListener(new ActionListener(){ public void
		 * actionPerformed(ActionEvent e){ if(jt.getSelectedRow() != -1) {
		 * jf1.dispose(); new
		 * LibrarianEdit().editLibrarian(jt.getModel().getValueAt(jt.getSelectedRow(),
		 * 1).toString(),jt.getModel().getValueAt(jt.getSelectedRow(), 3).toString()); }
		 * else { JOptionPane.showMessageDialog(jf1,"Please Select Row First","Alert",
		 * JOptionPane.WARNING_MESSAGE); }
		 * //System.out.println(jt.getModel().getValueAt(jt.getSelectedRow(),
		 * 1).toString()); } });
		 */
	    
	    jb1=new JButton("Back");  
	    jb1.setBounds(5,5,100,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jf1.dispose();
	    				new LibrarianMainPage().showLibrarianPage();
	    	        }  
	    });
		
		jf1.add(sp);
		jf1.add(jb1);
		jf1.add(jl);
		
		jf1.setSize(680,600);
		jf1.setLayout(null); 
	    jf1.setVisible(true);
	}
	
}
