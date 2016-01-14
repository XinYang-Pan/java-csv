package com.blueo.common;

import java.beans.PropertyDescriptor;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

import com.google.common.base.Defaults;
import com.google.common.base.Objects;

public class CodeGenerator {
	
	public static void generateSetting(Class<?> clazz) {
		generateSetting(clazz, (String)null);
	}
	
	public static void generateSetting(Class<?> clazz, String paramName) {
		BeanWrapper instance = new BeanWrapperImpl(clazz);
		PropertyDescriptor[] pds = instance.getPropertyDescriptors();
		paramName = ObjectUtils.firstNonNull(paramName, clazz.getSimpleName().toLowerCase());
		String clazzName = clazz.getSimpleName();
		System.out.println(String.format("%s %s = new %s();", clazzName, paramName, clazzName));
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null) {
				System.out.println(String.format("%s.set%s(%s);", paramName, StringUtils.capitalize(pd.getName()), Defaults.defaultValue(pd.getPropertyType())));
			}
		}
	}

	public static void generateSetting(Class<?> set, Class<?> get) {
		generateSetting(set, get, null, null);
	}
	
	public static void generateSetting(Class<?> set, Class<?> get, String setParamName, String getParamName) {
		BeanWrapper setInstance = new BeanWrapperImpl(set);
		BeanWrapper getInstance = new BeanWrapperImpl(get);
		PropertyDescriptor[] pds = setInstance.getPropertyDescriptors();
		getParamName = ObjectUtils.firstNonNull(getParamName, get.getSimpleName().toLowerCase());
		setParamName = ObjectUtils.firstNonNull(setParamName, set.getSimpleName().toLowerCase());
		if (Objects.equal(getParamName, setParamName)) {
			getParamName = getParamName + "1";
		}
		String setClazzName = set.getSimpleName();
		System.out.println(String.format("%s %s = new %s();", setClazzName, setParamName, setClazzName));
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null) {
				String propertyName = pd.getName();
				PropertyDescriptor getPd = getInstance.getPropertyDescriptor(propertyName);
				Object value;
				if (getPd == null) {
					value = Defaults.defaultValue(pd.getPropertyType());
				} else {
					value = String.format("%s.get%s()", getParamName, StringUtils.capitalize(getPd.getName()));
				}
				System.out.println(String.format("%s.set%s(%s);", setParamName, StringUtils.capitalize(propertyName), value));
			}
		}
	}
}
