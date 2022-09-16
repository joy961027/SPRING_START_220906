package com.academy.springdb.model.domain;

import java.io.File;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//VO(VALUE OBJECT) - readOnly Object Setter X 
//DTO(DATA TRANSFER OBJECT)
@Data
public class Gallery {
	private String title;
	private String writer;
	private MultipartFile photo; //html의 파라미터 이름과 일치할 경우, 파일업로드 처리를 내부적으로 자동처리
}
