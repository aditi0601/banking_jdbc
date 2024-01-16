package com.amdocs.jdbcproject;

import java.sql.*;

public class BankData {
	
	public static void display(Connection con) {
			try {
				//Class.forName("com.mysql.cj.jdbc.Driver");
				
				//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "student");
				
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("Select * from bank_transactions");
				System.out.println("Bank Transactions");
				System.out.println("-----------------");
				
				while(rs.next()) {
					String trans_id = rs.getString(1);
					int acc_no = rs.getInt(2);
					double old_bal = rs.getDouble(3);
					String trans_type = rs.getString(4);
					double trans_amount = rs.getDouble(5);
					double new_balance=rs.getDouble(6);
					String status=rs.getString(7);
					
					
					
					System.out.println(trans_id+"|"+acc_no+"|"+old_bal+"|"+trans_type+"|"+trans_amount+"|"+new_balance+"|"+status);
				}
				
				ResultSet vt = stmt.executeQuery("Select * from valid_transactions");
				
				System.out.println("\nValid transactions");
				System.out.println("-------------------");
				
				while(vt.next()) {
					String trans_id = vt.getString(1);
					String trans_type = vt.getString(2);
					double trans_amount = vt.getDouble(3);
					String status = vt.getString(4);
					
					System.out.println(trans_id+"|"+trans_type+"|"+trans_amount+"|"+status);
				}
				
				ResultSet ivt = stmt.executeQuery("Select * from invalid_transactions");
				
				System.out.println("\nInValid transactions");
				System.out.println("-------------------");
				
				while(ivt.next()) {
					String trans_id = ivt.getString(1);
					String trans_type = ivt.getString(2);
					double trans_amount = ivt.getDouble(3);
					String status = ivt.getString(4);
					
					System.out.println(trans_id+"|"+trans_type+"|"+trans_amount+"|"+status);
				}
			}
			
			catch(Exception e) {
				e.printStackTrace();
				}
	}
	
}
