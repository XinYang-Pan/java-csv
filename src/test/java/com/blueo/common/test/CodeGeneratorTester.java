package com.blueo.common.test;

import com.blueo.common.CodeGenerator;
import com.blueo.common.Person;

public class CodeGeneratorTester {

	public static void main(String[] args) {
		CodeGenerator.generateSetting(Person.class);
		CodeGenerator.generateSetting(Person.class, Person.class);
		CodeGenerator.generateSetting(Person.class, Person.class, "p1", "p2");
	}
	
}
