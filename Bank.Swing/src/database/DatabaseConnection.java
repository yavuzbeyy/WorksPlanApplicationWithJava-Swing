package database;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {// Super Class

	private final String HOST = "localhost";
	private final String PORT = "3306";
	private final String DB_NAME = "DB_Works";
	private final String userString = "root";
	private final String passwordString = "" ;
	
	public Connection connection = null;
	public Statement statement = null;
	
	public DatabaseConnection() {
		
		String urlString = "jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DB_NAME;
		try {
			this.connection = DriverManager.getConnection(urlString,this.userString,this.passwordString);
			System.out.println("Bağlantı Başarılı.");
		} catch (SQLException e) {
			System.out.println("Bağlantı Başarısız.");
			e.printStackTrace();
		}
		
	}
}
