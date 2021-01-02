package scheduleGenerator.testing;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import scheduleGenerator.storage.scheduleItem;

class scheduleItemTest {
	String name1 = "test";
	String desc1 = "Still a test";
	LocalDate date1 = LocalDate.of(2020, 1, 21);
	LocalTime time1 = LocalTime.of(4, 12);
	String toStringTest1 = "test 2020-01-21 04:12 Still a test";
	scheduleItem test1 = new scheduleItem(name1, desc1, date1, time1);
	
	String name2 = "test2";
	String desc2 = "Still a test2";
	LocalDate date2 = LocalDate.now();
	LocalTime time2 = LocalTime.now();
	String toStringTest2 = "test2 " + date2 + " " + time2 + " Still a test2"; 
	scheduleItem test2 = new scheduleItem(name2, desc2, date2, time2);
	
	@Test
	public void testgetName() {
		Assert.assertEquals(name1, test1.getName());
		Assert.assertEquals(name2, test2.getName());
		Assert.assertNotEquals(test1.getName(), test2.getName());
	}
	
	@Test
	public void testGetDescription() {
		Assert.assertEquals(desc1, test1.getTaskDescription());
		Assert.assertEquals(desc2, test2.getTaskDescription());
		Assert.assertNotEquals(test1.getTaskDescription(), test2.getTaskDescription());
	}
	
	@Test
	public void testGetDate() {
		Assert.assertEquals(date1, test1.getCompletionDate());
		Assert.assertEquals(date2, test2.getCompletionDate());
		Assert.assertNotEquals(test1.getCompletionDate(), test2.getCompletionDate());
	}
	
	@Test
	public void testGetTime() {
		Assert.assertEquals(time1, test1.getCompletionTime());
		Assert.assertEquals(time2, test2.getCompletionTime());
		Assert.assertNotEquals(test1.getCompletionTime(), test2.getCompletionTime());
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals(toStringTest1, test1.toString());
		Assert.assertEquals(toStringTest2, test2.toString());
		Assert.assertNotEquals(test1.toString(), test2.toString());
	}
}
