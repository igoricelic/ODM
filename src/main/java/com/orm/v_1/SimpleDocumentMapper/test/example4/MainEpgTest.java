package com.orm.v_1.SimpleDocumentMapper.test.example4;

import java.util.List;

import com.orm.v_1.SimpleDocumentMapper.odm.config.IndigoConfigurator;
import com.orm.v_1.SimpleDocumentMapper.odm.config.impl.IndigoConfiguratorImpl;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.Specification;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.builders.SpecificationBuilder;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Comparator;
import com.orm.v_1.SimpleDocumentMapper.odm.specification.model.enums.Operator;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.Page;
import com.orm.v_1.SimpleDocumentMapper.odm.structures.PageRequest;
import com.orm.v_1.SimpleDocumentMapper.repositories.PagingAndSortingRepository;

public class MainEpgTest {

	public static void main(String[] args) {
		try {
			IndigoConfigurator indigoConfigurator = new IndigoConfiguratorImpl("localhost", 27017, "ott_cms",
					List.of(Epg.class, Content.class));

			PagingAndSortingRepository<Epg> epgDao = indigoConfigurator
					.providePagingAndSortingRepository(Epg.class);
			
			System.out.println(epgDao.count());
			
//			Page<Epg> pageOfEpgs = epgDao.readAll(new PageRequest(3, 10));
//			for(Epg epg: pageOfEpgs.getContent()) {
//				System.out.println(epg.toString());
//			}
			
			Specification<Epg> epgSpecification1 = new SpecificationBuilder<Epg>()
					.addCriterion("title", "Vis", Comparator.StartsWith).build();
			
			Specification<Epg> epgSpecification2 = new SpecificationBuilder<Epg>()
					.addCriterion("stored", false, Comparator.Equality).build();
			
			Specification<Epg> epgSpecification3 = new SpecificationBuilder<Epg>()
					.addCriterion("content.year", "1998", Comparator.Equality)
					.addCriterion("content.runtime", "82", Comparator.Equality).operator(Operator.Or).build();

			Specification<Epg> epgSpecification4 = new SpecificationBuilder<Epg>()
					.addCriterion("content.genres", "drama", Comparator.In)
					.addCriterion("content.genres", "komedija", Comparator.In).build();
			
//			List<Epg> result1 = epgDao.readBy(epgSpecification1);
//			for(Epg epg: result1) {
//				System.out.println(epg.toString());
//			}
//			
//			List<Epg> result2 = epgDao.readBy(epgSpecification2);
//			for(Epg epg: result2) {
//				System.out.println(epg.toString());
//			}
//			
//			List<Epg> result3 = epgDao.readBy(epgSpecification3);
//			for(Epg epg: result3) {
//				System.out.println(epg.toString());
//			}
			
			List<Epg> result4 = epgDao.readBy(epgSpecification4);
			for(Epg epg: result4) {
				System.out.println(epg.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
