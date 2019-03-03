package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.model.util.Util;
import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;

public class Test3 {
	
	public static void main(String[] args) {
		try {
			Class<?> c = ContactDao.class;
			Method[] allMethods = c.getDeclaredMethods();
			
			MethodMetadata methodMetadata = new MethodMetadata();
			Class<?> entityClass = Contact.class;
			
			for(Method method: allMethods) {
				Type genericReturnType = method.getGenericReturnType();
				System.out.println(genericReturnType);
				Class<?> returnType = method.getReturnType();
				System.out.println(returnType);
				boolean isResolvedBasType = false;
				while(!isResolvedBasType) {
					if(genericReturnType instanceof ParameterizedType) {
						ParameterizedType paramType = (ParameterizedType) genericReturnType;
			            Type[] argTypes = paramType.getActualTypeArguments();
			            genericReturnType = paramType.getRawType();
			            if(Optional.class.equals(genericReturnType)) {
			            	methodMetadata.setOptional(true);
			            } else if(List.class.equals(genericReturnType)) {
			            	methodMetadata.setList(true);
			            }
			            if(argTypes[0] instanceof ParameterizedType) {
			            	genericReturnType = argTypes[0];
			            } else {
			            	returnType = (Class<?>) argTypes[0];
			            }
					} else {
						if(!returnType.equals(entityClass)) {
							// TODO: Exception
						}
						methodMetadata.setReturnType(returnType);
						isResolvedBasType = true;
					}
				}
			}
			
//			for(Method method: allMethods) {
//				
//				Type returnType = method.getGenericReturnType();
//				System.out.println(returnType);
//				System.out.println(returnType.getClass());
//				
//				System.out.println("Optional: "+Optional.class.equals(returnType));
//				System.out.println("List: "+List.class.equals(returnType));
//				System.out.println("Contact: "+Contact.class.equals(returnType));
//				
//				if(returnType instanceof ParameterizedType) {
//					ParameterizedType paramType = (ParameterizedType) returnType;
//		            Type[] argTypes = paramType.getActualTypeArguments();
//		            System.out.println("Optional: "+Optional.class.equals(paramType.getRawType()));
//					System.out.println("List: "+List.class.equals(paramType.getRawType()));
//					System.out.println("Contact: "+Contact.class.equals(paramType.getRawType()));
//		            System.out.println(argTypes[0]);
//				}
//				
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
