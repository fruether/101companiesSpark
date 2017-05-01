package org.softlang.operations;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * Created by freddy on 01.05.17.
 */
public class SparkOperation {
	protected SparkSession sparkSession;
	protected SparkContext sc;
	protected JavaSparkContext jsc;
	
	
	public SparkOperation() {
		sparkSession = SparkSession
				.builder()
				.appName("Java Spark SQL basic ")
				.config("spark.master", "local")
				.getOrCreate();
		sc = sparkSession.sparkContext();
		jsc = new JavaSparkContext(sc);
		
	}
}
