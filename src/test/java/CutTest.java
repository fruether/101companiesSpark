import org.apache.commons.collections.iterators.EmptyOrderedIterator;
import org.junit.Before;
import org.junit.Test;
import org.softlang.company.model.*;
import org.softlang.operations.Cut;

import static junit.framework.Assert.assertEquals;

/**
 * Created by freddy on 01.05.17.
 */
public class CutTest {
	Company comp;
	Employee ralf;
	Employee peter;
	
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
		
		ralf = new Employee("Ralf", 4711);
		peter = new Employee("Peter", 2222);
		
		dev2.addEmployee(ralf, peter);
	}
	
	@Test
	public void testCut() {
		Cut c = new Cut(2);
		c.cut(comp);
		
		assertEquals(ralf.getSalary(), 2355.5, 0.1);
		assertEquals(peter.getSalary(), 1111.0, 0.1);
		assertEquals(comp.getDepts().get(0).getManager().getSalary(), 44444.0, 0.1);
		
		
	}
}
