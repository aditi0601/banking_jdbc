package com.amdocs.jdbcproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test extends BankTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "student");
		
		transaction(con);
		
		con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
