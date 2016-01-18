package com.blueo.common;

import java.util.Iterator;

import org.springframework.util.Assert;

public abstract class BlueoUtils {
	
	public static <T> T oneOrNull(Iterable<T> iterable) {
		if (iterable == null) {
			return null;
		}
		// empty
		if (!iterable.iterator().hasNext()) {
			return null;
		}
		return oneNoNull(iterable);
	}
	
	public static <T> T oneNoNull(Iterable<T> iterable) {
		// validate not null
		Assert.notNull(iterable);
		// validate not empty
		Iterator<T> iterator = iterable.iterator();
		Assert.isTrue(iterator.hasNext());
		// Get value
		T t = iterator.next();
		// validate has more than 1 value
		Assert.isTrue(!iterator.hasNext());
		return t;
	}
	
	public static RuntimeException internalError(String message) {
		return new RuntimeException(message);
	}
	
	public static IllegalArgumentException illegalArgument(String message) {
		return new IllegalArgumentException(message);
	}
	
}
