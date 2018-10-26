package com.hotent.core.model;

/*
 *  广州宏天软件有限公司 JOffice协同办公管理系统   -- http://www.jee-soft.cn
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 短消息对象。
 */
public class SmsMobile implements Serializable {

    /**
     * 短信已发送
     */
    public static final Short STATUS_SENDED = (short) 1;
    /**
     * 短信未发送
     */
    public static final Short STATUS_NOT_SENDED = (short) 0;

    protected Long smsId;
    protected java.util.Date sendTime;
    protected String recipients;
    protected String phoneNumber;
    protected Long userId;
    protected String userName;
    protected String smsContent;
    protected Short status;
    protected String fromUser;

    /**
     * Default Empty Constructor for class SmsMobile
     */
    public SmsMobile() {
	super();
    }

    /**
     * Default Key Fields Constructor for class SmsMobile
     */
    public SmsMobile(Long in_smsId) {
	this.setSmsId(in_smsId);
    }

    /**
     * * @return Long
     * 
     * @hibernate.id column="smsId" type="java.lang.Long"
     *               generator-class="native"
     */
    public Long getSmsId() {
	return this.smsId;
    }

    /**
     * Set the smsId
     */
    public void setSmsId(Long aValue) {
	this.smsId = aValue;
    }

    /**
     * * @return java.util.Date
     * 
     * @hibernate.property column="sendTime" type="java.util.Date" length="10"
     *                     not-null="true" unique="false"
     */
    public java.util.Date getSendTime() {
	return this.sendTime;
    }

    /**
     * Set the sendTime
     * 
     * @spring.validator type="required"
     */
    public void setSendTime(java.util.Date aValue) {
	this.sendTime = aValue;
    }

    /**
     * * @return String
     * 
     * @hibernate.property column="recipients" type="java.lang.String"
     *                     length="128" not-null="false" unique="false"
     */
    public String getRecipients() {
	return this.recipients;
    }

    /**
     * Set the recipients
     */
    public void setRecipients(String aValue) {
	this.recipients = aValue;
    }

    /**
     * * @return String
     * 
     * @hibernate.property column="phoneNumber" type="java.lang.String"
     *                     length="128" not-null="true" unique="false"
     */
    public String getPhoneNumber() {
	return this.phoneNumber;
    }

    /**
     * Set the phoneNumber
     * 
     * @spring.validator type="required"
     */
    public void setPhoneNumber(String aValue) {
	this.phoneNumber = aValue;
    }

    /**
     * * @return Long
     * 
     * @hibernate.property column="userId" type="java.lang.Long" length="19"
     *                     not-null="false" unique="false"
     */
    public Long getUserId() {
	return this.userId;
    }

    /**
     * Set the userId
     */
    public void setUserId(Long aValue) {
	this.userId = aValue;
    }

    /**
     * * @return String
     * 
     * @hibernate.property column="userName" type="java.lang.String"
     *                     length="128" not-null="false" unique="false"
     */
    public String getUserName() {
	return this.userName;
    }

    /**
     * Set the userName
     */
    public void setUserName(String aValue) {
	this.userName = aValue;
    }

    /**
     * * @return String
     * 
     * @hibernate.property column="smsContent" type="java.lang.String"
     *                     length="1024" not-null="true" unique="false"
     */
    public String getSmsContent() {
	return this.smsContent;
    }

    /**
     * Set the smsContent
     * 
     * @spring.validator type="required"
     */
    public void setSmsContent(String aValue) {
	this.smsContent = aValue;
    }

    /**
     * * @return Short
     * 
     * @hibernate.property column="status" type="java.lang.Short" length="5"
     *                     not-null="true" unique="false"
     */
    public Short getStatus() {
	return this.status;
    }

    /**
     * Set the status
     * 
     * @spring.validator type="required"
     */
    public void setStatus(Short aValue) {
	this.status = aValue;
    }

    public String getFromUser() {
	return fromUser;
    }

    public void setFromUser(String fromUser) {
	this.fromUser = fromUser;
    }

}
