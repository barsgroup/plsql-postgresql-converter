package ru.barsopen.plsqlconverter.util;

public class ReflectionUtil {
	
	public static Object callMethod(Object obj, String methodName) {
		try {
			Object result = obj.getClass().getMethod(methodName).invoke(obj);
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
