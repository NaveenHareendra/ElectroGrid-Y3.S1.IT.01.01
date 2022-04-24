package model; 
import java.sql.*; 

public class Bill {
	
	//Db connection
	public Connection connect() { 
		Connection con = null; 
	 
		try{ 
			
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/billManagement", "root", ""); 
			//For testing
			System.out.print("Successfully connected"); 
			
		}catch(Exception e){ 
			
			e.printStackTrace(); 
		} 
	 
		return con; 
	}
	
	//Insert Data
			public String insertBill(int accnum , String cusname , String cusnic , String billamount ,String billdate) {
				
				String output = "";
				
				
				
				try {
					
					Connection con = connect();
					if(con == null) {
						return "error while connecting database";
					}
					
					String query = "insert into billinfo(`billId` , `accNum` ,`cusName` , `cusNIC` , `billAmount` , `billDate`)"
							        + "value (?,?,?,?,?,?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setInt(2,accnum); 
					 preparedStmt.setString(3,cusname); 
					 preparedStmt.setString(4,cusnic);
					 preparedStmt.setString(5,billamount);
	                 preparedStmt.setString(6,billdate);
					 
					//execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Inserted successfully";
					 
				}catch(Exception e) {
					
					 output = "Error while inserting"; 
					 System.err.println(e.getMessage());
				}
				
				return output;
			}
	
			//read Data
			public String readBills() {
				String output  = "";
				
				try {
					Connection con = connect();
					
					if (con == null) {
						return "error while connecting database for reading";
					}
					
					// Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Account Number</th>" 
					 +"<th>Customer Name</th><th>Customer NIC</th>"
					 + "<th>Bill Amount</th>"  + "<th>Bill Date</th>" 
					 + "<th>Update</th><th>Remove</th></tr>";
					 
					 String query = "select * from billinfo"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query);
					 
					 while (rs.next()) 
					 { 
						 String billId = Integer.toString(rs.getInt("billId")); 
						 String accNum = Integer.toString(rs.getInt("accNum")); 
						 String cusName = rs.getString("cusName"); 
						 String cusNIC =  rs.getString("cusNIC"); 
						 String billAmount = rs.getString("billAmount"); 
	                     String billDate = rs.getString("billDate");
	                                      
						 // Add a row into the html table
						 
						 output += "<tr><td>" + accNum  + "</td>"; 
						 output += "<td>" +  cusName  + "</td>"; 
						 output += "<td>" + cusNIC + "</td>";
						 output += "<td>" + billAmount + "</td>"; 
	                                         output += "<td>" + billDate + "</td>";
	                                        
						 // buttons
						 output += "<td><input name='btnUpdate' " 
								 + " type='button' value='Update'></td>"
								 + "<td><form method='post' action='bills.jsp'>"
								 + "<input name='btnRemove' " 
								 + " type='submit' value='Remove'>"
								 + "<input name='billId' type='hidden' " 
								 + " value='" + billId + "'>" + "</form></td></tr>"; 
					 } 
					 
					 con.close(); 
					 // Complete the html table
					 output += "</table>"; 
					 
					 
				}catch(Exception e) {
					
					output = "error while reading items";
					System.err.println(e.getMessage());
					
				}
				
				return output;
			}
			
			//update
			public String updateBill(int billId , int accNum , String cusName , String cusNIC ,String billAmount, String billDate ) {
				
				String output = ""; 
				
				try {
					
					Connection con = connect(); 
					
					if (con == null){
						
						return "Error while connecting to the database for updating."; 
					} 
					
					 // create a prepared statement
					 String query = "UPDATE billinfo SET accNum =?,cusName=?,cusNIC=?,billAmount=?,billDate=? WHERE billId=?";
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 //preparedStmt.setString(1, code); 
					 //preparedStmt.setString(2, name); 
					 //preparedStmt.setDouble(3, Double.parseDouble(price)); 
					 //preparedStmt.setString(4, desc); 
					 //preparedStmt.setInt(5, Integer.parseInt(ID)); 

	                         
				         preparedStmt.setInt(1, accNum); 
				         preparedStmt.setString(2, cusName); 
				         preparedStmt.setString(3, cusNIC);
				         preparedStmt.setString(4, billAmount);
				         preparedStmt.setString(5, billDate);
	                     preparedStmt.setInt(6, billId); 
					 
					 // execute the statement
					 preparedStmt.execute(); 
					 
					 con.close(); 
					 
					 output = "Updated successfully";
					
				}catch(Exception e) {
					
					output = "Error while updating the item."; 
					System.err.println(e.getMessage()); 
					
				}
				
				return output;
			}
			
			public String deleteBill(String billId) { 
				String output = ""; 
				try{ 
					Connection con = connect(); 
					if (con == null) { 
						return "Error while connecting to the database for deleting."; 
					} 
					
					// create a prepared statement
					String query = "delete from billinfo where billId=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(billId)); 
			 
					// execute the statement
					preparedStmt.execute(); 
					
					con.close(); 
					
					output = "Deleted successfully"; 
				}catch (Exception e) { 
					
					output = "Error while deleting the bill."; 
					System.err.println(e.getMessage()); 
				} 
				
				return output; 
				
			}

}
