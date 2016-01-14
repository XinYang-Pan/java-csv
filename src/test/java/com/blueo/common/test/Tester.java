package com.blueo.common.test;

import com.blueo.common.CodeGenerator;
import com.blueo.csv.test.Person;

public class Tester {

	public static void main(String[] args) {
		CodeGenerator.generateSetting(Person.class, Person.class);
		CodeGenerator.generateSetting(Person.class, Person.class, "p1", "p2");
	}
	
}
