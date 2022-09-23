package com.academy.shopping.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.category.TopCategoryService;

/*쇼핑몰에서 상위카테고리는 어디서건 보여줄 정보이므로 
 * 어플리케이션의 횡단적 관심사항에 해단된다.
 * 따라서 상위 카테고리 목록을 가져오는 코드를 별도의 객체로 정의하여, 
 * aop의 Aspect로 정의해놓고, 필요할때마다 이코드를 관여시키자.
 * */
public class TopCategoryAspect {
	@Autowired
	private TopCategoryService topCategoryService;
	//이 메서드는 ,쇼핑몰의 상위 카테고리를 필요로하는 모든 메서드에서 공통적으로 동작할 예정
	// ex)
	public Object getCategoryList(ProceedingJoinPoint joinPoint) {
		System.out.println("컨트롤러가 동작할때 나 관여하는 중...");
		//원래 호출하려던 객체명 알아맞추기
		Object target = joinPoint.getTarget();
		System.out.println("호출하려던 객체는"+target.getClass().getName());
		System.out.println( "호출하려던 메서드는" +joinPoint.getSignature());
		
		
		//원래 호출하려던 메서드 대신 호출하게 해줌(원래메서드역할을 수행)
		Object returnObj =null;
		try {
			returnObj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(returnObj instanceof ModelAndView) {
			ModelAndView mav  =(ModelAndView) returnObj;
			List topCategoryList = topCategoryService.selectAll();
			mav.addObject("topCategoryList",topCategoryList);
			returnObj= mav;
		}
		return returnObj	; //DispatcherServlet에게 반환되며, 이때 dispatcherservlet은 viewresolver에게 jsp페이지를 얻기 위한 해석을 맡김
	}
	
}
