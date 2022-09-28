package com.academy.shopping.model.util;
//한줄씩 html 태그를 넣지 말고 파일을 대상으로 스트림으로 읽어와
//한줄씩 누적하여 문자열로 반환하는 객체

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
public class MailFormReader {
	FileReader fr; 
	BufferedReader br;
	
	@Setter
	@Getter
	private String path;
	
	
	public String getStringFromMailFrom(String content){
		StringBuffer sb = new StringBuffer();
		try {
			fr= new FileReader(path);
			br = new  BufferedReader(fr);
			//한줄씩 읽어보자
			while(br.ready()) {
				String msg = br.readLine();
				if(msg.contains("@@")) {
					sb.append(content);
				}
				sb.append(msg);
			}
			System.out.println("읽은 결과 : " +sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fr!=null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
