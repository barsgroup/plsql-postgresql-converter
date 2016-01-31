package ru.barsopen.plsqlconverter.util;

import java.lang.reflect.Method;

public class ReflectionUtil {
	
	public static Object callMethod(Object obj, String methodName) {
		try {
			Object result = obj.getClass().getMethod(methodName).invoke(obj);
			return result;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static Object callStaticMethod(Class<?> klass, String methodName, Object... args) {
		try {
			Method[] methods = klass.getMethods();
			Method requestedMethod = null;
			for (Method method: methods) {
				if (method.getName().equals(methodName)) {
					requestedMethod = method;
				}
			}
			Object result = requestedMethod.invoke(null, args);
			return result;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static Object getField(Object obj, String fieldName) {
		try {
			Object result = obj.getClass().getField(fieldName).get(obj);
			return result;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
