package Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import Third.Marriage;
import Third.Person;

import org.junit.Test;

public class MarriageUnitTest {

	@Test(expected=IllegalArgumentException.class)
	public void testMarriage_Husband() {
		Person husband = new Person(1, Person.FEMALE);
		Person wife = new Person(2, Person.FEMALE);
		
		Marriage marriage = new Marriage(husband, wife);
		
		assertEquals("Husband gender must be MALE", husband.getGender(), Person.MALE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMarriage_Wife() {
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.MALE);
		
		Marriage marriage = new Marriage(husband, wife);
		marriage.setLocation("New York");
		marriage.setDate(new Date("Jul-12-2015"));
		marriage.toString();
		
		assertEquals("Wife gender must be FEFMALE", husband.getGender(), Person.MALE);
	}
	
	@Test
	public void testMarriage_HusbandMarriaged() {
		Person husband = new Person(1, Person.MALE);
		Person wife1 = new Person(2, Person.FEMALE);
		Person wife2 = new Person(3, Person.FEMALE);
		
		Marriage marriage1 = new Marriage(husband, wife1);
		Marriage marriage2 = new Marriage(husband, wife2);
		
		husband.addMarriage(marriage1);
		husband.addMarriage(marriage2);
		
		assertFalse("Husband already married with another", husband.getMarriages().toArray().length > 1);
	}
	
	@Test
	public void testMarriage_WifeMarriaged() {
		Person husband1 = new Person(1, Person.MALE);
		Person husband2 = new Person(2, Person.MALE);
		Person wife = new Person(3, Person.FEMALE);
		
		Marriage marriage1 = new Marriage(husband1, wife);
		Marriage marriage2 = new Marriage(husband1, wife);
		
		wife.addMarriage(marriage1);
		wife.addMarriage(marriage2);
		
		assertFalse("Wife already married with another", wife.getMarriages().toArray().length > 1);
	}
	
	@Test
	public void testMarriage_Sibling() {
		Person father = new Person(1, Person.MALE);
		Person husband = new Person(2, Person.MALE);
		Person wife = new Person(3, Person.FEMALE);
		
		husband.setFather(father);
		wife.setFather(father);
		Marriage marriage = new Marriage(husband, wife);
		
		assertNotEquals("Siblings cannot be husband and wife", husband.getFatherId(), wife.getFatherId());
	}
	
	@Test
	public void testToString() throws ParseException
	{
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		
		husband.setFirstName("John");
		
		wife.setLastName("Emma");
		
		Marriage marriage = new Marriage(husband, wife);
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		
		marriage.setDate(df.parse("Jul 12, 1969"));
		marriage.setLocation("New York");
		
		assertEquals("Sample marriage information must be John and Emma were married on Sat Jul 12 00:00:00 CST 1969 in New York", 
			marriage.toString(), "John and Emma were married on Sat Jul 12 00:00:00 CST 1969 in New York");
	}
	
	@Test
	public void testGetLocation()
	{
		Person husband = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		
		Marriage marriage = new Marriage(husband, wife);
		assertEquals("Marriage location must be null", null, marriage.getLocation());
		
		marriage.setLocation("New York");
		assertEquals("Marriage location must be New York", "New York", marriage.getLocation());
	}
}
