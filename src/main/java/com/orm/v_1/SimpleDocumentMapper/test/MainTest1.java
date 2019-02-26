package com.orm.v_1.SimpleDocumentMapper.test;

import java.util.Arrays;
import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.model.MDatabase;
import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.repositories.CrudRepository;
import com.orm.v_1.SimpleDocumentMapper.test.entities.Address;
import com.orm.v_1.SimpleDocumentMapper.test.entities.Car;
import com.orm.v_1.SimpleDocumentMapper.test.entities.User;

public class MainTest1 {
	
	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", Arrays.asList(User.class, Address.class, Car.class));
			
			MDatabase db = indigoConfigurator.provideModel();
			
			System.out.println(db.getName());
			
			CrudRepository<User> userDao = indigoConfigurator.provideCrudRepository(User.class);
			
			System.out.println(userDao.count());
			
			List<User> users = userDao.readAll();
			for(User user: users) {
				System.out.println(user.toString());
			}
			
			User user1 = new User("1234", "Igor", "Igoja", "igor.icelic@netcast.rs", 10, "neki", "neka");
			userDao.createOne(user1);
			
			System.out.println(userDao.count());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
