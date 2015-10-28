package org.neos.batch.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.DateTime;


@XmlRootElement(name = "userMapper")
public class UserMapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idUser;
	private String name;
	private String lastName;
	private Integer old;
	private String email;
	private DateTime   dateBorn;
	
	public UserMapper(){
		this.idUser="1";
		this.name="hugo";
		this.lastName="hidalgo";
		this.old=38;
		this.email="hhugohm@miemail.com";
		this.dateBorn=new DateTime();
		
		
	}
	
	@XmlElement(name = "idUser")
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name = "lastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement(name = "old")
	public Integer getOld() {
		return old;
	}
	public void setOld(Integer old) {
		this.old = old;
	}
	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
    @XmlElement(name = "dateBorn")
	@XmlJavaTypeAdapter(type = DateTime .class, value = org.neos.batch.adapter.LocalDateAdapter.class)
	public DateTime  getDateBorn() {
		return dateBorn;
	}
	public void setDateBorn(DateTime  dateBorn) {
		this.dateBorn = dateBorn;
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
