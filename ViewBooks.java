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

public class ViewBooks {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static Statement stmt=null;
	
	public void showBooks() {
		
		
		jf1=new JFrame("Books List");
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Book ID","Book Name","Author","Publisher","Quantity"}, 0) {
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
			ResultSet rs=stmt.executeQuery("select * from books");
			while(rs.next()) {
				int book_id = rs.getInt("book_id");
				String name = rs.getString("book_name");
			    String author = rs.getString("book_author");
			    String publisher = rs.getString("book_publisher");
			    int qty = rs.getInt("book_quantity");
			    tableModel.addRow(new Object[]{book_id,name, author, publisher, qty});
			}
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		jt.setModel(tableModel);
		jt.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		
		
		jt.setBounds(30,100,600,400);
		JScrollPane sp=new JScrollPane(jt);
		sp.setBounds(30,100,600, 400);
		
		jl1=new JLabel("ALL BOOKS DETAIL");
		jl1.setForeground(Color.BLUE);
		jl1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    jl1.setBounds(230,40,400,50);
		
		jb3=new JButton("Edit Details");  
	    jb3.setBounds(170,550,300,40);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		if(jt.getSelectedRow() != -1) {
		    		jf1.dispose();
		    		new EditBook().editBookDetails(jt.getModel().getValueAt(jt.getSelectedRow(), 0).toString());
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
	    				new LibrarianMainPage().showLibrarianPage();
	    	        }  
	    });
		
		jf1.add(sp);
		jf1.add(jb3);
		jf1.add(jb1);
		jf1.add(jl1);
		
		jf1.setSize(680,680);
		jf1.setLayout(null); 
	    jf1.setVisible(true);
	}
	
}
