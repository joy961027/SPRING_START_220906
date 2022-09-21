package com.academy.shopping.model.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;

@Component
public class FileManager {
	
	//파일과 관련된 유용한 기능을 구현한 객체
	public String save(Product product, String savePath) throws UploadException{
		MultipartFile multi = product.getPhoto();
		//파일저장
		String ext=	getExt(multi.getOriginalFilename());//확장자 반환
		long time = System.currentTimeMillis();
		try {
			System.out.println(savePath+"/"+time+"."+ext);
			multi.transferTo(new File(savePath+"/"+time+"."+ext));
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new UploadException("upload 실패",e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("upload 실패",e);
		}
		return time+"."+ext;
		
	}
	//엑셀파일 업로드 용
	public File saveExcel(String path, MultipartFile excel){
		
		File file=null;
		try {
				excel.transferTo(file = new File(path+"/"+excel.getOriginalFilename()));
				System.out.println(file.getAbsolutePath());
		} catch (IllegalStateException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		return file;
	}
	
	
	public static String  getExt(String path) {
		int index =path.lastIndexOf("."); //가장 마지막 점의 인데스 구하기
		return path.substring(index+1,path.length());
		
	}
}
