package com.models;
//it20032524 Warnakulasuriya M.A.N.H
public class employee extends User {
	private String empNic;
	
	public void setEmpNic(String empNic){
		this.empNic=empNic;
	}
	
	public void setEmpName(String empName){
		this.Name=empName;
	}
    
	public void setEmpAddress(String empAddress){
    	this.Address=empAddress;
    }
    
	public void setEmpEmail(String empEmail){
    	this.Email=empEmail;
    }
    
	public void setEmpPassword(String empPassword){
    	this.Password=empPassword;
    }
    
    
	public String getEmpNic(){
		return empNic;
	}
	public String getEmpName(){
		return Name;
	}
    
	public String getEmpAddress( ){
    	return Address;
    }
    
	public String getEmpEmail(){
    	return Email;
    }
    
	public String getEmpPassword(){
    	return Password;
    }
}
