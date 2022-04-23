package com.api;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import com.models.employee;
import com.dbService.databaseConnectionService;
import java.sql.SQLException;
import java.util.ArrayList;
@Path("/RestService")

public class RestService {
	
	employee regEmployee = new employee();
	employee logEmployee = new employee();
	
	databaseConnectionService dbConnect = new databaseConnectionService();
	
	@Path("/{empNic}/{empName}/{empAddress}/{empEmail}/{empPassword}")
	@POST
	public void Register(@PathParam("empNic") String empNic,
			@PathParam("empName") String empName,
			@PathParam("empAddress") String empAddress,
			@PathParam("empEmail") String empEmail,
			@PathParam("empPassword") String empPassword){

		regEmployee.setEmpNic(empNic);
		regEmployee.setEmpEmail(empEmail);
		regEmployee.setEmpAddress(empAddress);
		regEmployee.setEmpName(empName);
		regEmployee.setEmpPassword(empPassword);

//		System.out.println("Register()");						
		dbConnect.registerEmployeeDatabase(
				regEmployee.getEmpNic(),
				regEmployee.getEmpAddress(),
				regEmployee.getEmpEmail(),
				regEmployee.getEmpName(),
				regEmployee.getEmpPassword());
		
	}
	

	@GET
	@Path("/Login/{employeeEmail}/{employeePassword}")
	public void employeeLogin(@PathParam("employeeEmail") String empEmail, 
			@PathParam("employeePassword") String empPassword){
		
		logEmployee.setEmpEmail(empEmail);
		logEmployee.setEmpPassword(empPassword);
		
		ArrayList<String> profileInformation = new ArrayList<String>();
		
		if((empEmail!=null) && (empPassword!=null)){

			System.out.println("called!");	
			
			try{
				profileInformation.addAll(dbConnect.employeeLogin(logEmployee.getEmpEmail(), logEmployee.getEmpPassword()));
				System.out.println("User Logged Successfully!");	
				System.out.println("Employee Name:"+profileInformation.get(0)+"\nEmployee Address: "+profileInformation.get(1)+"\nEmployee Email: "+profileInformation.get(2));

			}catch(NullPointerException ArrNull){
				System.out.println("Invalid Login Credentials, Please try again...");
			}
		}else {
		
			System.out.println("Empty data cannot be inserted...");
		
		}
	}
	
	
	
}
