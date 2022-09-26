package com.academy.shopping.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.util.Message;
//restcontroller에서 발생되는 모든 예외를 여기서 잡자
@ControllerAdvice
public class RestGlobalExceptionAspect {
	
	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Message> handleException(MemberException e){
		Message message = new Message(Message.FAIL, e.getMessage());
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message,HttpStatus.OK);
		return entity;
	}
	

}
