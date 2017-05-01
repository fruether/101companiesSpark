package org.softlang.operations;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.SparkSession;
import org.softlang.company.model.*;
import scala.collection.JavaConversions;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freddy on 01.05.17.
 */
public class TotalClass extends SparkOperation{
	private List<Employee> allEmployees;
	
	public TotalClass() {
		super();
		allEmployees = new ArrayList<>();
	}
	
	public double total(Company c) {
		double total = 0;
		for (Department d : c.getDepts())
				total(d);
		
		//Seq<Employee> allEmployeesSeq = JavaConversions.asScalaBuffer(allEmployees).toSeq();
		
		JavaRDD<Employee> employeeRdd =  jsc.parallelize(allEmployees);
		
		JavaRDD<Double> employeeSalaries = employeeRdd.map(x -> x.getSalary());
		System.out.print("I am done");
		employeeSalaries.collect();
		System.out.println("Output: " + employeeSalaries.collect().toString());
		Double salary = employeeSalaries.fold(0.0, (x, y) -> x+y);
		
		return salary;
	}
	
	public void total(Department d) {
		
		allEmployees.add(d.getManager());
		for (Department sub : d.getSubdepts())
			total(sub);
		allEmployees.addAll(d.getEmployees());

	}
}
