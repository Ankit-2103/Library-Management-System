import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginPage {
	static JFrame f,f1,f2;
	static JButton b1,b2;
	static JTextField tf1;
	static JLabel jl1;
	
	
	

	public static void main(String[] args) {
		f=new JFrame("Library Management Login Page"); 
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		jl1=new JLabel("WELCOME TO LOGIN PAGE");  
		jl1.setForeground(Color.BLUE);
		jl1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl1.setBounds(75,50,300,50);
		//new LoginPage();
	    
		b1=new JButton("Admin Login");  
	    b1.setBounds(50,150,260,30); 
	    b1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    				f.dispose();
	    				new AdminLogin().showAdminForm();
	    	        }  
	    });
	    
	    
	    
	    b2=new JButton("Librarian Login");  
	    b2.setBounds(50,200,260,30); 
	    b2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
		    				f.dispose();
		    				new LibraryLogin().showLibraryForm();
		    	        }  
		});
	    
	    
	    f.add(b1);
	    f.add(b2);
	    f.add(jl1);
	    
	    f.setSize(400,400);  
	    f.setLayout(null);  
	    f.setVisible(true);  

	}

}
