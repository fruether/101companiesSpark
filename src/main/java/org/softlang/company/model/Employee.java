package org.softlang.company.model;

import java.io.Serializable;

/**
 * An employee has a name, an address, and a salary.
 */

public class Employee implements Serializable {
	
	private String name;
	private String address;
	private double salary;
	
	public Employee(String name, double salary) {
		this.salary = salary;
		this.name = name;
	}
	public Employee(String name, double salary, String address) {
		this.salary = salary;
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
}
