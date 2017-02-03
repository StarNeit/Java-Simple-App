package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Third.FamilyTree;
import Third.FamilyTreeList;
import Third.Person;

public class FamilyTreeListUnitTest {

	@Test(expected=NullPointerException.class)
	public void testFillInList() {
		FamilyTree ft = new FamilyTree();
		Person father = new Person(1, Person.MALE);
		Person mother = new Person(2, Person.FEMALE);
		Person me = new Person(3, Person.MALE);
		
		ft.addPerson(father);
		ft.addPerson(mother);
		ft.addPerson(me);
		
		FamilyTreeList ftl = new FamilyTreeList();
		ftl.fillInList(ft);
		
		FamilyTree nulltree = null;
		ftl.fillInList(nulltree);
		
		assertTrue("Parameter must not be null", nulltree.equals(null));
	}

	@Test
	public void testSetSelectedPerson() {
		FamilyTree ft = new FamilyTree();
		Person father = new Person(1, Person.MALE);
		Person mother = new Person(2, Person.FEMALE);
		Person me = new Person(3, Person.MALE);
		Person other = new Person(4, Person.MALE);
		
		ft.addPerson(father);
		ft.addPerson(mother);
		ft.addPerson(me);
		
		FamilyTreeList ftl = new FamilyTreeList();
		
		ftl.fillInList(ft);
		ftl.setSelectedPerson(me);
		ftl.setSelectedPerson(other);
	}

}
