import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class IssueBook {

	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5,jl7;
	static JButton jb1,jb2,jb3,jb4;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	static PreparedStatement pstmt1=null;
	static Statement stmt,stmt1=null;
	boolean flag=true;
	public void issueBook() {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try{
		    c.setTime(sdf.parse(date.format(now)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		   
		//Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, 14);
		
		JFrame jf1=new JFrame("ISSUE BOOK");
		jl7=new JLabel("ISSUE BOOK FORM");
		jl7.setForeground(Color.BLUE);
		jl7.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl7.setBounds(135,30,200,20);
	    
		jl1=new JLabel("Book ID");  
	    jl1.setBounds(30,70,90,30);
	    
	    jl2=new JLabel("Student ID");
	    jl2.setBounds(30,120,90,30);
	    
	    jl3=new JLabel("Issue Date");  
	    jl3.setBounds(30,170,90,30);
	    
	    jl4=new JLabel("Return Upto");
	    jl4.setBounds(30,220,90,30);
	    
	    
	    jtf1=new JTextField("");  
	    jtf1.setBounds(180,70,250,30); 
	    
	    jtf2=new JTextField("");  
	    jtf2.setBounds(180,120,250,30); 
	    
	    jtf3=new JTextField(date.format(now)+"");  
	    jtf3.setBounds(180,170,250,30);
	    jtf3.setEditable(false);
	    
	    jtf4=new JTextField(sdf.format(c.getTime())+"");  
	    jtf4.setBounds(180,220,250,30); 
	    jtf4.setEditable(false);
	    
	    
	    jb1=new JButton("Issue");  
	    jb1.setBounds(50,360,180,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				try {
		    				con=DataBaseConnection.getConnection();
		    				stmt=con.createStatement();
		    				ResultSet rs=stmt.executeQuery("select book_quantity from books where book_id='"+jtf1.getText().trim()+"'");
		    				rs.next();
		    				int qty=rs.getInt("book_quantity");
		    				System.out.println("Current Qty "+qty);
		    				stmt.close();
		    				
		    				stmt1=con.createStatement();
		    				ResultSet rs1=stmt1.executeQuery("select return_date from issuebook where book_id='"+jtf1.getText().trim()+"'");
		    				while(rs1.next()) {
		    					System.out.println("All data: "+rs1.getString("return_date"));
		    					if(rs1.getString("return_date").equalsIgnoreCase("NA")) {
		    						System.out.println("Book Already Issued to someone!!!");
		    						JOptionPane.showMessageDialog(jf1,"Book is Already Issued to Someone!!!","Alert",JOptionPane.WARNING_MESSAGE);
		    						flag=false;
		    						break;
		    					}
		    					else {
		    						System.out.println("Not Issued to anyone!!!");
		    					}
		    				}
		    				stmt1.close();
		    				
		    				if(flag) {
			    				pstmt=con.prepareStatement("insert into issuebook(book_id,student_id,issue_date,return_upto) values (?,?,?,?)");
			    				pstmt.setString(1, jtf1.getText().trim());
			    				pstmt.setString(2, jtf2.getText().trim());
			    				pstmt.setString(3, jtf3.getText().trim());
			    				pstmt.setString(4, jtf4.getText().trim());
			    				
			    				int insertResp=pstmt.executeUpdate();
						
				    			if(insertResp>=1) { 
								  pstmt1=con.prepareStatement("update books set book_quantity=? where book_id=?");
								  pstmt1.setString(2, jtf1.getText().trim());
								  pstmt1.setInt(1, qty-1);
								  pstmt1.executeUpdate();
								  
								  JOptionPane.showMessageDialog(jf1,"Book Issued Successfully");
								  jtf1.setText("");
								  jtf2.setText("");
				    			}
			    				pstmt.close();
			    				pstmt1.close();
		    				}
		    				con.close();
	    				}
	    				catch(Exception ex) {
	    					ex.printStackTrace();
	    					JOptionPane.showMessageDialog(jf1,"Something Went Wrong. Please try again!!!","Alert",JOptionPane.WARNING_MESSAGE);
	    				}
	    	        }  
	    });
	    
	    jb2=new JButton("Reset");  
	    jb2.setBounds(250,360,180,30);
	    jb2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jtf1.setText("");
	    				jtf2.setText("");
				/*
				 * jtf3.setText(""); jtf4.setText("");
				 */
	    	        }  
	    });
	    
	    jb3=new JButton("View Issue Books");  
	    jb3.setBounds(50,400,380,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ViewIssuedBooks().viewIssuedBooks();
	    	        }  
	    });
	    jb4=new JButton("Back");  
	    jb4.setBounds(5,5,100,30);
	    jb4.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jf1.dispose();
	    				new LibrarianMainPage().showLibrarianPage();
	    	        }  
	    });
	    
	    jf1.add(jl1);
	    jf1.add(jl2);
	    jf1.add(jl3);
	    jf1.add(jl4);
	    jf1.add(jl7);
	    
	    jf1.add(jtf1);
	    jf1.add(jtf2);
	    jf1.add(jtf3);
	    jf1.add(jtf4);
	    
	    jf1.add(jb1);
	    jf1.add(jb2);
	    jf1.add(jb3);
	    jf1.add(jb4);
	    
	    jf1.setSize(500,500);  
	    jf1.setLayout(null);  
	    jf1.setVisible(true);
	    
	    
	}
}


