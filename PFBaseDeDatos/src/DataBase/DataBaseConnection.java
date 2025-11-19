package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/jugueteria";
	private static final String User = "root";
	private static final String Pass = "cfgs";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, User, Pass);
		
	}
}
