package Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import Third.FamilyTree;
import Third.FamilyTreeException;
import Third.Marriage;
import Third.Parser;
import Third.Person;
import Third.PrettyPrinter;
import Third.XmlParser;
import Third.Person.Gender;

public class XmlParserPartitionTest {

	Document 				doc = null;
	XmlParser 				parser;
	DocumentBuilder 		builder;
	DocumentBuilderFactory 	factory;
	
	public void setPreparation(String path) 
	{
		try{
			XmlParser parser = new XmlParser(path);
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(parser);
			builder.setEntityResolver(parser);
			doc = (Document) builder.parse(new InputSource(parser.reader));
		} catch(ParserConfigurationException ex){
			
		} catch(SAXException ex){
			
		} catch(IOException ex){
			
		}
	}
	
	private void extractDate(Element root){

	    NodeList children = root.getChildNodes();
	    
	    assertTrue("One of year, month and day node is missing", children.getLength() == 3);
	    assertTrue("Node order must be Month, Day and Year", children.item(0).getNodeName().equals("month")
	    		&& children.item(1).getNodeName().equals("day") && children.item(2).getNodeName().equals("year"));
	}
	
	public void parse(String path){
		
		String extension = path.substring(path.lastIndexOf('.') + 1);
		assertEquals("File extension must be xml", extension, "xml");
		
		setPreparation(path);
		
		Element root = (Element) doc.getChildNodes().item(1);
	    assertEquals("Root node must be family-tree", root.getNodeName(), "family-tree");

	    NodeList stuff = root.getChildNodes();
		
	    for (int i = 0; i < stuff.getLength(); i++) {
	    	
	    	Node node = stuff.item(i);

	    	if (!(node instanceof Element)) {
	    		// Ignore whitespace text and other stuff
	    		continue;
	    	}

	    	Element element = (Element) node;

	    	if (element.getNodeName().equals("person")) {
	    		handlePerson(element);

	    	} else if (element.getNodeName().equals("marriage")) {
	    		handleMarriage(element);

	    	} else {
	    		
	    		String s = "A family tree should not have a " + element.getNodeName();
	    		fail(s);
	    		throw new FamilyTreeException(s);
	    		
	    	}
	    }
	}
	
	
	private void handlePerson(Element root) throws FamilyTreeException {

		int 		id;
		Person 		person = null;
	    
		assertFalse("ID and Gender field must be specified", root.getAttribute("id").equals("") || root.getAttribute("gender").equals(""));

	    NodeList elements = root.getChildNodes();
	    for (int i = 0; i < elements.getLength(); i++) {
	    	
	    	Node node = elements.item(i);
	    	if (!(node instanceof Element)) {
	    		continue;
	    	}

	    	Element element = (Element) node;

	    	String str = element.getNodeName();
	    	String substr = str.substring(element.getNodeName().indexOf('-') + 1);
	    	
	    	if (substr.equals("name"))
		    	assertTrue("Node of person's name must be first-name, middle-name or last-name", 
		    		str.equals("first-name") || str.equals("middle-name") || str.equals("last-name"));
	    	else if (substr.equals("id"))
	    		assertTrue("Node of person's father and mother id must be father-id or mother-id", 
			    		str.equals("father-id") || str.equals("mother-id"));
	    	else if (substr.equals(""))
	    	{
	    		assertTrue("Node of person's birth and death date must be dob and dod", 
			    		str.equals("dob") || str.equals("dod"));
	    		
	    		Element date = null;
	    		NodeList list = element.getChildNodes();
		        for (int j = 0; j < list.getLength(); j++) {
		        	Node n = list.item(j);
		        	if (n instanceof Element) {
		        		date = (Element) n;
		        		break;
		        	}
		        }
		        
		        if (date == null) {
		        	fail("No <date> in date node");
		        	throw new FamilyTreeException("No <date> in date node?");
		        }
		        
		        extractDate(date);
	    	}
	    } 
	}
	  
	  
	private void handleMarriage(Element root) throws FamilyTreeException {

	    assertFalse("Husband-id field and Wife-id field must be specified", root.getAttribute("husband-id").equals("") || root.getAttribute("wife-id").equals(""));

	    NodeList elements = root.getChildNodes();
	    
	    int locationExist = 0;
	    
	    for (int i = 0; i < elements.getLength(); i++) {
	    	
	    	Node node = elements.item(i);
	    	
	    	if (!(node instanceof Element)) {
		        continue;
	    	}
	
	    	Element element = (Element) node;
	    	
	    	if (element.getNodeName().equals("location"))
	    		locationExist = 1;
	    }
	    
	    assertFalse("Marriage location must be specified", locationExist == 0);
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase1()
	{
		parse("testcase1.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase474()
	{
		parse("testcase474.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase847()
	{
		parse("testcase847.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase1253()
	{
		parse("testcase1253.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase1681()
	{
		parse("testcase1681.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase2032()
	{
		parse("testcase2032.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase2450()
	{
		parse("testcase2450.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase2811()
	{
		parse("testcase2811.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase3295()
	{
		parse("testcase3295.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase3635()
	{
		parse("testcase3635.xml");
	}
	
	@Test(expected=Exception.class)
	public void test_TestCase4096()
	{
		parse("testcase4096.xml");
	}
	
	@Test
	public void test_TestCase4414()
	{
		parse("testcase4414.html");
	}
	
	@Test
	public void test_TestCase4897()
	{
		parse("testcase4897.html");
	}
	
	@Test
	public void test_TestCase5271()
	{
		parse("testcase5271.html");
	}
	
	@Test
	public void test_TestCase5655()
	{
		parse("testcase5655.html");
	}
	
	@Test
	public void test_TestCase6094()
	{
		parse("testcase6094.html");
	}
	
	@Test
	public void test_TestCase6489()
	{
		parse("testcase6489.html");
	}
	
	@Test
	public void test_TestCase6807()
	{
		parse("testcase6807.html");
	}
	
	@Test
	public void test_TestCase7236()
	{
		parse("testcase7236.html");
	}
	
	@Test
	public void test_TestCase7674()
	{
		parse("testcase7674.html");
	}
	
	public static void main(String[] args)
	{
		//parse("testcase7674.html");
	}

}
