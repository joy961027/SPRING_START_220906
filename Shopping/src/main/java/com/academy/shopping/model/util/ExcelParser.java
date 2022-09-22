package com.academy.shopping.model.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;
//엑셀 파일을 분석하는 객체
@Component
public class ExcelParser {
	
	public List getParseResult(File file) {
		List<Product> productList = new  ArrayList<>(); //product Dto를 담기위함 
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			System.out.println("엑셀 접근 성공");
			//엑셀 파일의 구성 시트 접근
			XSSFSheet sheet = workbook.getSheetAt(0);
			int totalRow =sheet.getPhysicalNumberOfRows(); //실제 사용자가 입력한 row 수
			System.out.println("현재 사용중인 row수는" +totalRow);
			for(int a=1; a<totalRow; a++) {
				XSSFRow row = sheet.getRow(a);
				int totalCell =row.getPhysicalNumberOfCells();
				Product product = new Product();
				//하나의 row에 소속된 각셀을 접근하자
				for(int i=0; i<totalCell; i++) {
					XSSFCell cell = row.getCell(i);
					switch(i) {
					case 0:	product.setProduct_name(cell.getStringCellValue());break;
					case 1:	product.setBrand(cell.getStringCellValue());break;
					case 2:	product.setPrice((int)(cell.getNumericCellValue()));break;
					case 3:	product.setDiscount((int)cell.getNumericCellValue());break;
					case 4:	product.setMemo(cell.getStringCellValue());break;
					case 5:	product.setDetail(cell.getStringCellValue());break;
					case 6:	product.setProduct_img(cell.getStringCellValue());break;
					case 7:	
						SubCategory subcategory = new SubCategory();
						subcategory.setSubcategory_id((int)cell.getNumericCellValue());
						product.setSubcategory(subcategory);
					}
				}
				productList.add(product);
				System.out.println();//줄바꿈
			}
			System.out.println("엑셀 분석 결과 : "+productList);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return productList;
	}

}
