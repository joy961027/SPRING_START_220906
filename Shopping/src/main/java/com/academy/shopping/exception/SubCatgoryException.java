package com.academy.shopping.exception;

public class SubCatgoryException extends RuntimeException {
	
	public SubCatgoryException(String msg) {
		super(msg);
	}
	public SubCatgoryException(String msg ,Throwable e) {
		super(msg,e);
	}
	public SubCatgoryException(Throwable e) {
		super(e);
	}
}
