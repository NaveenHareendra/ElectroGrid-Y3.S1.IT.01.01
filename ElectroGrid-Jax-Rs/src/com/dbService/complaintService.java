package com.dbService;
//it20032524 Warnakulasuriya M.A.N.H
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class complaintService{
	private Statement stmt;
	private Connection con;

	public void databaseConnection(){//Database connection
		try{
			System.out.println("Database Connection() Running...");			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con =DriverManager
					.getConnection("jdbc:mysql://localhost:3306/complaintservice", "root", "root");
			if(this.con!=null){
				System.out.println("Connected");
			}else{
				System.out.println("Not Connected!");
			}
			this.stmt = con.createStatement();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public boolean updateComplaint(int customerId, boolean status, int complaintNO){
		
		int updateStatus=0;
		
		try{
			System.out.println("CustomerId:"+customerId);
			System.out.println("status:"+status);
			System.out.println("complaintNO:"+complaintNO);
			databaseConnection();
			
			updateStatus = stmt.executeUpdate("update complaint set Complaint_Status="+status+" WHERE Complaint_No="+complaintNO+" AND CustomerId="+customerId+";");
			
			System.out.println("update Status value: "+updateStatus);
			
			con.close();
			
		}catch(SQLException SQ){
			System.out.println("Updation failed");
		}
		
		if(updateStatus!=0){
			System.out.println("Update Completed...");
			return true;
		}else{
			return false;
		}
	}
	
	public boolean deleteComplaint(int complaintId,
			int CustomerId){
		int updateStatus=0;
		
		try{
			databaseConnection();
			
			updateStatus=stmt.executeUpdate("DELETE FROM complaint WHERE complaint_no="+complaintId+" AND customerId="+CustomerId+";");
			con.close();
		}catch(SQLException sq){
			System.out.println("Deletion failed...");
			return false;
		}
		
		if(updateStatus!=0){
			System.out.println("Delete Completed...");
			return true;
		}
		
		return false;
	}
	
	public String readComplaints(){
		ResultSet rs;
		String output = "";
		try{
			databaseConnection();
			rs=stmt.executeQuery("Select *from Complaint");
			while(rs.next()){
				 String ComplaintNo = Integer.toString(rs.getInt("Complaint_No")); 
				 String CustomerId = rs.getString("CustomerId"); 
				 String Description = rs.getString("Description"); 
				 String complainDate = rs.getString("Complaint_Date");  
				 
				 output += "Complaint No: "+ComplaintNo+"\nCustomer Id: "+CustomerId+"\nDescription"+Description+"\nComplaint Added Date: "+complainDate+"\n";
				 output += "\n------------------------------------\n";
			}
			
			con.close();
			
			
		}catch(SQLException SQ){
			System.out.println("Exception: "+SQ);
		}
		
		return output;

	}
}
