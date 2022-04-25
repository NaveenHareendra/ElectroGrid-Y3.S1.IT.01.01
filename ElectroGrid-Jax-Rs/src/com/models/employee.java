package com.models;
//it20032524 Warnakulasuriya M.A.N.H
public class employee extends User {
	
	public void setEmpNic(String empNic){
		this.empNic=empNic;
	}
	
	public void setEmpName(String empName){
		this.empName=empName;
	}
    
	public void setEmpAddress(String empAddress){
    	this.empAddress=empAddress;
    }
    
	public void setEmpEmail(String empEmail){
    	this.empEmail=empEmail;
    }
    
	public void setEmpPassword(String empPassword){
    	this.empPassword=empPassword;
    }
    
    
	public String getEmpNic(){
		return empNic;
	}
	public String getEmpName(){
		return empName;
	}
    
	public String getEmpAddress( ){
    	return empAddress;
    }
    
	public String getEmpEmail(){
    	return empEmail;
    }
    
	public String getEmpPassword(){
    	return empPassword;
    }
}
