package com.academy.shopping.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_id;
	private String category_name;
	private List<SubCategory> subList;

}
