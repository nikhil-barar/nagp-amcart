package com.nagarro.amcart.email;

import java.util.Map;

import com.nagarro.amcart.models.enums.EmailTypeEnum;

public class AMCartEmail {

	private String toAddress;
	private String fromAddress;
	private String message;
	private EmailTypeEnum type;
	private String subject;
	private Map<String, String> params;
	private String altMsg;
	private EmailAttachmentFile attachment;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailTypeEnum getType() {
		return type;
	}

	public void setType(EmailTypeEnum type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getAltMsg() {
		return altMsg;
	}

	public void setAltMsg(String altMsg) {
		this.altMsg = altMsg;
	}

	public EmailAttachmentFile getAttachment() {
		return attachment;
	}

	public void setAttachment(EmailAttachmentFile attachment) {
		this.attachment = attachment;
	}

}
