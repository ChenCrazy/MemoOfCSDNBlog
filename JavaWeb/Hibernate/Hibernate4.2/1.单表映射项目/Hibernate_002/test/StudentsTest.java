import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *	测试类
 */

public class StudentsTest {
	
	private SessionFactory sessionFactor;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void Init(){
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactor = config.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session = sessionFactor.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	
	@After
	public void destory(){
		transaction.commit();//提交事务
		session.close();
		sessionFactor.close();
	}
	
	@Test
	public void testSaveStudents(){
		//生成学生对象
		Students s = new Students(1, "麦克白", "男", new Date(), "莎士比亚没看过？");
//		session.doWork(new Work() {
//			
//			@Override
//			public void execute(Connection connection) throws SQLException {
//				// connection就是传过来jdbc的一个连接对象,使用此对象,将其改为自动提交
//				connection.setAutoCommit(true);
//			}
//		});
		session.save(s);
//		session.flush();//需要调用此方法刷新,发出sql语句
	}
}
