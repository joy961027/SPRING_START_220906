package com.academy.shopping.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;

//rest요청에 대한 응답정보를 보다 체계적으로 구성하기 위한 객체
//그냥 string으로만 응답하면 @responsebody의 json변환 기능을 사용할수 없다.
//따라서 자바의 객체를 자동으로 json으로 변환해주게끔 아래와 같은 클래스를 정의한다
@Data
@AllArgsConstructor
public class Message {
	private int code; //성공, 실패 등의 정보를 담는 코드 1성공, 0 실패
	private String msg;
}
