package com.orm.v_1.SimpleDocumentMapper.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.interpreter.InterpreterProvider;
import com.orm.v_1.SimpleDocumentMapper.interpreter.InterpreterProviderImpl;
import com.orm.v_1.SimpleDocumentMapper.interpreter.model.MethodMetadata;
import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.impl.PagingAndSortingRepositoryImpl;

public class ProxyRepository<T> extends PagingAndSortingRepositoryImpl<T> implements InvocationHandler {
	
	public static Object newInstance(Class<?>[] interfaces, MDocument documentDatadata, ConnectionPool connectionPoolReference) {
		return Proxy.newProxyInstance(PagingAndSortingRepository.class.getClassLoader(), interfaces, new ProxyRepository<>(documentDatadata, interfaces[0], connectionPoolReference));
	}
	
	private InterpreterProvider interpreterProvider;
	
	private Map<Method, MethodMetadata> gridData;
	
	public ProxyRepository(MDocument documentMetadata, Class<?> declatedInterface, ConnectionPool connectionPoolReference) {
		super(documentMetadata, connectionPoolReference);
		this.interpreterProvider = new InterpreterProviderImpl();
		this.gridData = new HashMap<Method, MethodMetadata>();
		Method[] allMethods = declatedInterface.getDeclaredMethods();
		for(Method method: allMethods) {
			this.gridData.put(method, interpreterProvider.interpretMethod(method, documentMetadata));
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			return this.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(this, args);
		} catch (NoSuchMethodException e) {
			/**
			* Potrebno je bolje organizovati, tj. u zavisnosti od parametara, uzeti referencu na metodu
			* i nju posle pozvati
			*/
			
			MethodMetadata methodMetadata = gridData.get(method);
			Specification<T> specification = specificationResolver.prepareSpecificaiton(methodMetadata.getSpecificationProposal(), args);
			
			switch(methodMetadata.getMethodPrefixType()) {
				case READ:
					if(methodMetadata.isCollection()) {
						if(methodMetadata.isList()) {
							return (methodMetadata.isOptional()) ? Optional.ofNullable(readBy(specification)) : readBy(specification);
						} else if(methodMetadata.isPage()) {
							return (methodMetadata.isOptional()) ? Optional.ofNullable(readBy(specification, new PageRequest(0, 10))) : readBy(specification, new PageRequest(0, 10));
						}
					} else {
						return (methodMetadata.isOptional()) ? Optional.ofNullable(readOneBy(specification)) : readOneBy(specification);
					}
				case EXISTS:
					return existBy(specification);
				case COUNT:
					return countBy(specification);
			}
			
		}
		return null;
	}

}
