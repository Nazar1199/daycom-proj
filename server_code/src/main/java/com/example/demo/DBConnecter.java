package com.example.demo;
import java.sql.*;

public class DBConnecter {
	public static Connection getDBConnection() throws SQLException{
		final String DB_URL = "jdbc:postgresql://ec2-79-125-4-72.eu-west-1.compute.amazonaws.com:5432/d5vvho6r4hkm3j?user=zlufjjlafdlitx&password=4d4518229d479ce3a49bf6977d361519f320c60329c405150f0d2f3631085e0d"; 
		//Class.forName("org.postgresql.Driver");
		Connection c=DriverManager.getConnection(DB_URL);
		c.setAutoCommit(false);
		System.out.println("Connected!");
		return c;
	}
}
