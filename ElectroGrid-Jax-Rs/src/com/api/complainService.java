package com.api; 
import com.dbService.complain; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 



@Path("/Complain") 
public class complainService 
{ 
 complain complainObj = new complain(); 
 
 
 // reading complain data
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readComplains() 
 { 
	return complainObj.readComplains();
 } 


// inserting complain data
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertComplains(
 @FormParam("customerName") String customerName, 
 @FormParam("customerNIC") String customerNIC, 
 @FormParam("complain") String complain
 ) 
{ 
 String output = complainObj.insertComplains(customerName, customerNIC, complain); 
return output; 
}



}

