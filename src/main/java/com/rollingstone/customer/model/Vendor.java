package com.rollingstone.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@XmlRootElement(name = "vendor")
@Entity
@Table(name="VENDORS")
public class Vendor {

	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRODUCT")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VENDORID")
	int vendorId;
	
	@Column(name="VENDOR_SHORT_NAME")
	String vendorShortName;
	
	@Column(name="VENDOR_NAME")
	String vendorName;
	
	@Column(name="REGISTRATION_DATE")
	Date regDate;

	@Column(name="VENDOR_STATUS")
	Date vendorStatus;
	
	@Column(name="PAYABLE_AMT")
	float payableAmt;
	
	
	//TODO find uniary association in JPA
		@OneToOne(mappedBy="vendor", fetch=FetchType.EAGER)
		@Cascade(value = { CascadeType.ALL })
		@Fetch(FetchMode.JOIN)
		Address vendorAddress;
		
		@OneToOne(mappedBy="vendor", fetch=FetchType.EAGER)
		@Cascade(value = { CascadeType.ALL })
		@Fetch(FetchMode.JOIN)
		Contact contact;
		
		
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorShortName() {
		return vendorShortName;
	}

	public void setVendorShortName(String vendorShortName) {
		this.vendorShortName = vendorShortName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getVendorStatus() {
		return vendorStatus;
	}

	public void setVendorStatus(Date vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	public float getPayableAmt() {
		return payableAmt;
	}

	public void setPayableAmt(float payableAmt) {
		this.payableAmt = payableAmt;
	}

	public Address getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(Address vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
	

	
}