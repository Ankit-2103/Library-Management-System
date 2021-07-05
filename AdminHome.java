import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminHome {
	
	static JFrame f;
	static JButton b1,b2,b3;
	static JLabel jl;

	public void adminHome() {
		
	f=new JFrame("ADMIN HOME PAGE");
	
	jl=new JLabel("ADMIN HOME PAGE");
	jl.setForeground(Color.BLUE);
	jl.setFont(new Font("Tahoma", Font.PLAIN, 18));
    jl.setBounds(95,50,300,50);
		
	b1=new JButton("Add Librarian");  
    b1.setBounds(50,150,260,30); 
    b1.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){
    			f.dispose();
    			new AddLibrarian().addLibrarian();
    	 }  
    });
    
    
    b2=new JButton("Librarian List");  
    b2.setBounds(50,200,260,30);
    b2.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){
    		f.dispose();
    		new LibrarianList().showLibrarianList();
	    }  
	});
    
    b3=new JButton("Logout");  
    b3.setBounds(280,5,80,30);
    b3.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){
    		f.dispose();
    		//LoginPage.main(new String[]{"null"});
    		new AdminLogin().showAdminForm();
	    }  
	});
    
    f.add(jl);
    
    f.add(b1);
    f.add(b2);
    f.add(b3);
    
    f.setSize(400,400);  
    f.setLayout(null);  
    f.setVisible(true);
	}
}
