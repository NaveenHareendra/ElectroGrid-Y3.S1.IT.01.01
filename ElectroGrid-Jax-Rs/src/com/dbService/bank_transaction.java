package com.dbService; 
import java.sql.*; 
public class bank_transaction 
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
public String insertTransaction(String bank_name, String branch, String tranaction_date) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into bank_transaction_details  (`transactionID`,`bankName`,`branch`,`transactionDate`)"
 + " values (?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, bank_name); 
 preparedStmt.setString(3, branch); 
 preparedStmt.setString(4, tranaction_date); 
 
 
 
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
public String readTransaction() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>bankName</th><th>branch</th>" +
 "<th>transactionDate</th>" + 

 "</tr>"; 
 
 String query = "select * from bank_transaction_details"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String transactionID = Integer.toString(rs.getInt("transactionID")); 
 String bankName = rs.getString("bankName"); 
 String branch = rs.getString("branch"); 
 String transactionDate = rs.getString("transactionDate");
 
 
 // Add into the html table
 output += "<tr><td>" + bankName + "</td>"; 
 output += "<td>" + branch + "</td>"; 
 output += "<td>" + transactionDate + "</td>"; 

 
 // buttons
 
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
public String updateTransaction(String transaction_ID,String bank_name, String branch, String tranaction_date) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE bank_transaction_details SET bankName=?,branch=?,transactionDate=?WHERE transactionID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, bank_name); 
 preparedStmt.setString(2, branch); 
 preparedStmt.setString(3, tranaction_date); 
 
 
 preparedStmt.setInt(4, Integer.parseInt(transaction_ID)); 
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
public String deleteTransaction(String transactionID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from bank_transaction_details where transactionID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(transactionID));
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

