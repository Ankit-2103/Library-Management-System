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
import javax.swing.JTextField;

public class LibrarianEdit {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1,jl2,jl3,jl4,jl5;
	static JButton jb1,jb2,jb3;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	static Statement stmt=null;
	String activeStatus=null;
	
	public void editLibrarian(String emailId,String activeStatus) {
		System.out.println("Email id "+emailId);
		System.out.println("Active Status: "+activeStatus);
		this.activeStatus=activeStatus;
		try {
		con=DataBaseConnection.getConnection();
		stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from users where email_id='"+emailId+"'");
			rs.next();
		
		
		JFrame jf1=new JFrame("EDIT LIBRARIAN FORM");
		
		jl1=new JLabel("Name");  
	    jl1.setBounds(30,50,90,30);
	    
	    jl2=new JLabel("Father Name");
	    jl2.setBounds(30,100,90,30);
	    
	    jl3=new JLabel("Email ID");  
	    jl3.setBounds(30,150,90,30);
	    
	    jl4=new JLabel("Mobile Number");
	    jl4.setBounds(30,200,90,30);
	    
	    
	    jtf1=new JTextField(rs.getString("name"));  
	    jtf1.setBounds(180,50,250,30); 
	    
	    jtf2=new JTextField(rs.getString("f_name"));  
	    jtf2.setBounds(180,100,250,30); 
	    
	    jtf3=new JTextField(rs.getString("email_id"));  
	    jtf3.setBounds(180,150,250,30); 
	    jtf3.setEditable(false);
	    
	    jtf4=new JTextField(rs.getString("mobile_no"));  
	    jtf4.setBounds(180,200,250,30); 
	    
	    
	    rs.close();
	    stmt.close();
	    
	    jb1=new JButton("Update");  
	    jb1.setBounds(50,360,180,30);
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		try {
				con=DataBaseConnection.getConnection();
				pstmt=con.prepareStatement("update users set name=?,f_name=?,mobile_no=? where email_id=?");
				
				pstmt.setString(1, jtf1.getText().trim());
				pstmt.setString(2, jtf2.getText().trim());
				pstmt.setString(3, jtf4.getText().trim());
				pstmt.setString(4, jtf3.getText().trim());
				
				int insertResp=pstmt.executeUpdate();
				System.out.println("records update:- "+insertResp);
				if(insertResp>=1) {
					JOptionPane.showMessageDialog(jf1,"Record Updated Successfully."); 
					jf1.dispose();
					new LibrarianList().showLibrarianList();
				}
				
				pstmt.close();
	    		} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
	    				
	    	 }  
	    });
	    
	    jb2=new JButton("Back to List");  
	    jb2.setBounds(250,360,180,30);
	    jb2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new LibrarianList().showLibrarianList();
	    	        }  
	    });
	    
	    if(activeStatus.equalsIgnoreCase("Activated")) {
	    	jb3=new JButton("Deactivate");  
	    }
	    else {
	    	jb3=new JButton("Activate");
	    }
	    jb3.setBounds(50,400,380,30);
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		
	    		try {
					con=DataBaseConnection.getConnection();
					pstmt=con.prepareStatement("update users set active=? where email_id=?");
					if(activeStatus.equalsIgnoreCase("Activated")) {
						pstmt.setInt(1, 0);
					}
					else {
						pstmt.setInt(1, 1);
					}
					pstmt.setString(2, jtf3.getText().trim());
					
					int insertResp=pstmt.executeUpdate();
					
					System.out.println("records update:- "+insertResp);
					
					if(insertResp>=1) {
						JOptionPane.showMessageDialog(jf1,"Record Updated Successfully.");
						jf1.dispose();
						new LibrarianList().showLibrarianList();
					}
					
					pstmt.close();
		    		} 
					catch (SQLException e1) {
						e1.printStackTrace();
					}
	    				
	    	}  
	    });
	    
		
	    
	    jf1.add(jl1);
	    jf1.add(jl2);
	    jf1.add(jl3);
	    jf1.add(jl4);
	    //jf1.add(jl5);
	    
	    jf1.add(jtf1);
	    jf1.add(jtf2);
	    jf1.add(jtf3);
	    jf1.add(jtf4);
	    //jf1.add(jtf5);
	    
		
		jf1.add(jb1); jf1.add(jb2); jf1.add(jb3);
		 
	    
	    jf1.setSize(500,500);  
	    jf1.setLayout(null);  
	    jf1.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
