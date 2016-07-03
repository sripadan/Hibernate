package com.hibernate.hql;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Example1_HQLBasic {

	/**
	 * @param args
	 */
	private SessionFactory sessionFactory = null;
	
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Example1_HQLBasic testHQLExample1 = new Example1_HQLBasic();
		
		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();
		
		// 2. Build SessionFactory
		testHQLExample1.sessionFactory = config.buildSessionFactory();
		
		testHQLExample1.createDepartmentWithRelatedEmplyoees();;
		
		Query q  = session.createQuery("from Employee");
		List<Employee> employees = q.list();
		
		System.out.println("Empno\tEname\tSal\t\tDeptno");
		for(Employee employee:employees)
		{
			System.out.print(employee.getEmpno()+"\t");
			System.out.print(employee.getEname()+"\t");
			System.out.print(employee.getSal()+"\t\t");
			System.out.print(employee.getDeptno()+"\t");
			System.out.println();
			System.out.println();
		}
		
		session.close();
	}

	public void createDepartmentWithRelatedEmplyoees(){
		Transaction  tx = null;
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();
			// start a new transaction
			tx = session.beginTransaction(); 
			
			Dept d1 = new Dept();
			d1.setDname("IT");
			d1.setLoc("Hyderabad");
			session.save(d1);
			
			Dept d2 = new Dept();
			d2.setDname("CS");
			d2.setLoc("Bang");
			session.save(d2);
			
			Dept d3 = new Dept();
			d3.setDname("HR");
			d3.setLoc("Delhi");
			session.save(d3);
			
			/*Dept d4 = new Dept();
			d4.setDname("ISIT");
			d4.setLoc("Mumbai");
			session.save(d4);*/
			
			Employee e = new Employee();
			e.setEmpno(2001);
			e.setEname("Rakesh");
			e.setDeptno(1);
			e.setSal(10000);
			session.save(e);
			
			Employee e1 = new Employee();
			e1.setEmpno(2002);
			e1.setEname("Phani");
			e1.setDeptno(1);
			e1.setSal(15000);
			session.save(e1);

			Employee e2 = new Employee();
			e2.setEmpno(2003);
			e2.setEname("Kiran");
			e2.setDeptno(1);
			e2.setSal(17000);
			session.save(e2);

			Employee e3 = new Employee();
			e3.setEmpno(2004);
			e3.setEname("Kumar");
			e3.setDeptno(2);
			e3.setSal(25000);
			session.save(e3);

			Employee e4 = new Employee();
			e4.setEmpno(2005);
			e4.setEname("Madhu");
			e4.setDeptno(2);
			e4.setSal(30000);
			session.save(e4);
			
			Employee e5 = new Employee();
			e5.setEmpno(2006);
			e5.setEname("Anil");
			e5.setDeptno(3);
			e5.setSal(35000);
			session.save(e5);
			
			
			
			Employee e6 = new Employee();
			e6.setEmpno(2007);
			e6.setEname("Kishore");
			e6.setDeptno(3);
			e6.setSal(24000);
			session.save(e6);
			
			Employee e7 = new Employee();
			e7.setEmpno(2008);
			e7.setEname("Left join");
			e5.setDeptno(2);
			e7.setSal(21000);
			session.save(e7);
			
			
			Employee e8 = new Employee();
			e8.setEmpno(2009);
			e8.setEname("jhon");
			e5.setDeptno(3);
			e8.setSal(23000);
			session.save(e8);
			
			tx.commit();
			
		} catch (HibernateException he) {
			he.printStackTrace();
			tx.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			// Close the session
			session.close();  
			System.out.println("Employee and department Details saved");
		}
	}
}
