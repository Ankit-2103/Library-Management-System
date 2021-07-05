import java.sql.*;  
class DataBaseConnection{
	
static Connection con=null;	
static Connection getConnection(){  
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library_schema","root","");
		System.out.println("DataBase Connected");
		return con;


	}

	catch(Exception e){ 
		e.printStackTrace();
		return null;
	}  
}  
}  