package com.blueo.csv.test;

import java.io.IOException;
import java.util.List;

import com.blueo.common.Person;
import com.blueo.csv.Parser;

public class Tester {
	
	public static void main(String[] args) {
		try {
			String fileLocation = Person.class.getResource("simple_test.csv").getFile();
			System.out.println(Person.class.getResource("simple_test.csv"));
			List<Person> personList = Parser.propertyParser(Person.class, fileLocation);
			System.out.println(personList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
