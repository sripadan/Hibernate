package com.hibernate.hql;

public interface MyQueries {
	
	String GET_ALL_EMPLOYEES= "from Employee";
	String GET_DEPT_BY_NAME = "select d.dname,d.loc from Dept d where d.dname LIKE :deptnamePattern";
	
}
