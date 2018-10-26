package com.hotent.core.ldap.map;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ldap.core.ContextAssembler;
import org.springframework.ldap.core.DirContextOperations;

import com.hotent.core.ldap.model.LdapOrganization;
import com.hotent.core.ldap.model.LdapUser;



public class LdapOrganizationAssembler implements ContextAssembler {
	@Override
	public Object mapFromContext(Object context) {
		DirContextOperations ctx = (DirContextOperations)context;
		LdapOrganization org=new LdapOrganization();
		org.setBusinessCategory(ctx.getStringAttribute(LdapOrganization.KEY_BUSINESSCATEGORY));
		org.setDescription(ctx.getStringAttribute(LdapOrganization.KEY_DESCRIPTION));
		org.setDistinguishedName(ctx.getStringAttribute(LdapOrganization.KEY_DISTINGUISHEDNAME));
		org.setFacsimileTelephoneNumber(ctx.getStringAttribute(LdapOrganization.KEY_FACSIMILETELEPHONENUMBER));
		org.setName(ctx.getStringAttribute(LdapOrganization.KEY_NAME));
		org.setO(ctx.getStringAttribute(LdapOrganization.KEY_O));
		org.setPostalAddress(ctx.getStringAttribute(LdapOrganization.KEY_POSTALADDRESS));
		org.setPostalCode(ctx.getStringAttribute(LdapOrganization.KEY_POSTALCODE));
		org.setRegisteredAddress(ctx.getStringAttribute(LdapOrganization.KEY_REGISTEREDADDRESS));
		org.setSt(ctx.getStringAttribute(LdapOrganization.KEY_ST));
		org.setStreet(ctx.getStringAttribute(LdapOrganization.KEY_STREET));
		org.setTelephoneNumber(ctx.getStringAttribute(LdapOrganization.KEY_TELEPHONENUMBER));
		org.setTelexNumber(ctx.getStringAttribute(LdapOrganization.KEY_TELEXNUMBER));
		org.setuSNChanged(ctx.getStringAttribute(LdapOrganization.KEY_USNCHANGED));
		org.setuSNCreated(ctx.getStringAttribute(LdapOrganization.KEY_USNCREATED));
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
		Date createDate=null;
		Date changeDate=null;
		try {
			changeDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganization.KEY_WHENCHANGED));
			createDate = dateFormat.parse(ctx.getStringAttribute(LdapOrganization.KEY_WHENCREATED));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		org.setWhenChanged(changeDate);
		org.setWhenCreated(createDate);
		return org;
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
