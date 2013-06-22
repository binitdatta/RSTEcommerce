package com.rollingstone.customer.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@XmlRootElement(name = "customer")
@Entity
@Table(name="CUSTOMER")
//@SequenceGenerator(sequenceName="SEQ_CUSTOMER",name="SEQ_CUSTOMER")
public class Customer {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CUSTOMER")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CUSTOMERID")
	int customerId;

	@Column(name="CUSTOMERNAME")
	String customerName;
	
	@Column(name="MEMBERSINCE")
	Date memberSince;
	
	@Column(name="BALANCE")
	float balance;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@Cascade(value = { CascadeType.ALL })
	@JoinColumn(name="ADDRESSID", nullable=true, insertable=true, updatable=true)
	Address customerAddress;
	
	@OneToOne(mappedBy="customer", fetch=FetchType.EAGER)
	@Cascade(value = { CascadeType.ALL })
	@Fetch(FetchMode.JOIN)
	CreditCard defaultCard;
	
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	@Cascade(value = { CascadeType.ALL })
	@Fetch(FetchMode.SUBSELECT)
	List<Contact> contacts;

	public List<Contact> getContacts() {
		return this.contacts;
	}

	@XmlElement
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	@XmlElement
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	@XmlElement
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getMemberSince() {
		return this.memberSince;
	}

	@XmlElement
	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public Address getCustomerAddress() {
		return this.customerAddress;
	}

	@XmlElement
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public float getBalance() {
		return this.balance;
	}

	@XmlElement
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public CreditCard getDefaultCard() {
		return this.defaultCard;
	}

	@XmlElement
	public void setDefaultCard(CreditCard defaultCard) {
		this.defaultCard = defaultCard;
	}
}
