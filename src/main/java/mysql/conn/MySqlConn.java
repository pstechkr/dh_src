package mysql.conn;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlConn {
	private SqlSessionFactory factory = null;
	
	public MySqlConn(){
		try{
			Reader reader = Resources.getResourceAsReader("mybatis/config/mybatis-config.xml");
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(reader);
			reader.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public SqlSession openSession(){ 
		return factory.openSession();
	}
}
