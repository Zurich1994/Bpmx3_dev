package com.hotent.core.ldap.model;

import java.util.Date;

public class LdapOrganizationalUnit {
	public static String KEY_OU="ou";
	public static String KEY_BUSINESSCATEGORY="businessCategory";
	public static String KEY_DESCRIPTION="description";
	public static String KEY_DISTINGUISHEDNAME="distinguishedName";
	public static String KEY_FACSIMILETELEPHONENUMBER="facsimileTelephoneNumber";
	public static String KEY_NAME="name";
	public static String KEY_POSTALADDRESS="postalAddress";
	public static String KEY_POSTALCODE="postalCode";
	public static String KEY_REGISTEREDADDRESS="registeredAddress";
	public static String KEY_ST="st";
	public static String KEY_STREET="street";
	public static String KEY_TELEPHONENUMBER="telephoneNumber";
	public static String KEY_TELEXNUMBER="telexNumber";
	public static String KEY_USNCHANGED="uSNChanged";
	public static String KEY_USNCREATED="uSNCreated";
	public static String KEY_WHENCHANGED="whenChanged";
	public static String KEY_WHENCREATED="whenCreated";
	public static String OBJECTCATEGORY="organizationalUnit";
	public static String OBJECTCLASS="organizationalUnit";
	
	private String ou;
	private String businessCategory;
	private String description;
	private String distinguishedName;
	private String facsimileTelephoneNumber;
	private String name;
	private String postalAddress;
	private String postalCode;
	private String registeredAddress;
	private String st;
	private String street;
	private String telephoneNumber;
	private String telexNumber;
	private String uSNChanged;
	private String uSNCreated;
	private Date whenChanged;
	private Date whenCreated;
	
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDistinguishedName() {
		return distinguishedName;
	}
	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}
	public String getFacsimileTelephoneNumber() {
		return facsimileTelephoneNumber;
	}
	public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
		this.facsimileTelephoneNumber = facsimileTelephoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getTelexNumber() {
		return telexNumber;
	}
	public void setTelexNumber(String telexNumber) {
		this.telexNumber = telexNumber;
	}
	public String getuSNChanged() {
		return uSNChanged;
	}
	public void setuSNChanged(String uSNChanged) {
		this.uSNChanged = uSNChanged;
	}
	public String getuSNCreated() {
		return uSNCreated;
	}
	public void setuSNCreated(String uSNCreated) {
		this.uSNCreated = uSNCreated;
	}
	public Date getWhenChanged() {
		return whenChanged;
	}
	public void setWhenChanged(Date whenChanged) {
		this.whenChanged = whenChanged;
	}
	public Date getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
	}
	@Override
	public String toString() {
		return "OrganizationalUnit [ou=" + ou + ", businessCategory="
				+ businessCategory + ", description=" + description
				+ ", distinguishedName=" + distinguishedName
				+ ", facsimileTelephoneNumber=" + facsimileTelephoneNumber
				+ ", name=" + name + ", postalAddress=" + postalAddress
				+ ", postalCode=" + postalCode + ", registeredAddress="
				+ registeredAddress + ", st=" + st + ", street=" + street
				+ ", telephoneNumber=" + telephoneNumber + ", telexNumber="
				+ telexNumber + ", uSNChanged=" + uSNChanged + ", uSNCreated="
				+ uSNCreated + ", whenChanged=" + whenChanged
				+ ", whenCreated=" + whenCreated + "]";
	}

	
	
}
