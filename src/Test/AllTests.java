package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({FamilyTreeListUnitTest.class, MarriageUnitTest.class, PersonUnitTest.class, PersonPartitionTest.class, XmlParserPartitionTest.class})
public class AllTests {

}
