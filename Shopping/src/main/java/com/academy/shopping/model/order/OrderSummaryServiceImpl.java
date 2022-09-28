package com.academy.shopping.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.EmailException;
import com.academy.shopping.exception.OrderDetailException;
import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderDetail;
import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.util.MailFormReader;
import com.academy.shopping.model.util.MailSender;

@Service
public class OrderSummaryServiceImpl  implements OrderSummaryService{
	//주문 요약 관련 dao
	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	@Autowired
	private MailSender mailSender;
	//주문 상세 관련 dao
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	@Autowired
	private MailFormReader mailFormReader;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void order(OrderSummary orderSummary) throws OrderSummaryException, OrderDetailException,EmailException{
		//insert호출전 ordersummary dto안의 ordersummary_id 값은 ?0
		orderSummaryDAO.insert(orderSummary);//
		//insert 호출후 ordersummary dto안의 ordersummary_id값은 ? 가장최근에 일이킨 시퀀스값으로 채워짐
		
		//구매한 물건 수만큼
		for(int i = 0; i<orderSummary.getOrderDetailList().size(); i++ ) {
			OrderDetail orderDetail = orderSummary.getOrderDetailList().get(i);
			//orderdetail이 필요로하는 부모의 foreign key값을 대입해주자.
			orderDetail.setOrdersummary_id(orderSummary.getOrdersummary_id());
			System.out.println("orderdetail "+ orderDetail);
			orderDetailDAO.insert(orderDetail);
		}
		//email발송
		System.out.println("서비스에서의 메일 폼경로 " +mailFormReader.getPath());//외부로부터 결정받은상태
		String content = mailFormReader.getStringFromMailFrom("고객님 바보");
		mailSender.send(content);
	}

	@Override
	public List selectAll() {
		return orderSummaryDAO.selectAll();
	}

	@Override
	public OrderSummary select(int ordersummery_id) {
		return orderSummaryDAO.select(ordersummery_id);
	}

	@Override
	public List selectByCustomerId(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}
	

}
