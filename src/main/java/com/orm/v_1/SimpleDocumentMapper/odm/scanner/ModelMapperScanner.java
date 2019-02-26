package com.orm.v_1.SimpleDocumentMapper.odm.scanner;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;

public interface ModelMapperScanner {
	
	public MDatabase scanEntities (String dbName, int port, List<Class<?>> entities);

}
