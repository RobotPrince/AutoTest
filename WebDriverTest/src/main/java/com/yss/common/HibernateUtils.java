package com.yss.common;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
public class HibernateUtils {

//	/通过Configuration获得一个SessionFactory对象
//	SessionFactory sf 
//	= new Configuration().configure().buildSessionFactory();
//	            //打开一个Session
//	            Session session = sf.openSession();
//	            //开始一个事务
//	            Transaction tx = session.beginTransaction();
//	            //创建一个Student对象
//	            Student stu = new Student();
//	            //通过Student的setter方法改变它的属性
//	            //注意student_id不用我们设置
//	            stu.setStudent_name("zhangsan");
//	            stu.setStudent_age(18);
//	            //通过session的save()方法将Student对象保存到数据库中
//	            session.save(stu);
//	            //提交事务
//	            tx.commit();
//	            //关闭会话
//	            session.close();
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
        	//通过Configuration获得一个SessionFactory对象
            SessionFactory buildSessionFactory = new Configuration().configure().buildSessionFactory();
            return buildSessionFactory;
        }
        catch (Throwable ex) {
        	Logger.getLogger(HibernateUtils.class).error("初始化sessionFactory失败");
            throw new ExceptionInInitializerError(ex);
        }
    }

	public static SessionFactory getSessionFactory() {
    	Logger.getLogger(HibernateUtils.class).error("初始化sessionFactory");
        return sessionFactory;
    }
    
    public static void shutdown() {
        getSessionFactory().close();
    }
}
