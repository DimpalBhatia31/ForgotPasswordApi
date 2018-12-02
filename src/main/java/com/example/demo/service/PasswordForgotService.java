package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dto.PasswordForgotDto;
import com.example.demo.dto.PasswordResetDto;
import com.example.demo.model.Mail;
import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import com.example.demo.repository.PasswordTokenRepository;
import com.example.demo.repository.USerRepositpry;

@Service
public class PasswordForgotService {
	@Autowired
	USerRepositpry userRepository;
	@Autowired
	PasswordTokenRepository tokenRepository;
	@Autowired
	EmailServiceSend emailService;
	
	public String forgotPasswordSendMail(PasswordForgotDto passwordForgotDto,HttpServletRequest request)
	{
		Optional<User> user=userRepository.findByEmail(passwordForgotDto.getEmail());
		if(!user.isPresent())
		{
			return "we could not find an account with this email";
		}
		else
		{
			User userInfo=user.get();
	        PasswordResetToken token = new PasswordResetToken();
	        token.setToken(UUID.randomUUID().toString());
	        token.setUser(userInfo);
	        token.setExpiryDate(30);
	        tokenRepository.save(token);
	        
	        
	    	String appUrl = request.getScheme() + "://" + request.getServerName();
	        
	        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(userInfo.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
					+ "/reset?token=" + token.getToken());
	        
	        
	        emailService.sendEmail(passwordResetEmail);
        
	     /*   Mail mail = new Mail();
	        mail.setFrom("no-reply@memorynotfound.com");
	        mail.setTo(userInfo.getEmail());
	        mail.setSubject("Password reset request");
	        
	        Map<String, Object> model = new HashMap<>();
	        model.put("token", token);
	        model.put("user", user);
	        model.put("signature", "https://memorynotfound.com");
	        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	        System.out.println("scheme===="+request.getScheme()+"=====    server name:"+request.getServerName());
	        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
	        mail.setModel(model);
	        emailService.sendNotification(passwordForgotDto);
*/
	        return "redirect:/forgot-password?success";
		}
	}
	
	
	public String checkToken(String token)
	{
		Optional<PasswordResetToken> passResetToken=tokenRepository.findByToken(token);
		if(passResetToken.isPresent())
		{
			PasswordResetToken passTokn=passResetToken.get();
			if(!passTokn.isExpired())
			{
				return "reset Pass";
			}
			else {
				return "Time Expire";
			}
		
		}
		else {
			return "Invalid Token";
		}
	}
	
	public String resetPass(PasswordResetDto passResetDto)
	{
		Optional<PasswordResetToken> passResetToken=tokenRepository.findByToken(passResetDto.getToken());
		if(passResetToken.isPresent())
		{
			PasswordResetToken passTokn=passResetToken.get();
			User user=passTokn.getUser();
			user.setPassword(passResetDto.getNewPass());
			userRepository.save(user);
			return "Password change succesfuly";
			
		}
		
		return "Invalid token";
	}
}
