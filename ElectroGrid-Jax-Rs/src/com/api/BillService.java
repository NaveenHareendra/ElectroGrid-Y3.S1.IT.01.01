package com.api; 
import com.dbService.Bill;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Bills") 
public class BillService {
	Bill billObj = new Bill(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBills() 
	 { 
		return  billObj.readBills();
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBill(@FormParam("accNum") int accNum, 
	 @FormParam("cusName") String cusName, 
	 @FormParam("cusNIC") String cusNIC, 
	 @FormParam("billAmount") String billAmount,
	 @FormParam("billDate") String billDate)
	 
	{ 
	 String output =  billObj.insertBill(accNum, cusName,cusNIC,billAmount,billDate); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBill(String billData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject(); 
	//Read the values from the JSON object
	 int billId = billObject.get("billId").getAsInt(); 
	 int accNum = billObject.get("accNum").getAsInt(); 
	 String cusName = billObject.get("cusName").getAsString(); 
	 String cusNIC = billObject.get("cusNIC").getAsString(); 
	 String billAmount = billObject.get("billAmount").getAsString(); 
	 String billDate = billObject.get("billDate").getAsString(); 
	 String output = billObj.updateBill(billId, accNum, cusName, cusNIC,billAmount,billDate); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBill(String billData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String billId = doc.select("billId").text(); 
	 String output = billObj.deleteBill(billId); 
	return output; 
	}

}
