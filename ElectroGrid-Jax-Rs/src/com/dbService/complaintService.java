package com.dbService;
//it20032524 Warnakulasuriya M.A.N.H
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;

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
	
	public String addComplaint(int customerId, String Description){
		boolean complaintStatus =false;
//		Date complaintDate =  new Date(0);
		int addCheck=0;
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
		System.out.println(customerId);
		System.out.println(Description);
		
		try{
			databaseConnection();
			addCheck=stmt.executeUpdate("INSERT INTO Complaint VALUES(0,'"+customerId+"','"+Description+"',DATE_FORMAT(now(), '%y%m%d'),"+complaintStatus+",'');");
			con.close();
		}catch(SQLException SQ){
			System.out.println("Action Incomplete: "+SQ);
		}
		if(addCheck!=0){
			System.out.println("Complaint Added...");
			return "Complaint Added...";
		}else{
			return "Complaint not added Added...";
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
	
	public String readCustomerComplaints(int customerId){
		ResultSet rs;
		String output = "";
		try{
			databaseConnection();
			rs=stmt.executeQuery("Select *from Complaint WHERE customerId="+customerId);
			
			 output = "<table border='1'><tr>"+
					 "<th>Complaint No</th><th>"+
					 "Customer Id</th>" +
					 "<th>Description</th>" + 
					 "<th>complainDate</th>"+
					 "<th>complain status</th>"+
					 "</tr>"; 
			 
			while(rs.next()){
				 String ComplaintNo = Integer.toString(rs.getInt("Complaint_No")); 
				 String CustomerId = rs.getString("CustomerId"); 
				 String Description = rs.getString("Description"); 
				 String complainDate = rs.getString("Complaint_Date");  
				 boolean complainStatus = rs.getBoolean("Complaint_Status");  
				 
				 output += "<tr><td>" + ComplaintNo + "</td>"; 
				 output += "<td>" + CustomerId + "</td>"; 
				 output += "<td>" + Description + "</td>"; 
				 output += "<td>" + complainDate + "</td>"; 
				 output += "<td>" + complainStatus + "</td>"; 
			}
			output += "</table>"; 
			con.close();
			
			
		}catch(SQLException SQ){
			System.out.println("Exception: "+SQ);
		}
		
		return output;

	}
}
