package com.orm.v_1.SimpleDocumentMapper.test.example_customer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;

public class MainTest {

	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "test", List.of(Customer.class, Package.class));
			
			CustomerDao customerDao = (CustomerDao) indigoConfigurator.provideProxy(CustomerDao.class, Customer.class);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2017, 9, 10);
			Date date = calendar.getTime();
			
			Package p1 = new Package();
			p1.setTitle("PRO-101");
			p1.setCreatedAt(date);
			p1.setChannels(List.of("Prva", "Rts", "N1", "O2"));
			
			Package p2 = new Package();
			p2.setTitle("PRO-201");
			p2.setCreatedAt(date);
			p2.setChannels(List.of("RTV", "Nova", "Rtl", "Sabac"));
			
			Package p3 = new Package();
			p3.setTitle("LK015");
			p3.setCreatedAt(date);
			p3.setChannels(List.of("Kraljevo", "Valjevo", "Indjija", "Kragujevac"));
			
			Customer c1 = new Customer();
			c1.setFirstName("Pera");
			c1.setLastName("Peric");
			c1.setEmail("pera.peric@gmail.com");
			c1.setPassword("pera1234");
			c1.setDates(List.of(date, date, date));
			c1.setPackages(List.of(p1, p2, p3));
			customerDao.createOne(c1);
			
			Customer c2 = new Customer();
			c2.setFirstName("Mika");
			c2.setLastName("Mikic");
			c2.setEmail("mika.mikic@gmail.com");
			c2.setPassword("mika1234");
			c2.setDates(List.of(date, date));
			c2.setPackages(List.of(p1, p3));
			customerDao.createOne(c2);
			
			Customer c3 = new Customer();
			c3.setFirstName("Zika");
			c3.setLastName("Zikic");
			c3.setEmail("zika.zikic@gmail.com");
			c3.setPassword("zika1234");
			c3.setDates(List.of(date));
			c3.setPackages(List.of(p2));
			customerDao.createOne(c3);
			
			System.out.println(customerDao.existsByEmailEq("mika.mikic@gmail.com"));
			System.out.println(customerDao.existsByEmailEq("mika.mikic5@gmail.com"));
			
			Optional<Customer> optionalCustomer = customerDao.readByEmailEqAndPasswordEq("mika.mikic@gmail.com", "mika1234");
			if(optionalCustomer.isPresent()) {
				System.out.println(optionalCustomer.get());
			}
			
			List<Customer> customersPackageStartsWith = customerDao.readByPackages_TitleStartsWith("PRO");
			for(Customer customer: customersPackageStartsWith) {
				System.out.println(customer);
			}
			
			List<Customer> customersLastNameOrPackageCreated = customerDao.readByLastNameEndWithAndPackages_CreatedAtEq("kic", date);
			for(Customer customer: customersLastNameOrPackageCreated) {
				System.out.println(customer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
