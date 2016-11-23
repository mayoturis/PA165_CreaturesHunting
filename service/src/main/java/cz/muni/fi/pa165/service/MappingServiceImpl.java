package cz.muni.fi.pa165.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Marek Turis
 */
@Named
public class MappingServiceImpl implements MappingService {

	private Mapper dozerMapper;

	public MappingServiceImpl() {
		this.dozerMapper = new DozerBeanMapper();
	}

	@Override
	public <T> List<T> map(Collection<?> objects, Class<T> mapToClass) {
		List<T> mappedCollection = new ArrayList<>();
		for (Object object : objects) {
			mappedCollection.add(map(object, mapToClass));
		}
		return mappedCollection;
	}

	@Override
	public <T> T map(Object object, Class<T> mapToClass) {
		return dozerMapper.map(object,mapToClass);
	}
}
