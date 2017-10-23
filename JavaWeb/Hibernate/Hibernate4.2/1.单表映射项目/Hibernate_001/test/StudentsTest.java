import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
		transaction.commit();
		session.close();
		sessionFactor.close();
	}
	
	@Test
	public void testSaveStudents(){
		//生成学生对象
		Students s = new Students(1, "麦克白", "男", new Date(), "莎士比亚没看过？");
		session.save(s);
	}
}
