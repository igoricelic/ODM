package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.test.example2.Contact;

public class Test1 {

	public static void main(String[] args) {
		try {
			Contact contact = new Contact();
			List<Contact> listContact = new ArrayList<>();
			Optional<Contact> optionalContact = Optional.empty();
			Optional<List<Contact>> optionalListContact = Optional.empty();
			
			Class<?> clazz = contact.getClass();
			System.out.println(clazz.getName());
			
			if(clazz.getGenericSuperclass() instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) clazz.getGenericSuperclass();
				Type[] argTypes = paramType.getActualTypeArguments();
				System.out.println(argTypes[0]);
			}
			
			Class<?> optionalClazz = optionalContact.getClass();
			System.out.println(optionalClazz.getName());
			System.out.println(optionalClazz.getTypeParameters());
			
			if(optionalClazz.getGenericSuperclass() instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) optionalClazz.getGenericSuperclass();
				Type[] argTypes = paramType.getActualTypeArguments();
				System.out.println(argTypes[0]);
			}
			
//			Type type = field.getGenericType();
//			if(type instanceof ParameterizedType) {
//				ParameterizedType paramType = (ParameterizedType) type;
//	            Type[] argTypes = paramType.getActualTypeArguments();
//	            return (Class<?>) argTypes[0];
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
