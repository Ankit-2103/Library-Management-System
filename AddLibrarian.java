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

public class AddLibrarian {

	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	static JButton jb1,jb2,jb3,jb4;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	public void addLibrarian() {
		JFrame jf1=new JFrame("ADD LIBRARIAN FORM");
		jl6=new JLabel("ADD LIBRARIAN FORM");
		jl6.setForeground(Color.BLUE);
		jl6.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl6.setBounds(125,30,200,20);
		
		jl1=new JLabel("Name");  
	    jl1.setBounds(30,70,90,30);
	    
	    jl2=new JLabel("Father Name");
	    jl2.setBounds(30,120,90,30);
	    
	    jl3=new JLabel("Email ID");  
	    jl3.setBounds(30,170,90,30);
	    
	    jl4=new JLabel("Mobile Number");
	    jl4.setBounds(30,220,90,30);
	    
	    jl5=new JLabel("Password");
	    jl5.setBounds(30,270,90,30);
	    
	    jtf1=new JTextField("");  
	    jtf1.setBounds(180,70,250,30); 
	    
	    jtf2=new JTextField("");  
	    jtf2.setBounds(180,120,250,30); 
	    
	    jtf3=new JTextField("");  
	    jtf3.setBounds(180,170,250,30); 
	    
	    jtf4=new JTextField("");  
	    jtf4.setBounds(180,220,250,30); 
	    
	    jtf5=new JTextField("");  
	    jtf5.setBounds(180,270,250,30); 
	    
	    jb1=new JButton("Add");  
	    jb1.setBounds(50,360,180,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				try {
		    				con=DataBaseConnection.getConnection();
		    				pstmt=con.prepareStatement("insert into users(name,f_name,email_id,mobile_no,password) values (?,?,?,?,?)");
		    				
		    				pstmt.setString(1, jtf1.getText().trim());
		    				pstmt.setString(2, jtf2.getText().trim());
		    				pstmt.setString(3, jtf3.getText().trim());
		    				pstmt.setString(4, jtf4.getText().trim());
		    				pstmt.setString(5, jtf5.getText().trim());
		    				
		    				int insertResp=pstmt.executeUpdate();
					
					
					  if(insertResp>=1) { 
						  
						  	SendMail.send(jtf3.getText().trim(),"Librarian Added Successfully",jtf5.getText().trim(),jtf1.getText().trim()); 
		    				JOptionPane.showMessageDialog(jf1,"Librarian Added Successfully. Login Details sent to Librarian.");
		    				jtf1.setText("");
		    				jtf2.setText("");
		    				jtf3.setText("");
		    				jtf4.setText("");
		    				jtf5.setText("");
		    				System.out.println("insertUpdate---"+insertResp);
		    				pstmt.close();
		    				con.close();
					  }
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
	    				jtf4.setText("");
	    				jtf5.setText("");
	    	        }  
	    });
	    
	    jb3=new JButton("Librarian List");  
	    jb3.setBounds(50,400,380,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new LibrarianList().showLibrarianList();
	    				
	    	        }  
	    });
	    jb4=new JButton("Back");  
	    jb4.setBounds(5,5,100,30);
	    jb4.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				jf1.dispose();
	    				//new AdminLogin().showAdminForm();
	    				new AdminHome().adminHome();
	    	        }  
	    });
	    
	    jf1.add(jl1);
	    jf1.add(jl2);
	    jf1.add(jl3);
	    jf1.add(jl4);
	    jf1.add(jl5);
	    jf1.add(jl6);
	    
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


