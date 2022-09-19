package com.academy.shopping.model.domain;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private String prduct_name;
	private String brand;
	private int price;
	private int discount;
	private String memo;
	private String detail;
	private String product_img;
	private SubCategory subcategory; //1:1 association

}
