package com.test.helper;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	
	/* We call this method to encrypt the password */
	public static String MD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1,messageDigest);
			String hashtext = number.toString(16);
			while(hashtext.length() < 32) {
				hashtext = "0"+hashtext;
			}
			return hashtext;							// returning the encrypted password where the function is called
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		
	}

}
