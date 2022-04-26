package com.api; 
import com.dbService.complain; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.dbService.complaintService;
import com.models.client;
import com.models.complaint;


@Path("/Complain") 
public class complainService 
{ 

	complaintService readComplaint = new complaintService();
	
	private complain complainObj = new complain(); 
	
	//IT20032524 Warnakulasuriya M.A.N.H
	client addClientComplaint = new client();
	private complaint complainConstruct = new complaint();
	private complaintService complaintAdd = new complaintService();
    
    
 // inserting complain data
  //IT20032524 Warnakulasuriya M.A.N.H
  @Path("/AddComplaints") 
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
  @Produces(MediaType.TEXT_PLAIN) 
  @POST
  public String addComplaint(
   @FormParam("customerId") int customerId, 
   @FormParam("description") String description
   ) 
  { 
	  System.out.println("Value: "+customerId);
	  System.out.println("Value: "+description);
	  
	  
	   complainConstruct.setDescription( description);
  	   String output = addClientComplaint.addComplaint(customerId,  complainConstruct); 
   
  	   return output; 
  }

 // reading complain data
@POST
@Path("/readCustomerComplaints") 
@Produces(MediaType.TEXT_HTML) 
public String readComplains(
		@FormParam("customerId") int customerId) 
 { 
	return readComplaint.readCustomerComplaints(customerId);
 } 




}

