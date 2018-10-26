package com.hotent.core.ldap.map;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ldap.core.ContextAssembler;
import org.springframework.ldap.core.DirContextOperations;
import com.hotent.core.ldap.model.LdapUser;



public class LdapUserAssembler implements ContextAssembler {
	@Override
	public Object mapFromContext(Object context) {
		DirContextOperations ctx = (DirContextOperations)context;
		LdapUser user=new LdapUser();
		user.setC(ctx.getStringAttribute(LdapUser.KEY_C));
		user.setCn(ctx.getStringAttribute(LdapUser.KEY_CN));
		user.setCo(ctx.getStringAttribute(LdapUser.KEY_CN));
		user.setCompany(ctx.getStringAttribute(LdapUser.KEY_COMPANY));
		user.setCountryCode(ctx.getStringAttribute(LdapUser.KEY_COUNTRYCODE));
		user.setDepartment(ctx.getStringAttribute(LdapUser.KEY_DEPARTMENT));
		user.setDescription(ctx.getStringAttribute(LdapUser.KEY_DESCRIPTION));
		user.setDisplayName(ctx.getStringAttribute(LdapUser.KEY_DISPLAYNAME));
		user.setDistinguishedName(ctx.getStringAttribute(LdapUser.KEY_DISTINGUISHEDNAME));
		user.setFacsimileTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_FACSIMILETELEPHONENUMBER));
		user.setHomePhone(ctx.getStringAttribute(LdapUser.KEY_HOMEPHONE));
		user.setInitials(ctx.getStringAttribute(LdapUser.KEY_INITIALS));		user.setName(ctx.getStringAttribute(LdapUser.KEY_NAME));
		user.setL(ctx.getStringAttribute(LdapUser.KEY_L));
		user.setMail(ctx.getStringAttribute(LdapUser.KEY_MAIL));
		user.setName(ctx.getStringAttribute(LdapUser.KEY_NAME));
		user.setGivenName(ctx.getStringAttribute(LdapUser.KEY_GIVENNAME));
		user.setPostalAddress(ctx.getStringAttribute(LdapUser.KEY_POSTALADDRESS));
		user.setPostalCode(ctx.getStringAttribute(LdapUser.KEY_POSTALCODE));
		user.setPostOfficeBox(ctx.getStringAttribute(LdapUser.KEY_POSTOFFICEBOX));
		user.setsAMAccountName(ctx.getStringAttribute(LdapUser.KEY_SAMACCOUNTNAME));
		user.setSt(ctx.getStringAttribute(LdapUser.KEY_ST));
		user.setStreetAddress(ctx.getStringAttribute(LdapUser.KEY_STREETADDRESS));
		user.setTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_TELEPHONENUMBER));
		user.setSn(ctx.getStringAttribute(LdapUser.KEY_SN));
		user.setTelephoneNumber(ctx.getStringAttribute(LdapUser.KEY_TELEPHONENUMBER));
		user.setTitle(ctx.getStringAttribute(LdapUser.KEY_TITLE));
		user.setUserPrincipalName(ctx.getStringAttribute(LdapUser.KEY_USERPRINCIPALNAME));
		user.setUserAccountControl(ctx.getStringAttribute(LdapUser.KEY_USERACCOUNTCONTROL));
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
		Date createDate=null;
		Date changeDate=null;
		try {
			changeDate = dateFormat.parse(ctx.getStringAttribute(LdapUser.KEY_WHENCHANGED));
			createDate = dateFormat.parse(ctx.getStringAttribute(LdapUser.KEY_WHENCREATED));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setWhenChanged(changeDate);
		user.setWhenCreated(createDate);
		user.setwWWHomePage(ctx.getStringAttribute(LdapUser.KEY_WWWHOMEPAGE));
		return user;
	}

	@Override
	public void mapToContext(Object obj, Object context) {
		LdapUser user=(LdapUser)obj;
		DirContextOperations ctx = (DirContextOperations)context;
		ctx.setAttributeValues("objectclass",new String[]{"top",LdapUser.OBJECTCLASS});
		ctx.setAttributeValue(LdapUser.KEY_CN, user.getCn());
		ctx.setAttributeValue(LdapUser.KEY_C,user.getC());
		ctx.setAttributeValue(LdapUser.KEY_NAME,user.getName());
		ctx.setAttributeValue(LdapUser.KEY_DESCRIPTION,user.getDescription());
		ctx.setAttributeValue(LdapUser.KEY_TELEPHONENUMBER,user.getTelephoneNumber());
		ctx.setAttributeValue(LdapUser.KEY_MAIL,user.getMail());
		ctx.setAttributeValue(LdapUser.KEY_HOMEPHONE,user.getHomePhone());
		ctx.setAttributeValue(LdapUser.KEY_SAMACCOUNTNAME, user.getsAMAccountName());
		ctx.setAttributeValue(LdapUser.KEY_USERACCOUNTCONTROL, user.getUserAccountControl());
		
	}
}
