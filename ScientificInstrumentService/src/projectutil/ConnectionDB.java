package projectutil;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class ConnectionDB {
	//String url = "jdbc:mysql://localhost:3309/sciinstservicedb?characterEncoding=UTF-8";
	String url = "jdbc:mysql://localhost:3306/sciinstservicedb?characterEncoding=UTF-8";
	String uname = "sciinstservice";
	String pwd = "sci2021";
	Connection con = null;
	public Connection getConection() {
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con =  DriverManager.getConnection(url, uname, pwd);
	}catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	return con;
}
	public void closeConnection() {
		try {
			con.close();
		}catch(Exception e) {
			
		}
	}
}
