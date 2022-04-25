package com.api; 
import com.dbService.card;
import com.dbService.payment;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payment") 
public class paymentService 
{ 
 payment paymentObj = new payment(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readPayment() 
  { 
  return paymentObj.readPayment(); 
 }
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertCard(@FormParam("customerName") String customerName, 
  @FormParam("email") String email, 
  @FormParam("contactNo") String contactNo, 
  @FormParam("amount") String amount,
  @FormParam("paymentDate") String paymentDate
 
		 ) 
 { 
  String output = paymentObj.insertPayment(customerName, email, contactNo, amount, paymentDate); 
 return output; 
 }
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updatePayment(String paymentData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
 //Read the values from the JSON object
  String paymentID = paymentObject.get("paymentID").getAsString(); 
  String customerName = paymentObject.get("customerName").getAsString(); 
  String email = paymentObject.get("email").getAsString(); 
  String contactNo = paymentObject.get("contactNo").getAsString(); 
  String amount = paymentObject.get("amount").getAsString();
  String paymentDate = paymentObject.get("paymentDate").getAsString();

  String output = paymentObj.updatePayment(paymentID, customerName, email, contactNo, amount, paymentDate); 
 return output; 
 }
@DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteCard(String paymentData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String paymentID = doc.select("paymentID").text(); 
  String output = paymentObj.deletePayment(paymentID); 
 return output; 
 }

}
