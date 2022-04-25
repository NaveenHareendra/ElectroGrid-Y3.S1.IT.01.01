package com.api;
//it20032524 Warnakulasuriya M.A.N.H
import javax.ws.rs.Path;

import com.models.client;
import com.models.complaint;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import com.models.employee;
import com.dbService.databaseConnectionService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import com.dbService.*;
@Path("/Employee")

public class Employee {
	complaint updateComplaint = new complaint();
	employee regEmployee = new employee();
	employee logEmployee = new employee();
	
	
	databaseConnectionService dbConnect = new databaseConnectionService();
	complaintService complaintDbService = new complaintService();
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@POST
	public void Register(@FormParam("empNic") String empNic,
			@FormParam("empName") String empName,
			@FormParam("empAddress") String empAddress,
			@FormParam("empEmail") String empEmail,
			@FormParam("empPassword") String empPassword){

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
	@Produces(MediaType.TEXT_HTML) 
	public String employeeLogin(@PathParam("employeeEmail") String empEmail, 
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
		return "Employee Name: "+profileInformation.get(0)+"\nEmployee Email: "+profileInformation.get(1)+"\nEmployee Address: "+profileInformation.get(2);
	
	}
	
	

	//Complaint Status update of the customer, by Employee...
	@Path("/customerComplaintUpdate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_HTML) 
	@POST
	public String customerComplaintUpdate(	
			@FormParam("complaintStatus") boolean cmplStat,
			@FormParam("ComplaintNo") int cmplNo,
			@FormParam("customerId") int cusId){
		
		System.out.println("Update in Progress...");
		
		updateComplaint.setComplaintStatus(cmplStat);
		updateComplaint.setComplaintNo(cmplNo);
		boolean complaintUpdated=complaintDbService.updateComplaint(cusId,updateComplaint.getComplaintStatus(), updateComplaint.getComplaintNo());
	
		if(complaintUpdated==true){
			System.out.println("Status updated...");
			return "Updated!";
		}else{
			System.out.println("Something is wrong...");
			return "Not Updated!";
		}
		

	}
	

	@Path("/DeleteCustomerComplaint")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_HTML) 
	@DELETE
	public String complaintDelete(@FormParam("complaintId") int complaintId, 
			@FormParam("CustomerId") int  CustomerId){
		
		System.out.println("This is called ..."+CustomerId);

		boolean isDeleted=complaintDbService.deleteComplaint(complaintId, CustomerId);
		
		if(isDeleted==true)
		    return "Deleted !";
		else
			return "Not deleted";
		
		
	}
	
	@Path("/readComplaints")
	@Produces(MediaType.TEXT_HTML)
	@GET
	public String readComplaints(){
		String output=complaintDbService.readComplaints();
		
		return output;
	}
		
		
	
}
	
	

