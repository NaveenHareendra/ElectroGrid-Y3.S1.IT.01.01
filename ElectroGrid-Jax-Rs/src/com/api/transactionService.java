package com.api; 
import com.dbService.bank_transaction; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Bank") 
public class transactionService 
{ 
 bank_transaction transObj = new bank_transaction(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readTransaction() 
  { 
  return transObj.readTransaction(); 
 }
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertTransaction(@FormParam("bankName") String bankName, 
  @FormParam("branch") String branch, 
  @FormParam("transactionDate") String transactionDate
 

		 ) 
 { 
  String output = transObj.insertTransaction(bankName, branch, transactionDate); 
 return output; 
 }
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateTransaction(String bankData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject transactionObject = new JsonParser().parse(bankData).getAsJsonObject(); 
 //Read the values from the JSON object
  String transactionID = transactionObject.get("transactionID").getAsString(); 
  String bankName = transactionObject.get("bankName").getAsString(); 
  String branch = transactionObject.get("branch").getAsString(); 
  String transactionDate = transactionObject.get("transactionDate").getAsString(); 
 
  String output = transObj.updateTransaction(transactionID, bankName, branch, transactionDate); 
 return output; 
 }
@DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteCard(String bankData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(bankData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String transactionID = doc.select("transactionID").text(); 
  String output = transObj.deleteTransaction(transactionID); 
 return output; 
 }

}
