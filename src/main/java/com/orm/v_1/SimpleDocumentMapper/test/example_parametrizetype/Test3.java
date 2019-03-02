package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Test3 {
	
	public static void main(String[] args) {
		try {
			Class<?> c = ContactDao.class;
			Method[] allMethods = c.getDeclaredMethods();
			
			for(Method method: allMethods) {
				
				Type returnType = method.getGenericReturnType();
				System.out.println(returnType);
				
				
				if(returnType instanceof ParameterizedType) {
					ParameterizedType paramType = (ParameterizedType) returnType;
		            Type[] argTypes = paramType.getActualTypeArguments();
		            System.out.println(argTypes[0]);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
