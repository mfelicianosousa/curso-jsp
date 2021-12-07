package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true" ;
	private static String driver = "org.postgresql.Driver" ;
	private static String user = "postgres" ;
	private static String senha = "DBAdmin" ;
	private static Connection connection = null ;
	
	public static Connection getConnection() {
		return connection ;
	}
	
	static {
		conectar() ;
	}
	
	public SingleConnectionBanco() {
		conectar() ;
	}
	
	private static void conectar() {
		
		try {
			
			if (connection == null) {
				Class.forName( driver ) ;
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);
				
			}
			System.out.println("Database Conected !");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}

}
