package examples;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleExample {
	
	public static Connection getConnection() {
		try{
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
			
			StringBuilder url = new StringBuilder();
			
			url.
			append("jdbc:mysql://").		//db type
			append("localhost:"). 			//host name
			append("3306/").				//port
			append("lecture8?").			//db name
			append("user=tully&").			//login
			append("password=tully");		//password
			
			System.out.append("URL: " + url + "\n");
			
			Connection connection = DriverManager.getConnection(url.toString());
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void connect(){
		Connection connection = getConnection();
		System.out.append("Connected!\n");
		try {
			System.out.append("Autocommit: " + connection.getAutoCommit() + '\n');
			System.out.append("DB name: " + connection.getMetaData().getDatabaseProductName() + '\n');
			System.out.append("DB version: " + connection.getMetaData().getDatabaseProductVersion() + '\n');
			System.out.append("Driver: " + connection.getMetaData().getDriverName() + '\n');
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		 
	}
}
