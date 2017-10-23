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
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpenSession() {
		Configuration config = new Configuration().configure();
		// 获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 获得一个session对象
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		System.out.println(session1==session2);//false
		/*if (session != null) {
			System.out.println("session创建成功!");
		} else {
			System.out.println("session创建失败!");
		}*/
	}

	@Test
	public void testGetCurrentSession() {
		Configuration config = new Configuration().configure();
		// 获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 获得一个session对象
		Session session1 = sessionFactory.getCurrentSession();//此处报错,因为没有在hibernate.cfg.xml中进行配置
		Session session2 = sessionFactory.getCurrentSession();
		System.out.println(session1==session2);//true
		/*if (session!=null) {
			System.out.println("session创建成功!");
		} else {
			System.out.println("session创建失败!");
		}*/
	}
	
	@Test
	public void testSaveStudentsWithOpenSession(){
		Configuration config = new Configuration().configure();
		// 获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建session对象
		Session session1 = sessionFactory.openSession();
		//开启事务
		Transaction transaction = session1.beginTransaction();
		//生成学生对象
		Students s = new Students(1,"夜喵","男",new Date(),"深圳");
		session1.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode:"+connection.hashCode());
			}
		});
		session1.save(s);
		//session.close();
		transaction.commit();
		
		Session session2 = sessionFactory.openSession();
		transaction = session2.beginTransaction();
		 s = new Students(2,"铲屎喵","男",new Date(),"广州");
		 session2.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					System.out.println("connection hashCode:"+connection.hashCode());
				}
			});
		session2.save(s);
		transaction.commit();
	}
	
	@Test
	public void testSaveStudentsWithGetCurrentSession(){
		Configuration config = new Configuration().configure();
		// 获得服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
				.buildServiceRegistry();
		// 获得sessionFactory对象
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建session对象
		Session session1 = sessionFactory.getCurrentSession();
		//开启事务
		Transaction transaction = session1.beginTransaction();
		//生成学生对象
		Students s = new Students(1,"夜喵","男",new Date(),"深圳");
		session1.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println("connection hashCode:"+connection.hashCode());
			}
		});
		session1.save(s);
		//session.close();
		transaction.commit();
		
		Session session2 = sessionFactory.getCurrentSession();
		transaction = session2.beginTransaction();
		 s = new Students(2,"铲屎喵","女",new Date(),"广州");
		 session2.doWork(new Work() {
				
				@Override
				public void execute(Connection connection) throws SQLException {
					System.out.println("connection hashCode:"+connection.hashCode());
				}
			});
		session2.save(s);
		transaction.commit();
	}
}
