package com.hibernate.hql;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example7_HQLNativeSQLQuery {
	
	private SessionFactory sessionFactory = null;
	public static void main (String[] args) {

		Example7_HQLNativeSQLQuery testCase = new Example7_HQLNativeSQLQuery();

		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();

		// 2. Build SessionFactory
		testCase.sessionFactory = config.buildSessionFactory();
		testCase.nativeQueryExample();
	}

	public void nativeQueryExample(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			//SQLQuery q1  = session.createSQLQuery("SELECT * FROM CATS").addEntity(Dept.class);
			//sess.createSQLQuery("SELECT ID, NAME, BIRTHDATE FROM CATS").addEntity(Cat.class);
			
			SQLQuery q1  = session.createSQLQuery("SELECT * FROM dept")
			 .addScalar("deptno", Hibernate.LONG)
			 .addScalar("dNAME",  Hibernate.STRING)
			 .addScalar("loc",  Hibernate.STRING);
			 
			List<Object[]> results = q1.list();
			System.out.println("DepartmentName\tLocation");
			for(Object[] dept: results) {
				if(dept != null && dept.length >=2 ){
					System.out.print(dept[0]+"\t");
					System.out.print(dept[1]);
					System.out.println();
				}
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
