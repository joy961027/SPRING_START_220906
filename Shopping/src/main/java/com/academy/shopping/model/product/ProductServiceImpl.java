package com.academy.shopping.model.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.category.SubCategoryDAO;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;
import com.academy.shopping.model.util.ExcelParser;
import com.academy.shopping.model.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Autowired
	private FileManager fileManager;

	@Autowired
	private ExcelParser excelParser;

	@Override
	public List selectAll() {
		return productDAO.selectAll();
	}

	@Override
	public List selectBySubCategoryId(int subcategory_id) {
		return productDAO.selectBySubCategoryId(subcategory_id);
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		List<SubCategory> subCategoryList = subCategoryDAO.selectByTopCategoryId(topcategory_id);
		List productList = new ArrayList<>();
		for (int i = 0; i < subCategoryList.size(); i++) {
			SubCategory subCategory = subCategoryList.get(i);
			List<Product> list = productDAO.selectBySubCategoryId(subCategory.getSubcategory_id());
			System.out.println("이건 리스트 " + list);
			for (Product product : list) {
				productList.add(product);
				System.out.println("이건 productList" + productList);
				System.out.println("이건 product" + product);
			}

		}
		return productList;
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}

	// 등록 완성 = dao+file저장
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product, String savePath) throws ProductException, UploadException {
		String filename = fileManager.save(product, savePath); // 파일 저장
		product.setProduct_img(filename); // 새롭게 생성된 파일명저장
		productDAO.insert(product); // db에 저장
	}

	// excel을 이용하여 insert
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registByExcel(File file, String ori,String dest) {
		List<Product> productList = excelParser.getParseResult(file);
		
		for (Product product : productList) {
			// 이미지를 서버에 저장하기 (스프링과 상관없이 개밪자의 javaSE 능력으로 해결)
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				// 파일을 대상으로 한 출력 스트림
				fis = new FileInputStream(ori + "/" + product.getProduct_img());
				long time = System.currentTimeMillis();
				String ext = FileManager.getExt(product.getProduct_img());
				String filename = time+"."+ext; //최종적으로 결정된 파일명
				product.setProduct_img(filename);
				fos = new FileOutputStream(dest + "/" + filename);//개발자가 파일명을 생성해야함;
				int data=-1;
				while(true) {
					
					data =fis.read();//다시 1byte 읽음
					if(data==-1)break; //멈춤
					fos.write(data);//다시 1byte출력
				}
				//복사완료
				System.out.println("파일 복사 완료");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fis!=null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			productDAO.insert(product); //레코드 한건 넣기 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub

	}

}
