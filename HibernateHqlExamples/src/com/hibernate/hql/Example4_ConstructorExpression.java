package com.hibernate.hql;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//Example for Constructor Expression in select
// And also Multiple scalar values using custom value object

public class Example4_ConstructorExpression {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		Query q  = session.createQuery
					("select new com.hibernate.hql.DeptEmpDetails(d.dname,d.loc,e.ename,e.sal) from Dept d JOIN d.employees e");
		List<DeptEmpDetails> results = q.list();
		
		System.out.println("DeptName\tLocation\tEmpName\tSalary");
		for(DeptEmpDetails deptEmpDetails:results)
		{
			if(deptEmpDetails != null){
				System.out.print(deptEmpDetails.getDname()+"\t");
				System.out.print(deptEmpDetails.getLoc()+"\t");
				System.out.print(deptEmpDetails.getEmpName()+"\t");
				System.out.print(deptEmpDetails.getSal());
				System.out.println();
			}
		}
		session.close();
	}
}

