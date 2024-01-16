package com.amdocs.jdbcproject;

import java.sql.*;

public class BankTransaction extends BankData{

	public static void transaction(Connection con) {
		// TODO Auto-generated method stub
		try {
			
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select * from bank_transactions");
			
			while(rs.next()) {
				String trans_id = rs.getString(1);
				int acc_no = rs.getInt(2);
				double old_bal = rs.getDouble(3);
				String trans_type = rs.getString(4);
				double trans_amount = rs.getDouble(5);
				double new_bal=0;
				String status="";
				
				if(trans_type.equals("W")||trans_type.equals("w")) {
					new_bal = old_bal - trans_amount;
					
					if(new_bal<0) {
						status="Invalid";
					}
					else {
						status="Valid";
					}
				}
				else if(trans_type.equals("D")||trans_type.equals("d")) {
					new_bal = old_bal + trans_amount;
					
					status="Valid";
				}
				
				
				
				PreparedStatement pstmt = con.prepareStatement("Update bank_transactions set new_bal=?, trans_stat=? where acc_no=?");
				
				pstmt.setDouble(1, new_bal);
				pstmt.setString(2, status);
				pstmt.setInt(3, acc_no);
				
				pstmt.executeUpdate();
				
				if(status.equals("Valid")){
					
					PreparedStatement in_stmt = con.prepareStatement("Insert into valid_transactions(trans_id, trans_type,trans_amount,validity) values(?,?,?,?)");
					
					in_stmt.setString(1, trans_id);
					in_stmt.setString(2, trans_type);
					in_stmt.setDouble(3, trans_amount);
					in_stmt.setString(4, status);
					
					in_stmt.executeUpdate();
					
				}
				else if(status.equals("Invalid")){
					
					PreparedStatement in_stmt = con.prepareStatement("Insert into invalid_transactions(trans_id, trans_type,trans_amount,validity) values(?,?,?,?)");
					
					in_stmt.setString(1, trans_id);
					in_stmt.setString(2, trans_type);
					in_stmt.setDouble(3, trans_amount);
					in_stmt.setString(4, status);
					
					in_stmt.executeUpdate();
					
				}
			}
			display(con);
		}
		catch(Exception e) {
			System.out.println("Duplicate entries");
			
			//e.printStackTrace();
		}
	}

}
