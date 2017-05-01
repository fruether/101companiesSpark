package org.softlang.operations;

import org.apache.spark.api.java.JavaRDD;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by freddy on 01.05.17.
 */
public class Cut extends SparkOperation implements Serializable {
	private List<Employee> allEmployees;
	private Map<String, Employee> nameToEmp;
	private int amount;
	
	public Cut(int amount) {
		super();
		allEmployees = new ArrayList<>();
		this.amount = amount;
		nameToEmp = new HashMap<String, Employee>();
	}
	
	public void cut(Company c) {
		double total = 0;
		for (Department d : c.getDepts())
			getEmployees(d);
		
		JavaRDD<Employee> employeeRdd = jsc.parallelize(allEmployees);
		createHashMap();
		
		JavaRDD<Employee> employeeSalaries = employeeRdd.map(x -> Cut.cutSalary(x, amount));
		employeeSalaries.collect().forEach(x -> this.updateSalary(x));
	}
	
	public void getEmployees(Department d) {
		
		allEmployees.add(d.getManager());
		for (Department sub : d.getSubdepts())
			getEmployees(sub);
		allEmployees.addAll(d.getEmployees());
		
	}
	
	public static Employee cutSalary(Employee e, int n) {
		double salary = e.getSalary();
		double cutSalary  = salary/n;
		e.setSalary(cutSalary);
		return e;
	}
	public void createHashMap() {
		for(Employee emp : allEmployees) {
			nameToEmp.put(emp.getName(), emp);
		}
	}
	
	public void updateSalary(Employee x) {
		Employee old = nameToEmp.get(x.getName());
		old.setSalary(x.getSalary());
	}
}
