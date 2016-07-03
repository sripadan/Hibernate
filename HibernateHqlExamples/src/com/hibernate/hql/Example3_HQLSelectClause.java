package com.hibernate.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Example3_HQLSelectClause {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		// Example with Where clause
		Query q1  = session.createQuery("select d.dname, d.loc from Dept d");
		List<Object[]> resList = q1.list();
		
		System.out.println("Dept Name\tLocation");
		for(Object[] deptProperties:resList)
		{
			if(deptProperties != null){
				System.out.print(deptProperties[0]+"\t");
				System.out.println(deptProperties[1]);
			}
			System.out.println();
		}
		session.close();
	}

}
