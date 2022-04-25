package com.dbService; 
import java.sql.*; 
public class card 
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
public String insertCard(String card_number, String card_name, String expiry_date, String cus_cvc) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into card_details  (`cardID`,`cardNumber`,`nameOnCard`,`expiryDate`,`cvc`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, card_number); 
 preparedStmt.setString(3, card_name); 
 preparedStmt.setString(4, expiry_date); 
 preparedStmt.setString(5, cus_cvc); 
 
 
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
public String readCard() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Card Number</th><th>nameOnCard</th>" +
 "<th>expiryDate</th>" + 
 "<th>cvc</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from card_details"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String cardID = Integer.toString(rs.getInt("cardID")); 
 String cardNumber = rs.getString("cardNumber"); 
 String nameOnCard = rs.getString("nameOnCard"); 
 String expiryDate = rs.getString("expiryDate");
 String cvc = rs.getString("cvc");

 // Add into the html table
 output += "<tr><td>" + cardNumber + "</td>"; 
 output += "<td>" + nameOnCard + "</td>"; 
 output += "<td>" + expiryDate + "</td>"; 
 output += "<td>" + cvc + "</td>"; 

 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" +cardID 
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
public String updateCard(String card_ID,String card_number, String card_name, String expiry_date, String cus_cvc) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE card_details SET cardNumber=?,nameOnCard=?,expiryDate=?,cvc=? WHERE cardID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, card_number); 
 preparedStmt.setString(2, card_name); 
 preparedStmt.setString(3, expiry_date); 
 preparedStmt.setString(4,cus_cvc ); 
 
 preparedStmt.setInt(5, Integer.parseInt(card_ID)); 
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
public String deleteCard(String cardID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from card_details where cardID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(cardID));
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
