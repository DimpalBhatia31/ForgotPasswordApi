package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.USerRepositpry;

@Service
public class UserService {

	@Autowired
	USerRepositpry userRepo;

	public void createUSer(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setPassword(userDto.getPassword());
		userRepo.save(user);
	}

}
