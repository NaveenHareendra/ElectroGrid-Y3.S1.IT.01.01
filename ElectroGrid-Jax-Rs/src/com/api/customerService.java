package com.api; 
import com.dbService.customer; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Customer") 
public class customerService 
{ 
 customer customerObj = new customer(); 
 
 
 // reading customer data
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readCustomers() 
 { 
	return customerObj.readCustomers();
 } 


// inserting customer data
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertNewConnection(
 @FormParam("customerName") String customerName, 
 @FormParam("customerNIC") String customerNIC, 
 @FormParam("customerAddress") String customerAddress, 
 @FormParam("customerEmail") String customerEmail,
 @FormParam("customerContact") String customerContact
 ) 
{ 
 String output = customerObj.insertCustomers(customerName, customerNIC, customerAddress, customerEmail, customerContact); 
return output; 
}

//updating customer data
@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCustomers(String customerData) 
{ 
//Convert the input string to a JSON object 
 JsonObject connectionObject = new JsonParser().parse(customerData).getAsJsonObject(); 
//Read the values from the JSON object
 String customerID = connectionObject.get("customerID").getAsString(); 
 String customerName = connectionObject.get("customerName").getAsString(); 
 String customerNIC = connectionObject.get("customerNIC").getAsString(); 
 String customerAddress = connectionObject.get("customerAddress").getAsString(); 
 String customerEmail = connectionObject.get("customerEmail").getAsString(); 
 String customerContact = connectionObject.get("customerContact").getAsString(); 
 String output = customerObj.updateCustomers(customerID, customerName, customerNIC, customerAddress, customerEmail,customerContact); 
return output; 
}


//deleting customer data
@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCustomers(String customerData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
 
//Read the value from the element <customerID>
 String customerID = doc.select("customerID").text(); 
 String output = customerObj.deleteCustomers(customerID); 
return output; 
}


}
