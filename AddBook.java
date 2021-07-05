import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddBook {

	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	static JButton jb1,jb2,jb3,jb4;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	public void addBook() {
		JFrame jf1=new JFrame("ADD BOOK");
		jl7=new JLabel("ADD BOOK FORM");
		jl7.setForeground(Color.BLUE);
		jl7.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl7.setBounds(135,30,200,20);
		
		jl5=new JLabel("Book ID");  
	    jl5.setBounds(30,60,90,30);
	    
		jl1=new JLabel("Book Name");  
	    jl1.setBounds(30,150,90,30);
	    
	    jl2=new JLabel("Author");
	    jl2.setBounds(30,200,90,30);
	    
	    jl3=new JLabel("Publisher");  
	    jl3.setBounds(30,250,90,30);
	    
	    jl4=new JLabel("Quantity");
	    jl4.setBounds(30,300,90,30);
	    
	    
	    jtf5=new JTextField("");  
	    jtf5.setBounds(180,60,250,30); 
	    jl6=new JLabel("Note: Book Id is Optional, Unique Number will be Assigned if you don't enter.");  
	    jl6.setBounds(30,100,400,30);
	    jl6.setForeground(Color.RED);
		jl6.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    
	    jtf1=new JTextField("");  
	    jtf1.setBounds(180,150,250,30); 
	    
	    jtf2=new JTextField("");  
	    jtf2.setBounds(180,200,250,30); 
	    
	    jtf3=new JTextField("");  
	    jtf3.setBounds(180,250,250,30); 
	    
	    jtf4=new JTextField("1");  
	    jtf4.setBounds(180,300,250,30); 
	    jtf4.setEditable(false);
	    
	    jb1=new JButton("Add");  
	    jb1.setBounds(50,360,180,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				try {
		    				con=DataBaseConnection.getConnection();
		    				if(jtf5.getText().trim().equals("")) {
		    					pstmt=con.prepareStatement("insert into books(book_name,book_author,book_publisher) values (?,?,?)");
		    					pstmt.setString(1, jtf1.getText().trim());
			    				pstmt.setString(2, jtf2.getText().trim());
			    				pstmt.setString(3, jtf3.getText().trim());
		    				}
		    				else {
			    				pstmt=con.prepareStatement("insert into books(book_id,book_name,book_author,book_publisher) values (?,?,?,?)");
			    				pstmt.setInt(1, Integer.parseInt(jtf5.getText().trim()));
			    				pstmt.setString(2, jtf1.getText().trim());
			    				pstmt.setString(3, jtf2.getText().trim());
			    				pstmt.setString(4, jtf3.getText().trim());
		    				}
		    				
		    				int insertResp=pstmt.executeUpdate();
					
					
					  if(insertResp==1) { 
						  JOptionPane.showMessageDialog(jf1,"Book Added Successfully");
						  jtf1.setText("");
						  jtf2.setText("");
						  jtf3.setText("");
					  }
		    				
		    				pstmt.close();
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
	    				jtf3.setText("");
	    	        }  
	    });
	    
	    jb3=new JButton("View All Books");  
	    jb3.setBounds(50,400,380,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ViewBooks().showBooks();
	    				
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
	    jf1.add(jl5);
	    jf1.add(jl6);
	    jf1.add(jl7);
	    
	    jf1.add(jtf1);
	    jf1.add(jtf2);
	    jf1.add(jtf3);
	    jf1.add(jtf4);
	    jf1.add(jtf5);
	    
	    jf1.add(jb1);
	    jf1.add(jb2);
	    jf1.add(jb3);
	    jf1.add(jb4);
	    
	    jf1.setSize(500,500);  
	    jf1.setLayout(null);  
	    jf1.setVisible(true);
	}
}


