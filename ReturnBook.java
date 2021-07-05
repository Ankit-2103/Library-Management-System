import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ReturnBook {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2;
	static JLabel jl1,jl2,jl3;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	static Statement stmt=null;
	String activeStatus=null;
	
	public void returnBook() {
		
		JFrame jf1=new JFrame("Return Book");
		jl3=new JLabel("RETURN BOOK FORM");
		jl3.setForeground(Color.BLUE);
		jl3.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl3.setBounds(135,30,200,20);
		
		jl1=new JLabel("Book ID");  
	    jl1.setBounds(30,70,90,30);
	    
	    jl2=new JLabel("Student ID");  
	    jl2.setBounds(30,120,90,30);
	    
	    
	    jtf1=new JTextField("");  
	    jtf1.setBounds(180,70,250,30); 
	    
	    jtf2=new JTextField("");  
	    jtf2.setBounds(180,120,250,30); 
	    
		
	    
	    
	    jb1=new JButton("Return");  
	    jb1.setBounds(50,310,380,35);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		try {
	    			DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    			LocalDateTime now = LocalDateTime.now();
				con=DataBaseConnection.getConnection();
				
				stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select book_quantity from books b join issuebook i on i.book_id=b.book_id where i.book_id='"+jtf1.getText().trim()+"' and i.student_id='"+jtf2.getText().trim()+"'");
				rs.next();
				int qty=rs.getInt("book_quantity");
				qty=qty+1;
				System.out.println("Updated Qty "+qty);
				stmt.close();
				
				pstmt=con.prepareStatement("update books b,issuebook i set b.book_quantity=?,i.issuebook_status=?,i.return_date=? where b.book_id=? AND i.book_id=? AND i.return_date=?");
				pstmt.setInt(1, qty);
				pstmt.setString(2, "Returned");
				pstmt.setString(3,date.format(now)+"");
				pstmt.setInt(4, Integer.parseInt(jtf1.getText().trim()));
				pstmt.setInt(5, Integer.parseInt(jtf1.getText().trim()));
				pstmt.setString(6, "NA");
				
				
				int insertResp=pstmt.executeUpdate();
				System.out.println("records update:- "+insertResp);
				
				if(insertResp >=1) {
					JOptionPane.showMessageDialog(jf1,"Book Returned Successfully");
				}
				
				pstmt.close();
				con.close();
				
			    
	    		} 
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(jf1,"Something went wrong!!!","Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
	    				
	    	 }  
	    });
	    
		
	    
	    jb2=new JButton("View Issue Books");  
	    jb2.setBounds(50,370,380,35);
	    jb2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ViewIssuedBooks().viewIssuedBooks();
	    	        }  
	    });
	    
	    jb3=new JButton("Back");  
	    jb3.setBounds(5,5,100,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jf1.dispose();
	    				new LibrarianMainPage().showLibrarianPage();
	    	        }  
	    });
	    
		
		
	    
	    jf1.add(jl1);
	    jf1.add(jl2);
	    jf1.add(jl3);
		
	    
	    jf1.add(jtf1);
	    jf1.add(jtf2);
		
		
		
		jf1.add(jb1);
		jf1.add(jb2);
		jf1.add(jb3);
		 
	    
	    jf1.setSize(500,500);  
	    jf1.setLayout(null);  
	    jf1.setVisible(true);
		
	}

}
