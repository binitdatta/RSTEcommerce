package com.rollingstone.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
@Entity
@Table(name="CONTACT")
//@SequenceGenerator(sequenceName="SEQ_CONTACT",name="SEQ_CONTACT")
public class Contact {
	@Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CONTACT")
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CONTACTID")
	long contactId;
	
	@Column(name="PHONENUMBER")
	String phoneNumber;
	
	@Column(name="PHONETYPE")
	String phoneType;
	
	@Column(name="CONTACTTYPE")
	String contactType;
	
	@Column(name="EMAILID")
	String emailId;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMERID", nullable=false, insertable=true, updatable=true)
	Customer customer;

	/**
	 * @return the contactId
	 */
	public long getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public int getCustomer() {
		return this.customer.getCustomerId();
	}

//	public Customer getCustomer() {
//		return this.customer;
//	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	@XmlElement
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	@XmlElement
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getContactType() {
		return this.contactType;
	}

	@XmlElement
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getEmailId() {
		return this.emailId;
	}

	@XmlElement
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
