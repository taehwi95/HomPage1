package mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBService {
	
	//필드
	private SqlSessionFactory factory;
	
	//생성자
	private DBService() {
		try {
			String resource = "mybatis/config/sqlmap-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//생성자가 private 이므로 여기서 DBService()를 new해야한다.
	private static DBService service = new DBService();
	
	//new DBService()가 호출되면 sqlSessionFactory factory가 생성된다.
	//생성된 factory를 호출할 수 있는 DBService service 객체를 리턴하는 getter를 만든다.
	public static DBService getInstance() {
		return service;
	}
	
	// private SQLSessionFactory factory를 외부에 리턴하는 getter를 만든다.
	public SqlSessionFactory getFactory() {
		return factory;
	}
	
}
