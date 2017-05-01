import org.junit.Before;
import org.junit.Test;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;
import org.softlang.operations.Total;

import static junit.framework.Assert.assertEquals;

/**
 * Created by freddy on 01.05.17.
 */
public class TotalTest {
	Company comp;
	@Before
	public void setUp() {
		
		comp = new Company("ACME");
		
		Department research = new Department("Research");
		Department development = new Department("Developement");
		comp.addDepartment(research, development);
		
		Department dev1 = new Department("dev1");
		Department dev2 = new Department("dev2");
		
		development.addDepartment(dev1, dev2);
		
		research.setManager(new Employee("Fred", 88888));
		development.setManager(new Employee("Marie", 77777));
		
		dev1.setManager(new Employee("Bob", 77776));
		dev2.setManager(new Employee("Alice", 77775));
		
		Employee ralf = new Employee("Ralf", 4711);
		Employee peter = new Employee("Peter", 2222);
		
		dev2.addEmployee(ralf, peter);
		
	}
	
	@Test
	public void testTotal() {
		Total t = new Total();
		double total = t.total(comp);
		assertEquals(329149, total, 0);
	}
	
}
