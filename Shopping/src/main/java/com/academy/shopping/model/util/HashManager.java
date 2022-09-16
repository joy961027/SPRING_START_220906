package com.academy.shopping.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

//회원 정보중, 비밀번호를 평문으로 넣지 말자!!
//암호화 시켜넣을건데, 16진값의 해쉬값으로 변환하자!! 암호화 로직은 누가??
@Component
public class HashManager {
	
	public static String getConvertedPassword(String pass) {
		StringBuffer sb =null;
		//위의 문자열을 사람이 알아볼수 없는 해쉬값을 변환해보자!
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash= digest.digest(pass.getBytes("UTF-8"));
			//쪼개진 데이터 크기만크 반복문 돌리면서 하나씩 개별적으로 16진수 해쉬값으로 변환한후,
			//하나으 ㅣ문자열에 모아놓자
			sb = new StringBuffer();
			for(int i=0;i<hash.length; i++) {
				String hex = Integer.toHexString(0xff&hash[i]);
				if(hex.length()==1) {
					sb.append("0");
				}
				sb.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	

}
