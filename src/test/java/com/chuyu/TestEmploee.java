package com.chuyu;

import com.chuyu.pojo.Employee;
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
 * Created by Administrator on 2017-08-09.
 */
public class TestEmploee {
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

    /**
     * 使用group by查询
     */
    @Test
    public void testSelect() {
        String hql="SELECT SUM(salary), firstName FROM Employee " +
                "GROUP BY firstName";
        Query query=session.createQuery(hql);
        List result=query.list();

        for (Iterator iterator=result.iterator();iterator.hasNext();){
            Object[] objects= (Object[]) iterator.next();
            System.out.println("firstName:"+objects[1]+"总数："+objects[0]);
        }

    }
    /**
     * 使用命名参数查询
     */
    @Test
    public void testSelectByParam(){
        String hql="from Employee e where e.id=:employee_id";
        Query query=session.createQuery(hql);
        query.setParameter("employee_id",2);
        List result=query.list();
        for (Iterator iterator=result.iterator();iterator.hasNext();){
            Employee employee= (Employee) iterator.next();
            System.out.println("firstName:"+employee.getFirstName());
        }

    }
    /**
     * UPDATE 语句
     */
    @Test
    public void testUpdate(){
        String hql="update Employee set salary=:salary where id=:employee_id";
        Query query=session.createQuery(hql);
        query.setParameter("salary",16982);
        query.setParameter("employee_id",2);
        int result=query.executeUpdate();
        System.out.println("rows affected:"+result);
    }
    /**
     * delete 语句
     */
    @Test
    public void TestDelete(){
        String hql="delete from Employee where id=:employee_id";
        Query query=session.createQuery(hql);
        query.setParameter("employee_id",2);
        int result=query.executeUpdate();
        System.out.println("rows affected:"+result);
    }
    /**
     * INSERT 语句
     * HQL 只有当记录从一个对象插入到另一个对象时才支持 INSERT INTO 语句
     */
    @Test
    public void testInsert(){
        String hql="insert into Employee(firstName,lastName,salary) select firstName,lastName,salary from Employee ";
        Query query=session.createQuery(hql);
        int result=query.executeUpdate();
        System.out.println("rows affected:"+result);
    }
    /**
     * 分页
     */
    @Test
    public void testPage(){
        String hql="from Employee ";
        Query quer=session.createQuery(hql);
        quer.setFirstResult(0);
        quer.setMaxResults(10);
        List result=quer.list();
        for (Iterator iterator=result.iterator();iterator.hasNext();) {
            Employee employee= (Employee) iterator.next();
            System.out.println("id:"+employee.getId());
        }
    }
}
