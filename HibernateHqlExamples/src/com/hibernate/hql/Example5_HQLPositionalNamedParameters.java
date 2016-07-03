package com.hibernate.hql;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Example5_HQLPositionalNamedParameters {

	private SessionFactory sessionFactory = null;
	public static void main (String[] args) {

		Example5_HQLPositionalNamedParameters testCase = new Example5_HQLPositionalNamedParameters();

		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();

		// 2. Build SessionFactory
		testCase.sessionFactory = config.buildSessionFactory();

		// Creating a new persistent state
		testCase.positionalParameterExample();

		testCase.namedParameterExample();
	}

	public void positionalParameterExample(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Query q  = session.createQuery("select d.dname,d.loc from Dept d where d.deptno=?");
			q.setParameter(0, new Integer(20),Hibernate.INTEGER);
			List<Object[]> results = q.list();

			System.out.println("DepartmentName\tLocation");
			for(Object[] dept:results) {
				System.out.print(dept[0]+"\t");
				System.out.print(dept[1]);
				System.out.println();
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// Close the session
			session.close();  
		}
	}

	public void namedParameterExample(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Query q  = session.createQuery ("select d.dname, d.loc from Dept d where d.deptno=:departmentno");
			q.setParameter("departmentno", new Integer(20),Hibernate.INTEGER);
			List<Object[]> results = q.list();

			System.out.println("DepartmentName\tLocation");
			for(Object[] dept:results) {
				System.out.print(dept[0]+"\t");
				System.out.print(dept[1]);
				System.out.println();
			}


		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// Close the session
			session.close();  
		}
	}
}


