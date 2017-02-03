package Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import Third.Marriage;
import Third.Person;
import Third.Person.Gender;
import Third.FamilyTreeException;

import org.junit.Test;

public class PersonUnitTest {

	@Test(expected=FamilyTreeException.class)
	public void testPerson_Id(){
		Person me = new Person(0, Person.MALE);
		assertFalse("Person's ID must be greater than 1", me.getId() < 1);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testPerson_Gender(){
		Person me = new Person(1, Gender.UNKNOWN);
		assertNotEquals("Person's ID must be greater than 1", me.getGender(), Gender.UNKNOWN);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testSetFather_GenderFeMale() {
		Person me = new Person(1, Person.MALE);
		Person father = new Person(2, Person.FEMALE);
		me.setFather(father);
		assertNotEquals("Father's gender must be MALE", Person.MALE, father.getGender());
	}
	
	@Test
	public void testSetFather_Sibiling() {
		Person me = new Person(1, Person.MALE);
		Person brother = new Person(2, Person.MALE);
		Person father = new Person(3, Person.MALE);
		
		me.setFather(father);
		brother.setFather(father);
		
		int my_father = me.getFatherId();
		int brother_father = brother.getFatherId();
		
		me.setFather(brother);
		
		assertNotEquals("Sibling cannot be a father", my_father, brother_father);
	}
	
	@Test
	public void testSetFather_Child() {
		Person me = new Person(1, Person.MALE);
		Person father = new Person(2, Person.MALE);
		
		father.setFather(me);
		me.setFather(father);
		
		assertNotEquals("Child cannot be a father", me.getId(), father.getFatherId());
	}
	
	@Test
	public void testSetFather_Husband() {
		Person me = new Person(1, Person.FEMALE);
		Person father = new Person(2, Person.MALE);
		
		Marriage marriage = new Marriage(father, me);
		me.setFather(father);
		
		assertNotEquals("Husband cannot be a father", marriage.getHusband().getId(), father.getId());
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testSetMother_GenderMale() {
		Person me = new Person(1, Person.MALE);
		Person mother = new Person(2, Person.MALE);
		me.setMother(mother);
		assertNotEquals("Mother's gender must be FEMALE", Person.FEMALE, mother.getGender());
	}
	
	@Test
	public void testSetMother_Sibiling() {
		Person me = new Person(1, Person.MALE);
		Person brother = new Person(2, Person.FEMALE);
		Person mother = new Person(3, Person.FEMALE);
		
		me.setMother(mother);
		brother.setMother(mother);
		
		int my_mother = me.getMotherId();
		int brother_mother = brother.getMotherId();
		
		me.setMother(brother);
		
		assertNotEquals("Sibling cannot be a mother", my_mother, brother_mother);
	}
	
	@Test
	public void testSetMother_Child() {
		Person me = new Person(1, Person.FEMALE);
		Person mother = new Person(2, Person.FEMALE);
		
		mother.setMother(me);
		me.setMother(mother);
		
		assertNotEquals("Child cannot be a mother", me.getId(), mother.getMotherId());
	}
	
	@Test
	public void testSetMother_Wife() {
		Person me = new Person(1, Person.MALE);
		Person mother = new Person(2, Person.FEMALE);
		
		Marriage marriage = new Marriage(me, mother);
		me.setMother(mother);
		
		assertNotEquals("Husband cannot be a mother", marriage.getHusband().getId(), mother.getId());
	}
	
	@Test
	public void testToString() throws ParseException
	{
		Person me = new Person(1, Person.MALE);
		Person mother = new Person(2, Person.FEMALE);
		Person father = new Person(3, Person.MALE);
		
		me.setFather(father);
		me.setMother(mother);
		me.setFirstName("A");
		me.setMiddleName("B");
		me.setLastName("C");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		me.setDateOfBirth(df.parse("Jul 12, 1969"));
		me.setDateOfDeath(df.parse("Mar 9, 2011"));
		System.out.println(me.toString());
		assertEquals("Name string must be Person 1: A B C\nBorn: Sat Jul 12 00:00:00 CST 1969, Died: Wed Mar 09 00:00:00 CST 2011\nMother: , Father: \nMarried 0 times", me.toString(), "Person 1: A B C\nBorn: Sat Jul 12 00:00:00 CST 1969, Died: Wed Mar 09 00:00:00 CST 2011\nMother: , Father: \nMarried 0 times");
	}
	
	@Test
	public void testGetFullName()
	{
		Person me = new Person(1, Person.MALE);
		me.setFirstName("AAA");
		me.setMiddleName("BBB");
		me.setLastName("CCC");
		assertEquals("SampleName must be AAA BBB CCC ", me.getFullName(), "AAA BBB CCC");
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testAddMarriage_Husband()
	{
		Person me = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		Person friend = new Person(3, Person.MALE);
		Person friendwife = new Person(4, Person.FEMALE);
		
		Marriage marriage1 = new Marriage(me, wife);
		Marriage marriage2 = new Marriage(friend, friendwife);
		
		me.addMarriage(marriage1);
		me.addMarriage(marriage2);
		
		assertNotEquals("Marriage is not valid", me, marriage1.getHusband());
		assertNotEquals("Marriage is not valid", me, marriage2.getHusband());
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testAddMarriage_Wife()
	{
		Person me = new Person(1, Person.MALE);
		Person wife = new Person(2, Person.FEMALE);
		Person friend = new Person(3, Person.MALE);
		Person friendwife = new Person(4, Person.FEMALE);
		
		Marriage marriage1 = new Marriage(me, wife);
		Marriage marriage2 = new Marriage(friend, friendwife);
		
		wife.addMarriage(marriage1);
		wife.addMarriage(marriage2);
		
		assertNotEquals("Marriage is not valid", wife, marriage1.getWife());
		assertNotEquals("Marriage is not valid", wife, marriage2.getWife());
	}
	
	@Test(expected=FamilyTreeException.class)
	public void testSetDateOfDeath() throws ParseException
	{
		Person me = new Person(1, Person.MALE);
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Mar 9, 1968");
		
		me.setDateOfBirth(dob);
		me.setDateOfDeath(dod);
		
		assertTrue("Date of death must be after date of birth", dod.after(dob));
	}
	
	@Test
	public void testGetFatherId()
	{
		Person me = new Person(1, Person.MALE);
		Person father = new Person(2, Person.MALE);
		
		assertEquals("Father id must be UNKNOWN", Person.UNKNOWN, me.getFatherId());
		
		me.setFather(father);
		assertEquals("Father id must be 2", 2, me.getFatherId());
	}
	
	@Test
	public void testGetMotherId()
	{
		Person me = new Person(1, Person.MALE);
		Person mother = new Person(3, Person.FEMALE);
		
		assertEquals("Mother id must be UNKNOWN", Person.UNKNOWN, me.getMotherId());
		
		me.setMother(mother);
		assertEquals("Mother id must be 3", 3, me.getMotherId());
	}

}
