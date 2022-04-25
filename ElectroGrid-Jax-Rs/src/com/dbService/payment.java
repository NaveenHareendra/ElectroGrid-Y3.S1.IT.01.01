package com.dbService; 
import java.sql.*; 
public class payment
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
public String insertPayment(String cus_name, String cus_email, String cus_contact, String cus_amount,String payment_date) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into payment  (`paymentID`,`customerName`,`email`,`contactNo`,`amount`,`paymentDate`)"
 + " values (?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, cus_name); 
 preparedStmt.setString(3, cus_email); 
 preparedStmt.setString(4, cus_contact); 
 preparedStmt.setString(5, cus_amount); 
 preparedStmt.setString(6, payment_date); 
 
 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readPayment() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Customer namer</th><th>email</th>" +
 "<th>contactNo</th>" + 
 "<th>amount</th>" +
 "<th>paymentDate</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from payment"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String paymentID = Integer.toString(rs.getInt("paymentID")); 
 String customerName = rs.getString("customerName"); 
 String email = rs.getString("email"); 
 String contactNo = rs.getString("contactNo");
 String amount = rs.getString("amount");
 String paymentDate = rs.getString("paymentDate");

 // Add into the html table
 output += "<tr><td>" + customerName + "</td>"; 
 output += "<td>" + email + "</td>"; 
 output += "<td>" + contactNo + "</td>"; 
 output += "<td>" + amount + "</td>"; 
 output += "<td>" + paymentDate + "</td>"; 

 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" +paymentID 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updatePayment(String payment_ID,String cus_name, String cus_email, String cus_contact, String cus_amount,String payment_date) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE payment SET customerName=?,email=?,contactNo=?,amount=?,paymentDate=? WHERE paymentID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, cus_name); 
 preparedStmt.setString(2, cus_email); 
 preparedStmt.setString(3, cus_contact); 
 preparedStmt.setString(4,cus_amount); 
 preparedStmt.setString(5,payment_date); 
 
 preparedStmt.setInt(6, Integer.parseInt(payment_ID)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String deletePayment(String paymentID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from payment where paymentID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(paymentID));
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 
