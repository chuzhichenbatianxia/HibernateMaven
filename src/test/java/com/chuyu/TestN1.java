package com.chuyu;

import com.chuyu.pojo.Employee;
import com.chuyu.pojo.TUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zhuyuping2 on 2017-08-30.
 */
public class TestN1 {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        Configuration configuration = new Configuration().configure();//创建配置对象
        sessionFactory = configuration.buildSessionFactory();//创建会话工厂
        session = sessionFactory.openSession();//开启会话
        transaction = session.beginTransaction();//开启事务
    }

    @After
    public void destory() {
        transaction.commit();//事务提交
        session.close();//关闭会话
        sessionFactory.close();//关闭会话工厂
    }

    @Test
    public void testSelectByParam(){
        String hql="from TUser ";
        Query query=session.createQuery(hql);
        List result=query.list();
        for (Iterator iterator = result.iterator(); iterator.hasNext();){
            TUser user= (TUser) iterator.next();
            System.out.println("name:"+user.getNeme()+"\n"+"groupName"+user.getGroup().getName());
        }

    }
//    @Test
//    public void
}
