package Integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection{
//	private static String user = "ddb167984";
//	private static String url = "jdbc:mysql://bbddsrv10.dondominio.com:3306/ddb167984";
	
//	private static String user = "ddb170242";
//	private static String url = "jdbc:mysql://bbddsrv10.dondominio.com:3306/ddb170242";
	
//  private static String user = "is2021taller";
//  private static String url = "jdbc:mysql://db4free.net:3306/is2021taller";

//	private static String user = "taller";
//	private static String url = "jdbc:mysql://taller.gonzalovilchez.es:9906/taller";
	
//	private static String user = "admin";
//	private static String url = "jdbc:postgresql://database-1.cxxptdwal79m.eu-west-3.rds.amazonaws.com";
	
	private static String user = "admin";
	private static String url = "jdbc:mysql://database-1.cxxptdwal79m.eu-west-3.rds.amazonaws.com:3306/is";
	private static String pass = "breton37";
	
	public static Connection getConnection() throws SQLException {
		 Connection con = DriverManager.getConnection(url, user, pass);
	  	 return con;
	}

}
