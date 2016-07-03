package com.hibernate.hql;

import java.io.Serializable;

public class DeptEmpDetails implements Serializable {
	private String dname,loc, empName;
	private double sal;
	

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public DeptEmpDetails(String dname, String loc, String empName, double sal) {
		this.dname = dname;
		this.loc = loc;
		this.empName = empName;
		this.sal = sal;
	}
	public DeptEmpDetails(){}

}
