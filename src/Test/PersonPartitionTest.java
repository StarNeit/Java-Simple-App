package Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.rmi.*;

import org.junit.Test;

import Third.FamilyTreeException;
import Third.Person;
import Third.Person.Gender;

public class PersonPartitionTest {
	
	public boolean isAlphabetic(String name)
	{
		for (int i = 0; i < name.length(); i++)
		{
			if (Character.isLetter(name.charAt(i)) == false)
				return false;
		}
		return true;
	}

	public void test(Person person) throws ParseException {
		assertTrue("Id must be greater than 1", person.getId() > 0);
		assertFalse("Gender must be Male or Female", person.getGender().equals(Gender.UNKNOWN));
		assertTrue("Name must be alphabetic", isAlphabetic(person.getMiddleName()));
		assertEquals("Last name must be the same as father's last name.", person.getLastName(), "Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");		
		assertTrue("Date of birth must be prior to date of death", person.getDateOfDeath().after(dob));
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase1() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile123");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase2() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile123");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase3() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase4() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase5() throws ParseException{
		Person person = new Person(0, Gender.UNKNOWN);
		person.setMiddleName("Michile123");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase6() throws ParseException{
		Person person = new Person(0, Gender.UNKNOWN);
		person.setMiddleName("Michile123");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase7() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase8() throws ParseException{
		Person person = new Person(0, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase9() throws ParseException{
		Person person = new Person(1, Gender.MALE);
		person.setMiddleName("Michile123");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		
		assertTrue("Id must be greater than 1", person.getId() > 0);
		assertFalse("Gender must be Male or Female", person.getGender().equals(Gender.UNKNOWN));
		isAlphabetic(person.getMiddleName());
		assertFalse("Name must be alphabetic", isAlphabetic(person.getMiddleName()));
		assertNotEquals("Last name must be the same as father's last name.", person.getLastName(), "Michile");
		
		//DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		//Date dob = df.parse("Jul 12, 1969");		
		assertTrue("Date of birth must be prior to date of death", person.getDateOfDeath().after(dob));
		//test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase10() throws ParseException{
		Person person = new Person(1, Gender.MALE);
		person.setMiddleName("Michile123");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase11() throws ParseException{
		Person person = new Person(1, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase12() throws ParseException{
		Person person = new Person(1, Gender.MALE);
		person.setMiddleName("Michile");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase13() throws ParseException{
		Person person = new Person(1, Gender.UNKNOWN);
		person.setMiddleName("Michile123");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase14() throws ParseException{
		Person person = new Person(1, Gender.UNKNOWN);
		person.setMiddleName("Michile123");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase15() throws ParseException{
		Person person = new Person(1, Gender.UNKNOWN);
		person.setMiddleName("Michile");
		person.setLastName("John");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	
	@Test(expected=FamilyTreeException.class)
	public void test_TestCase16() throws ParseException{
		Person person = new Person(1, Gender.UNKNOWN);
		person.setMiddleName("Michile");
		person.setLastName("Michile");
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date dob = df.parse("Jul 12, 1969");
		Date dod = df.parse("Aug 10, 1967");
		person.setDateOfBirth(dob);
		person.setDateOfDeath(dod);
		test(person);
	}
	

}
