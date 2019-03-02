package com.orm.v_1.SimpleDocumentMapper.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.orm.v_1.SimpleDocumentMapper.model.MDocument;
import com.orm.v_1.SimpleDocumentMapper.odm.config.ConnectionPool;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;
import com.orm.v_1.SimpleDocumentMapper.repositories.impl.PagingAndSortingRepositoryImpl;

public class ProxyRepository<T> extends PagingAndSortingRepositoryImpl<T> implements InvocationHandler {
	
	public static Object newInstance(Class<?>[] interfaces, MDocument documentDatadata, ConnectionPool connectionPoolReference) {
		return Proxy.newProxyInstance(PagingAndSortingRepository.class.getClassLoader(), interfaces, new ProxyRepository<>(documentDatadata, connectionPoolReference));
	}
	
	public ProxyRepository(MDocument documentMetadata, ConnectionPool connectionPoolReference) {
		super(documentMetadata, connectionPoolReference);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/**
		 * 	Spremamo metapodatke o metodu, kada se funkcija aktivira
		 *  ukoliko metoda nije iz PagingAndSortingRepository uzimamo metapodatke,
		 *  na osnovu njih i argumenata pakujemo specifikaciju, i gledamo koju od metoda
		 *  korisnimo za spremanje ogvorora te u sta pakujemo odgovor
		 */
		return null;
	}

}
