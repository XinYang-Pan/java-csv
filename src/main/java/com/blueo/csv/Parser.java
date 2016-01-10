package com.blueo.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

public class Parser {
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> propertyParser(Class<T> clazz, String csvFileLoc) throws IOException {
		List<T> list = new ArrayList<T>();
		List<String> lines = FileUtils.readLines(new File(csvFileLoc));
		Assert.notEmpty(lines);
		// header
		String[] headers = lines.remove(0).split(",");
		int headerCount = headers.length;
		// content
		for (String line : lines) {
			String[] values = line.split(",");
			// each header/value pair
			BeanWrapper instance = new BeanWrapperImpl(clazz);
			for (int i = 0; i < headerCount; i++) {
				instance.setPropertyValue(headers[i], values[i]);
			}
			list.add((T) instance.getWrappedInstance());
		}
		return list;
	}
	
	
}
