package com.hacktober.blog.otp;

import com.hacktober.blog.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.security.SecureRandom;

@Service
public class OtpService {

	private final UnifiedJedis jedis;
	@Autowired
	private EmailService emailService;
	public OtpService(UnifiedJedis jedis) {
		this.jedis = jedis;
	}

	public String generateOtp(String email) throws JedisConnectionException {
		SecureRandom secureRandom=new SecureRandom();
		int otp=100000+secureRandom.nextInt(900000);
		jedis.setex("email:"+email,300,String.valueOf(otp));
		return String.valueOf(otp);
	}

	public boolean verifyOtp(String email, String inputOtp) throws JedisConnectionException {
		if(email==null || inputOtp==null){
			return false;
		}
		String key="email:"+email;
		String otp=jedis.get(key);
		if(otp!=null && otp.equals(inputOtp)){
			jedis.del(key);
			return true;
		}
		return false;
	}
	
	
}
