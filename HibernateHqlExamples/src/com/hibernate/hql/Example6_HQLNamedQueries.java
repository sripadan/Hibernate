package com.hibernate.hql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example6_HQLNamedQueries {
	
	private SessionFactory sessionFactory = null;
	public static void main (String[] args) {

		Example6_HQLNamedQueries testCase = new Example6_HQLNamedQueries();

		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();

		// 2. Build SessionFactory
		testCase.sessionFactory = config.buildSessionFactory();

		// Creating a new persistent state
		testCase.namedQueryFromInterface();

		testCase.namedQueryFromHBMFile();
	}

	public void namedQueryFromInterface(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			//Named Query from an Interface
			Query q  = session.createQuery(MyQueries.GET_DEPT_BY_NAME);
			q.setParameter("deptnamePattern", "I%");
			List<Object[]> results = q.list();
			
			System.out.println("DepartmentName\tLocation");
			for(Object[] dept:results)
			{
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

	public void namedQueryFromHBMFile(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			//Named Query from HBM XML File
			Query q  = session.getNamedQuery("GetByDeptNumber");
			q.setParameter("departno", 1);
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
