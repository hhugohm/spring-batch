package org.neos.batch.domain;

import java.io.Serializable;

import javax.validation.constraints.Size;
import org.joda.time.DateTime;
import org.neos.batch.validation.ExtendedEmailValidator;



public class UserMapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(min = 1, max = 5, message = "Excede la longituda permitida")  
	private String idUser;
	//@AcceptedValues(acceptValues = { "PREPAREID", "postpaid_mail","postpaid_email" }, message = "Invalid bill type")  
	private String name;
	private String lastName;
	private Integer old;
	@ExtendedEmailValidator
	private String email;
	private DateTime   dateBorn;
	private int record;
	
	public UserMapper(){
		this.idUser="1";
		this.name="hugo";
		this.lastName="hidalgo";
		this.old=38;
		this.email="hhugohm@miemail.com";
		this.dateBorn=new DateTime();
	}
	public UserMapper(String idUser){
		this.idUser=idUser;
		this.name="";
		this.lastName="";
		this.old=0;
		this.email="";
		this.dateBorn=new DateTime();
		
		
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getOld() {
		return old;
	}
	public void setOld(Integer old) {
		this.old = old;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DateTime  getDateBorn() {
		return dateBorn;
	}
	public void setDateBorn(DateTime  dateBorn) {
		this.dateBorn = dateBorn;
	}
	
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	@Override
	public String toString(){
		StringBuilder cadena= new StringBuilder();
		cadena.append(this.idUser).append(" ");
		cadena.append(this.name).append(" ");
		cadena.append(this.lastName).append(" ");
		cadena.append(this.old).append(" ");
		cadena.append(this.email).append(" ");
		cadena.append(this.dateBorn);
		
		return cadena.toString();
	}
	
}
