package com.nt.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailsUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static  Logger  log=LoggerFactory.getLogger(EmailsUtils.class);
	
	public boolean sendEmailMeassage(String toMail,String subject,String body)throws Exception
	{ 
		 boolean mailSentStatus=false;
		 try {
			 
			 MimeMessage message=mailSender.createMimeMessage();
			 MimeMessageHelper helper=new MimeMessageHelper(message,true);
			 helper.setTo(toMail);
			 helper.setSentDate(new Date());
			 helper.setSubject(subject);
			 helper.setText(body,true);
			 mailSender.send(message);
			 mailSentStatus=true;
		 }catch(Exception e)
		 {
				log.error(e.getMessage());
			 throw e;
		 }
		return mailSentStatus;
	}

}
