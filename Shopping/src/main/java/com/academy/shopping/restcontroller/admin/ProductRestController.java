package com.academy.shopping.restcontroller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.product.ProductDAO;
import com.academy.shopping.model.product.ProductService;
import com.academy.shopping.model.util.Message;

import oracle.jdbc.proxy.annotation.Post;

@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/admin/product/delete")														//이놈을 넣어야 json과 dto의 매핑
	public ResponseEntity<Message> removeFile(HttpServletRequest request, @RequestBody Product product){
		System.out.println("넘겨 받은 상품id는 "+product.getProduct_id());
		System.out.println("넘겨 받은 상품img는 "+product.getProduct_img());
		String filePath = request.getServletContext().getRealPath("/resources/data/"+product.getProduct_img());
		productService.remove(product, filePath);
		Message message = new Message(Message.SUCCESS,"상품 삭제 성공");
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		
		return entity;
	}

}
