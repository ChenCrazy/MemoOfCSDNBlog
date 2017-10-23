import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
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
	@Test
	public void testWriteBlob() throws Exception{
		Students s = new Students(1, "麦克白", "男", new Date(), "莎士比亚没看过？");
		File file = new File("E:"+File.separator+"71.jpg");
		InputStream iStream = new FileInputStream(file);
		Blob image = Hibernate.getLobCreator(session).createBlob(iStream, iStream.available());
		s.setPicture(image);
		session.save(s);
	}
	@Test
	public void testReadBlob() throws Exception{
		Students s = (Students) session.get(Students.class, 1);
		Blob image = s.getPicture();
		InputStream iStream = image.getBinaryStream();
		File file = new File("E:"+File.separator+"21.jpg");
		OutputStream outputStream = new FileOutputStream(file);
		byte[] bs = new byte[iStream.available()];
		iStream.read(bs);
		outputStream.write(bs);
		iStream.close();
	}
}
