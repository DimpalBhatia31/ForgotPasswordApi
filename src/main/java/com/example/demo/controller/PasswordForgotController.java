package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PasswordForgotDto;
import com.example.demo.dto.PasswordResetDto;
import com.example.demo.service.PasswordForgotService;
@RestController
public class PasswordForgotController {
	
	@Autowired
	PasswordForgotService passwordForgotService;
	
	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestBody PasswordForgotDto dto,HttpServletRequest request)
	{
		return passwordForgotService.forgotPasswordSendMail(dto, request);
	}
	
	@GetMapping("forgotPassword/{token}")
	public String getToken(@PathVariable String token)
	{
		return passwordForgotService.checkToken(token);
	}
	
	@PutMapping("forgotPassword")
	public String resetPass(@RequestBody PasswordResetDto passResetDto)
	{
		return passwordForgotService.resetPass(passResetDto);
	}

}
