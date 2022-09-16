package com.academy.ormsqlmapapp.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//이 객체는, mybatis의 쿼리수행 객체인 sqlsession 을 얻기 위한 sqlsessionfactory를 
//설정하기 위함이다.. 그리고 이 객체는 싱글턴으로 정의하여 중복 인스턴스 생성을 방지할 것임.
public class ConfigManager {
	private static ConfigManager instance;
	SqlSessionFactory sqlSessionFactory;
	
	private ConfigManager() {

		try {
			String resource = "com/academy/ormsqlmapapp/mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ConfigManager getInstance() {
		if(instance==null) {
			instance = new ConfigManager();
		}
		return instance;
	}
	
	//이 메서드를 통해,dao  등이 sql session객체를 반환받을수 잇또로 해주자.
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}
}
