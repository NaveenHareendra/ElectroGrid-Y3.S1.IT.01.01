package com.models;
//it20032524 Warnakulasuriya M.A.N.H
public class complaint {

	private int complaintNo;
	private boolean complaintStatus;
	private String description;
	
	public void setComplaintNo(int complaintNo){
		this.complaintNo=complaintNo;
	}
	
	public void setComplaintStatus(boolean complaintStatus){
		this.complaintStatus=complaintStatus;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public int getComplaintNo(){
		return complaintNo;
	}
	
	public boolean getComplaintStatus(){
		return complaintStatus;
	}
	
	public String getDescription(){
		return description;
	}
}
