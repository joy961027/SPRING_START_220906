package com.academy.shopping.model.product;

import java.io.File;
import java.util.List;

import com.academy.shopping.model.domain.Product;

public interface ProductService {
	public List selectAll();
	public List selectBySubCategoryId(int subcategory_id);
	public List selectByTopCategoryId(int topcategory_id);
	public Product select(int product_id);
	public void regist(Product product, String savePath);
	public void registByExcel(File file, String ori, String dest);
	public void update(Product product);
	public void delete(Product product);
		
}
