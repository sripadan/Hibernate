package com.hibernate.hql.bulkupdate;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestHqlBulkUpdate {

	/**
	 * This example is for Bulk updates and Deletes 
	 * Using HQL
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx=session.beginTransaction();
		Query q = session.createQuery("UPDATE Dept as d SET d.dname=:dname where deptno =:deptno ");
		q.setParameter("dname", "d3");
		q.setParameter("deptno", new Long(4));
		int count = q.executeUpate();
		tx.commit();
		System.out.println(count +" number of records updated.");
		session.close();
	}

}
