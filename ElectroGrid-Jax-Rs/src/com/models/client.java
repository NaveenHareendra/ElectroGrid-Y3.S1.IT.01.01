package com.models;
import java.util.Date;
//it20032524 Warnakulasuriya M.A.N.H
import com.dbService.complaintService;
public class client extends User {
	private int customerId;
	private int customerNic;
	
   //Dependency implementation
	public String addComplaint(int customerId, complaint c){
		complaintService complaint = new complaintService();
		this.customerId = customerId;
		String output = complaint.addComplaint(this.customerId, c.getDescription());
		return output;
	}
}
