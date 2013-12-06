package examples;

import handlers.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import executor.SimpleExecutor;

public class ConnectWithStatements {
	public static void connect(){
		Connection connection = SimpleExample.getConnection();
		SimpleExecutor exec = new SimpleExecutor();
		try {
			exec.execUpdate(connection, "create table users (id bigint auto_increment, user_name varchar(256), primary key (id))");
			System.out.append("Table created\n");
			exec.execUpdate(connection, "insert into users (user_name) values ('tully2')");
			System.out.append("User added\n");
			
			exec.execQuery(connection, "select user_name from users where id=1", new ResultHandler(){
				public void handle(ResultSet result) throws SQLException {
					result.next();
					System.out.append("Read user: " + result.getString("user_name") + '\n');				
				}				
			});					
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try{
			exec.execUpdate(connection, "drop table users");
			System.out.append("Done!\n");
			
			connection.close();	
			} catch(Exception ignore){}
		}
	}
	

}
