package org.softlang.operations;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import java.util.*;
import org.apache.spark.sql.functions.*;
import scala.collection.mutable.WrappedArray;

/**
 * Created by freddy on 15.04.17.
 */
public class Total {
  private SparkSession spark;
  private String path = "files/sampleCompany.json";

  public void read_company_file(String path) {
      Dataset<Row> company = spark.read().json(path).toDF();
      company.createOrReplaceTempView("company");
      company.printSchema();
  }
    public List<Object> getSalaries(List<Row> empRow) {
        List<Object> salaries = new ArrayList<>();

        for (Row row : empRow) {
            if (row != null && !row.anyNull()) {
                for(int i = 0; i < row.length(); i++) {
                    salaries.addAll( row.getList(i));
                }
            }
        }
        return salaries;
    }


  public double total(){
      read_company_file("files/sampleCompany.json");
      Dataset<Row> managerSallaries = spark.sql("Select departments.manager.salary from company");
      Dataset<Row> departments = spark.sql("Select departments.departments from company").toDF();

      Dataset<Row> employees = departments.select(org.apache.spark.sql.functions.explode(departments.col("departments")).as("dep")).select("dep.employees");
      Dataset<Row> emp_salary= employees.select(org.apache.spark.sql.functions.explode(employees.col("employees")).as("empl")).select("empl.salary");
      Dataset<Row> manager_salary= departments.select(org.apache.spark.sql.functions.explode(departments.col("departments")).as("dep")).select("dep.manager.salary");

      List<Row> rows = managerSallaries.collectAsList();;
      List<Object> salaries = getSalaries(emp_salary.collectAsList());
      salaries.addAll(getSalaries(manager_salary.collectAsList()));
      salaries.addAll(getSalaries(managerSallaries.collectAsList()));


      return calculateTotal(salaries);
  }

  public double calculateTotal(List<Object> salaries){
      double total = 0.0;
      for(Object salary : salaries) {
          total +=  Double.parseDouble(salary.toString());
      }
      return total;
  }

  public Total() {
       spark = SparkSession
              .builder()
              .appName("Java Spark SQL basic ")
              .config("spark.master", "local")
              .getOrCreate();

  }
    public Total(String path) {
        spark = SparkSession
                .builder()
                .appName("Java Spark SQL basic ")
                .config("spark.master", "local")
                .getOrCreate();

        this.path = path;
    }

    public static void main(String [] args)  {

       System.out.print(new Total().total());

  }

}
