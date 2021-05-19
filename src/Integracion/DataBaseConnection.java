package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection{
	private static String url = "jdbc:mysql://bbddsrv10.dondominio.com:3306/ddb167984";
	private static String user = "ddb167984";
	private static String pass = "Prueba11";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
}
