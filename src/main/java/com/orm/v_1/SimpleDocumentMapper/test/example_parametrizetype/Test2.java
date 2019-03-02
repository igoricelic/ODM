package com.orm.v_1.SimpleDocumentMapper.test.example_parametrizetype;

import static java.lang.System.out;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Test2 {

	private static final String fmt = "%24s: %s%n";

	public static void main(String... args) {
		try {
			Class<?> c = ContactDao.class;
			Method[] allMethods = c.getDeclaredMethods();
			for (Method m : allMethods) {
				out.format("%s%n", m.toGenericString());

				out.format(fmt, "ReturnType", m.getReturnType());
				out.format(fmt, "GenericReturnType", m.getGenericReturnType());

				Class<?>[] pType = m.getParameterTypes();
				Type[] gpType = m.getGenericParameterTypes();
				for (int i = 0; i < pType.length; i++) {
					out.format(fmt, "ParameterType", pType[i]);
					out.format(fmt, "GenericParameterType", gpType[i]);
				}

			}

			// production code should handle these exceptions more gracefully
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}
