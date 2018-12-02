package com.example.demo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class PasswordResetToken {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long reset_token_id;
	@Column(nullable=false,unique=true)
	String token;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(nullable=false,name="userId")
	User user;
	
	@Column(nullable=false)
	Date expiryDate;

	public Long getReset_token_id() {
		return reset_token_id;
	}
	public void setReset_token_id(Long reset_token_id) {
		this.reset_token_id = reset_token_id;
		
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		
		this.user = user;
	}

	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
    public void setExpiryDate(int minutes){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }

    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }
	

}
