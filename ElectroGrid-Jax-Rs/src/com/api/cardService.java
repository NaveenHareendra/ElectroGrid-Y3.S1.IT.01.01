package com.api; 
import com.dbService.card; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Card") 
public class cardService 
{ 
 card cardObj = new card(); 
 @GET
 @Path("/") 
 @Produces(MediaType.TEXT_HTML) 
 public String readCard() 
  { 
  return cardObj.readCard(); 
 }
 @POST
 @Path("/") 
 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String insertCard(@FormParam("cardNumber") String cardNumber, 
  @FormParam("nameOnCard") String nameOnCard, 
  @FormParam("expiryDate") String expiryDate, 
  @FormParam("cvc") String cvc
 
		 ) 
 { 
  String output = cardObj.insertCard(cardNumber, nameOnCard, expiryDate, cvc); 
 return output; 
 }
 @PUT
 @Path("/") 
 @Consumes(MediaType.APPLICATION_JSON) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String updateCard(String cardData) 
 { 
 //Convert the input string to a JSON object 
  JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject(); 
 //Read the values from the JSON object
  String cardID = cardObject.get("cardID").getAsString(); 
  String cardNumber = cardObject.get("cardNumber").getAsString(); 
  String nameOnCard = cardObject.get("nameOnCard").getAsString(); 
  String expiryDate = cardObject.get("expiryDate").getAsString(); 
  String cvc = cardObject.get("cvc").getAsString(); 

  String output = cardObj.updateCard(cardID, cardNumber, nameOnCard, expiryDate, cvc); 
 return output; 
 }
@DELETE
 @Path("/") 
 @Consumes(MediaType.APPLICATION_XML) 
 @Produces(MediaType.TEXT_PLAIN) 
 public String deleteCard(String cardData) 
 { 
 //Convert the input string to an XML document
  Document doc = Jsoup.parse(cardData, "", Parser.xmlParser()); 
  
 //Read the value from the element <itemID>
  String cardID = doc.select("cardID").text(); 
  String output = cardObj.deleteCard(cardID); 
 return output; 
 }

}
