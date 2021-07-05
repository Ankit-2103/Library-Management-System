import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;

class LibraryLogin{
	static JTextField jtf1;
	static JPasswordField jtf2;
	static JLabel jl,jl1,jl2;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static Statement stmt=null;
	boolean flag=true;
	static JFrame f2;
	
	public void showLibraryForm() {
		f2=new JFrame("Librarian Login Page");
		
		jl=new JLabel("LIBRARY LOGIN PAGE");
		jl.setForeground(Color.BLUE);
		jl.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl.setBounds(155,50,300,50);
		
		jl1=new JLabel("Username");  
	    jl1.setBounds(50,150,70,30);
	    
	    jl2=new JLabel("Password");
	    jl2.setBounds(50,200,70,30);
		
	    jtf1=new JTextField("");  
	    jtf1.setBounds(180,150,250,30); 
	    
	    jtf2=new JPasswordField("");  
	    jtf2.setBounds(180,200,250,30); 
	    
	    jb1=new JButton("Back");  
	    jb1.setBounds(5,5,100,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				f2.dispose();
	    				LoginPage.main(new String[]{"null"});
	    	        }  
	    });
	    
	    jb2=new JButton("Login");  
	    jb2.setBounds(50,300,180,30);
	    jb2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				try {
		    				con=DataBaseConnection.getConnection();
		    				stmt=con.createStatement();
		    				ResultSet rs=stmt.executeQuery("select * from users");
		    			
		    				System.out.println(jtf2.getText());
		    				if(jtf1.getText().trim().equals("") || jtf2.getText().trim().equals("")) {
	    						JOptionPane.showMessageDialog(f2,"Please Fill the Required Fields!!!","Alert",JOptionPane.WARNING_MESSAGE);
	    					}
		    				else {
			    				while(rs.next()) {
			    					if(jtf1.getText().trim().equalsIgnoreCase(rs.getString("email_id")) 
			    							&& jtf2.getText().trim().equals(rs.getString("password")) && rs.getInt("active")==1 ) {
			    						flag=false;
			    					    f2.dispose();
			    					    new LibrarianMainPage().showLibrarianPage();
			    						break;
			    					}
			    				}
		    				}
		    				if(flag) {
		    						JOptionPane.showMessageDialog(f2,"Username or Password do not Match!!!","Alert",JOptionPane.WARNING_MESSAGE); 
		    						System.out.println("Not Match");
		    				}
		    				con.close();
		    				stmt.close();
	    				}
	    				catch(Exception ex) {
	    					ex.printStackTrace();
	    				}
	    	        }  
	    });
	    
	    jb3=new JButton("Reset");  
	    jb3.setBounds(250,300,180,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jtf1.setText("");
	    		jtf2.setText("");
	    	 }  
	    });
	    
	    f2.add(jl);
	    f2.add(jl1);
	    f2.add(jl2);
	    f2.add(jtf1);
	    f2.add(jtf2);
	    f2.add(jb1);
	    f2.add(jb2);
	    f2.add(jb3);
	    
		
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setSize(500,500);
		f2.setLayout(null); 
		f2.setVisible(true); 
		
		
	}
}