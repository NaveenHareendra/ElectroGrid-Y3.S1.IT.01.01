package com.dbService; 
import java.sql.*; 
public class complain
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "root"); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 

//method to insert complains
public String insertComplains(String name, String nic, String complain) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into complain (`complainID`,`customerName`,`customerNIC`,`complain`)"
 + " values (?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, nic); 
 preparedStmt.setString(4, complain); 

 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting complain."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


//method to read complains
public String readComplains() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr>"+
 "<th>Customer Name</th><th>"+
 "Customer NIC</th>" +
 "<th>Complain</th>" + 
 "</tr>"; 
 
 String query = "select * from complain"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String complainID = Integer.toString(rs.getInt("complainID")); 
 String customerName = rs.getString("customerName"); 
 String customerNIC = rs.getString("customerNIC"); 
 String complain = rs.getString("complain");  

 // Add into the html table
 output += "<tr><td>" + customerName + "</td>"; 
 output += "<td>" + customerNIC + "</td>"; 
 output += "<td>" + complain + "</td>"; 
 // buttons
  
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading complains."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 

} 
