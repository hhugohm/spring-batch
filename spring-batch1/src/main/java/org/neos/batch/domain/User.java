package org.neos.batch.domain;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUser;
	private String name;
	private String lastName;
	private Integer old;
	private String email;
	private Date dateSave;
	private Date bornDate;
	
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
	public Date getDateSave() {
		return dateSave;
	}
	public void setDateSave(Date dateSave) {
		this.dateSave = dateSave;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	@Override
	public String toString(){
		StringBuilder cadena= new StringBuilder();
		cadena.append(this.idUser).append(" ");
		cadena.append(this.name).append(" ");
		cadena.append(this.lastName).append(" ");
		cadena.append(this.old).append(" ");
		cadena.append(this.email).append(" ");
		cadena.append(this.dateSave).append(" ");
		cadena.append(this.bornDate).append(" ");
		
		return cadena.toString();
	}
	

}
