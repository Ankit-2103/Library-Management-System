import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LibrarianMainPage {
	
	static JFrame jf1;
	static JTextField jtf1,jtf2,jtf3,jtf4,jtf5;
	static JLabel jl1;
	static JButton jb1,jb2,jb3,jb4,jb5,jb6;
	static Connection con=null;
	static PreparedStatement pstmt=null;
	
	public void showLibrarianPage() {
		
		jf1=new JFrame("Librarian Home Page");
		jl1=new JLabel("LIBRARIAN HOME PAGE");
		jl1.setForeground(Color.BLUE);
		jl1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    jl1.setBounds(95,40,300,50);
		
		jb1=new JButton("Add Books");  
	    jb1.setBounds(60,100,260,35); 
	    jb1.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new AddBook().addBook();
	    	 }  
	    });
	    
	    
	    jb2=new JButton("View Books");  
	    jb2.setBounds(60,150,260,35);
	    jb2.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ViewBooks().showBooks();
		    }  
		});
	    
	    jb3=new JButton("Issue Book");  
	    jb3.setBounds(60,200,260,35); 
	    jb3.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new IssueBook().issueBook();
	    	 }  
	    });
	    
	    
	    jb4=new JButton("View Issued Books");  
	    jb4.setBounds(60,250,260,35);
	    jb4.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ViewIssuedBooks().viewIssuedBooks();
		    }  
		});
	    
	    jb5=new JButton("Return Book");  
	    jb5.setBounds(60,300,260,35);
	    jb5.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new ReturnBook().returnBook();
		    }  
		});
	    
	    jb6=new JButton("Logout");  
	    jb6.setBounds(60,350,260,35);
	    jb6.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		jf1.dispose();
	    		new LibraryLogin().showLibraryForm();
	    		}  
		});
	    jf1.add(jl1);
	    jf1.add(jb1);
	    jf1.add(jb2);
	    jf1.add(jb3);
	    jf1.add(jb4);
	    jf1.add(jb5);
	    jf1.add(jb6);
	    
		jf1.setSize(400,500);  
	    jf1.setLayout(null);  
	    jf1.setVisible(true);
		
	}

}
