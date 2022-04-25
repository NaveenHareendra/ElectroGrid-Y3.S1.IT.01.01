package com.dbService;
//it20032524 Warnakulasuriya M.A.N.H
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseConnectionService {
	private Statement stmt;
	private Connection con;
	
	public void databaseConnection(){
		try{
			System.out.println("Database Connection() Running...");			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con =DriverManager
					.getConnection("jdbc:mysql://localhost:3306/electrogridpower", "root", "root");
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
	//Register for Employees
	public void registerEmployeeDatabase(String empNic, String empAddress, String empEmail, String empName, String empPassword){
		
		try{

			databaseConnection();
			
			System.out.println(stmt.executeUpdate("INSERT INTO employeeInformation VALUES('"+empNic+ "','" + empName+ "','" + empAddress+ "','" +empEmail + "' ,'" + empPassword+ "')"));

			
      		con.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	//Login for employee, Database authentication
	public ArrayList<String> employeeLogin(String empEmail, String empPassword){
		ResultSet result = null;
		
		Boolean someBool=false;
		
		ArrayList<String> profileInformation = new ArrayList<String> ();

		try{
			databaseConnection();
			
			result=stmt.executeQuery("SELECT empName, empEmail, empAddress FROM employeeInformation WHERE empEmail='"+empEmail+"' AND empPassword='"+empPassword+"'");
		
			if(result.next()){
				profileInformation.add(result.getString(1));
				profileInformation.add(result.getString(2));
				profileInformation.add(result.getString(3));
				someBool=true;
				
			}
			
			con.close();
			
		}catch(SQLException SQLException){
			System.out.println("Sql Error: "+SQLException);
		}

		if(someBool!=false){
							
			return profileInformation;
		}else{

			return null;
		}
		
	}
	

}
