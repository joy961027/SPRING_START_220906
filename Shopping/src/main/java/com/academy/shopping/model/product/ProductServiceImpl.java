package com.academy.shopping.model.product;

import java.io.File;
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
import com.academy.shopping.model.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Autowired
	private FileManager fileManager;
	
	
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
		for(int i=0; i<subCategoryList.size(); i++) {
			SubCategory subCategory = subCategoryList.get(i);
			List<Product> list =productDAO.selectBySubCategoryId(subCategory.getSubcategory_id());
			System.out.println( "이건 리스트 " +list);
			for(Product product : list) {
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

	
	
	//등록 완성 = dao+file저장
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product,String savePath) throws ProductException, UploadException{
		String filename =fileManager.save(product,savePath); //파일 저장
		product.setProduct_img(filename); //새롭게 생성된 파일명저장
		productDAO.insert(product); //db에 저장
	}
	
	
	//excel을 이용하여 insert
	@Override
	public void registByExcel(File file) {
		//2003년이후 최선버전의서 전담객체
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
