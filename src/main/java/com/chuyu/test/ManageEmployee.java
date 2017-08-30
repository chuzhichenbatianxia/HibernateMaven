package com.chuyu.test;

import com.chuyu.pojo.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017-08-23.
 */
public class ManageEmployee {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory=new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("failed to create sessionFactory object."+e.getMessage());
        }
        ManageEmployee manageEmployee=new ManageEmployee();
//        Integer empID1 = manageEmployee.addEmployee("zhuyuping3", "Ali", 10000);
//        Integer empID2 = manageEmployee.addEmployee("Daisy", "Das", 5000);
//        Integer empID3 = manageEmployee.addEmployee("John", "Paul", 10000);
//        manageEmployee.listEmployees();
//        manageEmployee.updateEmployee(1,15982);
        manageEmployee.deleteEmployee(1);
    }

    public Integer addEmployee(String fname,String lname,int salary){
        Session session=factory.openSession();
        Transaction tx=null;
        Integer employeeID=null;
        try {
            tx=session.beginTransaction();
            Employee employee=new Employee(fname,lname,salary);
            employeeID= (Integer) session.save(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    public void listEmployees(){
        Session session=factory.openSession();
        Transaction tx=null;
        tx=session.beginTransaction();
        try {
            List<Employee> employees=session.createQuery("FROM com.chuyu.pojo.Employee").list();
            for(Iterator iterator=employees.iterator();iterator.hasNext();){
                Employee employee= (Employee) iterator.next();
                System.out.println("first name"+employee.getFirstName());
                System.out.println("last name"+employee.getLastName());
                System.out.println("salary"+employee.getSalary());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployee(Integer id,int salaty){
        Session session=factory.openSession();
        Transaction tx=null;
        tx=session.beginTransaction();
        try {
            Employee employee=session.get(Employee.class,id);
            employee.setSalary(salaty);
            session.update(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void deleteEmployee(Integer id){
        Session sessio=factory.openSession();
        Transaction tx=null;
        try {
            tx=sessio.beginTransaction();
            Employee employee=sessio.get(Employee.class,id);
            sessio.delete(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            sessio.close();
        }

    }

}
