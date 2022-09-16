package com.academy.springdb.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springdb.model.domain.Gallery;

@Controller
public class UploadController {
	//클라이언트가 전송한 이미지 파일을 업로드 처리
	@PostMapping("/upload")
	public ModelAndView save(Gallery gallery, HttpServletRequest request) {

		System.out.println("제목은"+gallery.getTitle());
		System.out.println("작성자는"+gallery.getWriter());
		System.out.println("파일은"+gallery.getPhoto());
		
		//업로드된 파일에 대한 분석
		MultipartFile multi= gallery.getPhoto();
		System.out.println("파일의유형" + multi.getContentType());
		System.out.println("파일의이름" +multi.getOriginalFilename());
		System.out.println("html의 파라미터 이름" +multi.getName());
		System.out.println("파일의크기 byte" +multi.getSize());
		
		//아직 물리적 파일로 존재시킨 적이 없으므로, 원하는 서버의 디렉토리에 파일을 저장해보자
		String path =request.getServletContext().getRealPath("/resources/data");
		String filepath = path+"/"+multi.getOriginalFilename();
		try {
			multi.transferTo(new File(filepath));
			System.out.println(filepath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
