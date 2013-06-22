package com.rollingstone.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@Entity
@Table(name="ADDRESS")
//@SequenceGenerator(sequenceName="SEQ_ADDRESS",name="SEQ_ADDRESS")
public class Address {

	@Id
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ADDRESS")
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ADDRESSID")
	long addressId;
	
	@Column(name="HOUSENUMBER")
	int houseNumber;
	
	@Column(name="STREET")
	String street;
	
	@Column(name="CITY")
	String city;
	
	@Column(name="STATE")
	String state;
	
	@Column(name="COUNTRY")
	String country;

	@Column(name="ADDRESSTYPEID")
	int addressTypeId;
	
	public int getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(int addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	@XmlElement
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	@XmlElement
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString(){
		return this.getHouseNumber() + " " +this.getStreet() + " "+ this.getCity() + " " + this.getState() + " " + this.getCountry();		
	}
}
