package com.hotent.core.ldap.map;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.ldap.core.ContextAssembler;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

import com.hotent.core.ldap.model.LdapGroup;
import com.hotent.core.ldap.model.LdapOrganizationalUnit;
import com.hotent.core.ldap.model.LdapUser;



public class LdapGroupAssemabller implements ContextAssembler {
	@Override
	public Object mapFromContext(Object context) {
		DirContextOperations ctx = (DirContextOperations)context;
		LdapGroup group=new LdapGroup();
		group.setAdminCount(ctx.getStringAttribute(LdapGroup.KEY_ADMINCOUNT));
		group.setCn(ctx.getStringAttribute(LdapGroup.KEY_CN));
		group.setDescription(ctx.getStringAttribute(LdapGroup.KEY_DESCRIPTIONT));
		group.setDistinguishedName(ctx.getStringAttribute(LdapGroup.KEY_DISTINGUISHEDNAME));
		group.setInfo(ctx.getStringAttribute(LdapGroup.KEY_INFO));
		group.setMail(ctx.getStringAttribute(LdapGroup.KEY_MAIL));
		group.setMembers(ctx.getStringAttributes(LdapGroup.KEY_MEMBER));
		group.setName(ctx.getStringAttribute(LdapGroup.KEY_NAME));
		group.setsAMAccountName(ctx.getStringAttribute(LdapGroup.KEY_SAMACCOUNTNAME));
		group.setsAMAccountType(ctx.getStringAttribute(LdapGroup.KEY_SAMACCOUNTTYPE));
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss'.'S'Z'");
		Date createDate=null;
		Date changeDate=null;
		try {
			changeDate = dateFormat.parse(ctx.getStringAttribute(LdapGroup.KEY_WHENCHANGED));
			createDate = dateFormat.parse(ctx.getStringAttribute(LdapGroup.KEY_WHENCREATED));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		group.setWhenChanged(changeDate);
		group.setWhenCreated(createDate);
		return group;
	}

	@Override
	public void mapToContext(Object obj, Object ctx) {

	}
}
