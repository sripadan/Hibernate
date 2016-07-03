package com.hibernate.hql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Example2_HQLWherAndJoins {

	
	private SessionFactory sessionFactory = null;
	public static void main (String[] args) {
		
		Example2_HQLWherAndJoins testCase = new Example2_HQLWherAndJoins();
		
		// 1. Prepare Configuration object the 
		Configuration config =  new Configuration(); 
		config.configure();
		
		// 2. Build SessionFactory
		testCase.sessionFactory = config.buildSessionFactory();

		// Creating a new persistent state
		testCase.whereExamples();
		
		testCase.joinExamples();
	}
	
	public void whereExamples(){
		Transaction  tx = null;
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();
			// start a new transaction
			tx = session.beginTransaction(); 
			
			// Example with Where clause
			Query q1  = session.createQuery("from Employee AS e where e.deptno=1");
			List<Employee> resList = q1.list();
			
			System.out.println("Empno\tEname\tSal\t\tDeptno");
			for(Employee employee:resList)
			{
				System.out.print(employee.getEmpno()+"\t");
				System.out.print(employee.getEname()+"\t");
				System.out.print(employee.getSal()+"\t\t");
				System.out.print(employee.getDeptno()+"\t");
				System.out.println();
			}
			
			// Where condition and select department
			Query q2  = session.createQuery("select d from Dept d where d.deptno = 1");
			List<Dept> resList2 = q2.list();
			for(Dept dept:resList2) 	{
				System.out.print(dept.getDeptno()+"\t");
				System.out.println(dept.getDname());
				System.out.println();
			}
			
			
			// Where condition with LIKE clause
			Query q3  = session.createQuery("select d from Dept d where d.dname LIKE 'I%'");
			List<Dept> resList3 = q3.list();
			for(Dept dept:resList3) 	{
				System.out.print(dept.getDeptno()+"\t");
				System.out.println(dept.getDname());
				System.out.println();
			}
			
			// Order by clause
			Query q4  = session.createQuery("select d from Dept d ORDER BY d.dname desc");
			List<Dept> resList4 = q4.list();
			for(Dept dept:resList4) 	{
				System.out.print(dept.getDeptno()+"\t");
				System.out.println(dept.getDname());
				System.out.println();
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();
			tx.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			// Close the session
			session.close();  
		}
	}

	public void joinExamples(){
		Session session = null;
		try {
			// 3. Obtain a session
			session   = sessionFactory.openSession();
			
			// Example with JOIN
			Query q  = session.createQuery("from Dept d JOIN d.employees e");
			List<Object[]> results = q.list();
			
			System.out.println("Empno\tEname\tSal\t\tDeptno\tDepartmentName");
			for(Object[] result:results)
			{
				Dept dept = (Dept)result[0];
				Employee employee = (Employee)result[1];
				if(employee != null){
					System.out.print(employee.getEmpno()+"\t");
					System.out.print(employee.getEname()+"\t");
					System.out.print(employee.getSal()+"\t\t");
				}
				if(dept != null){
					System.out.print(dept.getDeptno()+"\t");
					System.out.println(dept.getDname());
				}
				System.out.println();
				System.out.println();
			}
			
			// Example with Left join
			Query qL  = session.createQuery("from Dept d LEFT JOIN d.employees e");
			List<Object[]> resultsLeft = qL.list();
			
			System.out.println("---------- Example Left join -----------");
			System.out.println("Empno\tEname\tSal\t\tDeptno\tDepartmentName");
			for(Object[] result:resultsLeft)
			{
				Dept dept = (Dept)result[0];
				Employee employee = (Employee)result[1];
				if(employee != null){
					System.out.print(employee.getEmpno()+"\t");
					System.out.print(employee.getEname()+"\t");
					System.out.print(employee.getSal()+"\t\t");
				}
				if(dept != null){
					System.out.print(dept.getDeptno()+"\t");
					System.out.println(dept.getDname());
				}
				System.out.println();
				System.out.println();
			}
			
			//Cartesian product of queried objects
			
			Query q2  = session.createQuery("from Dept d, Employee e");
			List<Object[]> resultLists = q2.list();
			
			System.out.println("Empno\tEname\tSal\t\tDeptno\tDepartmentName");
			for(Object[] result:resultLists)
			{
				Dept dept = (Dept)result[0];
				Employee employee = (Employee)result[1];
				if(employee != null){
					System.out.print(employee.getEmpno()+"\t");
					System.out.print(employee.getEname()+"\t");
					System.out.print(employee.getSal()+"\t\t");
				}
				if(dept != null){
					System.out.print(dept.getDeptno()+"\t");
					System.out.println(dept.getDname());
				}
				System.out.println();
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
