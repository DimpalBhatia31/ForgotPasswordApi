package com.example.demo.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PasswordForgotDto;
import com.example.demo.model.Mail;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


@Service
public class EmailServiceSend {

	JavaMailSender javaMailSender;
	
	@Autowired
	public EmailServiceSend(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	   @Autowired
	    private SpringTemplateEngine templateEngine;

	public void sendNotification(PasswordForgotDto dto) throws MailException {
		
		try
		{/*
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name()
					);
			
			   Context context = new Context();
	            context.setVariables(mail.getModel());
	            System.out.println("modelllll="+mail.getModel());
	            String html = templateEngine.process("email/email-template", context);

	            helper.setTo(mail.getTo());
	            helper.setText(html, true);
	            helper.setSubject(mail.getSubject());
	            helper.setFrom(mail.getFrom());
*/
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(dto.getEmail());
			mail.setFrom("dimpalbhatia31@gmail.com");
			mail.setSubject("Dear " + "Dimpl");
			mail.setText("Your sign up process is completed with following information" + " \n email:" + dto.getEmail()
					+ "\n name:");
	            javaMailSender.send(mail);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void sendEmail(SimpleMailMessage mail)
	{
		javaMailSender.send(mail);
	}
}
