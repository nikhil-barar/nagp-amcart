package com.nagarro.amcart.services.impl;

import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nagarro.amcart.email.AMCartEmail;
import com.nagarro.amcart.email.EmailAttachmentFile;
import com.nagarro.amcart.email.EmailConfiguration;
import com.nagarro.amcart.services.AmcartEmailService;

@Service("emailService")
public class AmcartEmailServiceImpl implements AmcartEmailService {
	
	private static final String VM_PATH = "./resources/emailTemplate/";
	private static final String VM_Extension = ".vm";

	@Override
	public boolean sendEmail(AMCartEmail amCartEmail) {
		boolean status = false;

		try {
			ImageHtmlEmail email = new ImageHtmlEmail();
			EmailConfiguration.configure(email);
			if (null != amCartEmail.getAttachment()) {
				EmailAttachmentFile attachmentFile = amCartEmail
						.getAttachment();
				EmailAttachment attachment = new EmailAttachment();
				attachment.setDescription(attachmentFile.getDescription());
				attachment.setName(attachmentFile.getName());
				attachment.setPath(attachmentFile.getPath());
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				email.attach(attachment);
			}
			email.addTo(amCartEmail.getToAddress());
			email.setSubject(amCartEmail.getSubject());
			email.setFrom(amCartEmail.getFromAddress());
			boolean isHTMLMessaheCreated = createEmailMessage(amCartEmail,email);
			if(StringUtils.isNotEmpty(amCartEmail.getMessage()) && BooleanUtils.isFalse(isHTMLMessaheCreated)){
				email.setMsg(amCartEmail.getMessage());
			}
			if(StringUtils.isNotEmpty(amCartEmail.getAltMsg())){
				email.setTextMsg(amCartEmail.getAltMsg());
			}
			
			email.send();
			status = true;
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	private boolean createEmailMessage(AMCartEmail amCartEmail, ImageHtmlEmail email){
		boolean messageCreated = false;
		if(!ObjectUtils.isEmpty(amCartEmail.getType())){
			String vmFileName = amCartEmail.getType().name().toLowerCase();
			StringBuilder vmPathBuilder = new StringBuilder();
			vmPathBuilder.append(VM_PATH);
			vmPathBuilder.append(vmFileName);
			vmPathBuilder.append(VM_Extension);
			VelocityEngine velocityEngine = new VelocityEngine();
			String vmPath = vmPathBuilder.toString();
			Template template = velocityEngine.getTemplate(vmPath);
			StringWriter writer = new StringWriter();
			VelocityContext context  = createParams(amCartEmail);
			if(ObjectUtils.isEmpty(context)){
				messageCreated = false;
			}else{
				template.merge( context, writer );
				try {
					email.setHtmlMsg(writer.toString());
					messageCreated = true;
				} catch (EmailException e) {
					e.printStackTrace();
				}
				
			}
		}
		return messageCreated;
	}

	
	private VelocityContext createParams(AMCartEmail email){
		VelocityContext context  = null;
		if(!ObjectUtils.isEmpty(email.getParams())){
			context = new VelocityContext();
			for (Map.Entry<String, String> entry : email.getParams().entrySet()){
				context.put(entry.getKey(), entry.getValue());
			}
		}
		return context;
	}
}
