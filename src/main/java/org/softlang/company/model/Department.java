package org.softlang.company.model;

import java.util.LinkedList;
import java.util.List;

/**
 * A department has a name, a manager,
 * employees, and subdepartments.
 */

public class Department {
	
	private String name;
	private Employee manager;
	private List<Department> subdepts;
	private List<Employee> employees;
	
	public Department() {
		subdepts = new LinkedList<>();
		employees = new LinkedList<>();
	}
	
	public Department(String name) {
		subdepts = new LinkedList<>();
		employees = new LinkedList<>();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Employee getManager() {
		return manager;
	}
	
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	
	public List<Department> getSubdepts() {
		return subdepts;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void addDepartment(Department dep) {
		subdepts.add(dep);
	}
	public void addDepartment(Department ... deps) {
		for (Department dep : deps)
			addDepartment(dep);
	}
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}
	public void addEmployee(Employee ... emps) {
		for (Employee emp : emps)
			addEmployee(emp);
	}
}
