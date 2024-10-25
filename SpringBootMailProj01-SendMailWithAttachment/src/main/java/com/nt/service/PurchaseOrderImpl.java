package com.nt.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("purchaseService")
public class PurchaseOrderImpl   implements  IpurchaseOrder{
    
	@Autowired
	private JavaMailSender sender;
		
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	
	@Override
	public String purchase(String[] items, double[] prices, String[] emails) throws Exception {
		//calculate  bill amout
		double billAmount=0.0;
		for(double  p : prices)
		{
			billAmount=billAmount+p;
			
		}
		String msg=Arrays.toString(items)+"are purchaesd having  prices:: "+Arrays.toString(prices)+"with the bill amount::"+billAmount;
		String status=sendMail(msg,	emails);
		return msg+"------>"+status;
	}
	
	
	private  String  sendMail(String  messageBody,String[] ToEmails) throws  Exception
	{
		
		
		//create MimeMessage obj
		MimeMessage message=sender.createMimeMessage();
		//create MImeMessageHelper obj
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		//set messaeg  header
		helper.setFrom(fromEmail);
		helper.setTo(ToEmails);
		helper.setSubject("open it to know it");
		helper.setSentDate(new Date());
		helper.setText(messageBody);
		helper.addAttachment("nit.jpg",new ClassPathResource("nit.jpg"));//place nit.jpg  file  src/main/resource  folder
		sender.send(message);
		return "mail sent";
		
		
		
		
	}

}
