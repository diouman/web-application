package model;

import javax.persistence.*;
import javax.persistence.Id;



@Entity
@Table(name="customer")
public class Customer {
	@Column(name="cId")
	private int cId;
	@Column(name="cName")
	private String cName;
	@Column(name="cAddress")
	private String cAddress;
	@Column(name="cGender")
	private String cGender;
	@Column(name="cPhone")
	private int cPhone;
	
	public Customer(int cId, String cName, String cAddress, String gender, int cPhone) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cAddress = cAddress;
		this.cGender = gender;
		this.cPhone = cPhone;
	}
	
	public Customer (String cName, String cAddress, String gender, int cPhone) {
		super();
		
		this.cName = cName;
		this.cAddress = cAddress;
		this.cGender = gender;
		this.cPhone = cPhone;
	}
	public Customer() {
		
	}

	@Id
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	
	public int getcPhone() {
		return cPhone;
	}
	public void setcPhone(int cPhone) {
		this.cPhone = cPhone;
	}

	public String getcGender() {
		return cGender;
	}

	public void setcGender(String cGender) {
		this.cGender = cGender;
	}
	
	
	
	
	

}
