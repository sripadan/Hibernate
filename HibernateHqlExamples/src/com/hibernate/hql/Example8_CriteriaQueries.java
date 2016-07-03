package com.hibernate.hql;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

// http://docs.jboss.org/hibernate/core/3.3/reference/en/html/querycriteria.html

public class Example8_CriteriaQueries {

	private SessionFactory sessionFactory = null;
	public static void main (String[] args) {

		Example8_CriteriaQueries testCase = new Example8_CriteriaQueries();

		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();

		// 2. Build SessionFactory
		testCase.sessionFactory = config.buildSessionFactory();

		// Creating a new persistent state
		testCase.basicExample();

		testCase.criteriaWithRestriction();
		
		testCase.criteriaWithMultipleRestrictions();
		
		testCase.criteriaWithSingleProjections();
		
		testCase.criteriaWithMultipleProjections();
		
		testCase.criteriaWithOrderBy();
		
		testCase.criteriaSubQuery();
		
		testCase.criteriaWithJoins();
		
	}

	public void basicExample(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class);
			List<Dept> depts = c.list();
			System.out.println("DeptName \t Location");
			for(Dept dept:depts)
			{
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
	
	public void criteriaWithRestriction(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();


			Criteria c = session.createCriteria(Dept.class);
			c.add(Restrictions.like("dname", "I%"));
			List<Dept> depts = c.list();
			System.out.println("----- Criteria With Restriction --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:depts)
			{
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
	
	
	public void criteriaWithMultipleRestrictions(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class);
			c.add(Restrictions.and(
					Restrictions.eq("loc", "Mumbai"),Restrictions.eq("deptno", new Integer(4))));
			List<Dept> depts = c.list();
			
			System.out.println("----- Criteria With Multiple Restriction --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:depts)
			{
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
				System.out.println();
			}
			
			
			// Or condition
			Criteria cOr = session.createCriteria(Dept.class);
			cOr.add(Restrictions.or(
					Restrictions.eq("loc", "Mumbai"),Restrictions.eq("deptno", new Integer(2))));
			List<Dept> deptsList = cOr.list();
			
			System.out.println("----- Criteria With Or Condition --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:deptsList)
			{
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
	
	
	public void criteriaWithSingleProjections(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class);
			c.setProjection(Projections.property("dname"));
			//c.setProjection(Projections.property("deptno"));
			
			List<String> depts = c.list();
			System.out.println("----- Criteria With Single Projections --------");
			System.out.println("DeptName");
			for(String deptName:depts)
			{
				System.out.print(deptName);
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
	
	public void criteriaWithMultipleProjections(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class);
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("dname"));
			projectionList.add(Projections.property("loc"));
			c.setProjection(projectionList);

			/*c.setProjection(Projections.projectionList()
					.add(Projections.property("dname"))
					.add(Projections.property("loc")));*/
			
			List<Object[]> depts = c.list();
			System.out.println("----- Criteria With Multiple Projections --------");
			System.out.println("DeptName \t Location");
			for(Object[] dept:depts){
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
	
	public void criteriaWithOrderBy(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class);
			c.addOrder(Order.desc("dname"));
			c.addOrder(Order.asc("loc"));
			
			List<Dept> depts = c.list();
			System.out.println("----- Criteria With Order By --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:depts) {
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
	
	public void criteriaSubQuery(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			// Prepare a detached query
			DetachedCriteria dc =  DetachedCriteria.forClass(Employee.class);
			dc.setProjection(Projections.property("deptno"));
			
			// Prepare outer query/criteria
			Criteria c = session.createCriteria(Dept.class);
			c.add(Subqueries.in("deptno", dc));
			
			List<Dept> depts = c.list();
			System.out.println("----- Criteria With Order By --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:depts) {
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
	
	
	public void criteriaWithJoins(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();

			Criteria c = session.createCriteria(Dept.class, "d");
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("d.dname"));
			projectionList.add(Projections.property("d.loc"));
			c.setProjection(projectionList);
			
			c.createAlias("d.employees", "e");
			c.add(Restrictions.like("deptno", "1"));
			//c.add(Restrictions.gt("e.sal", "15000"));
			
			List<Dept> depts = c.list();
			System.out.println("----- Criteria With Joins --------");
			System.out.println("DeptName \t Location");
			for(Dept dept:depts) {
				System.out.print(dept.getDname()+"\t");
				System.out.print(dept.getLoc());
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
