package cz.muni.fi.pa165.service.services.mapping;

import java.util.Collection;
import java.util.List;

/**
 * Service interface for mapping objects and collections.
 *
 * @author Marek Turis
 */
public interface MappingService {

	/**
	 * Maps whole collection to list of given class
	 *
	 * @param objects Objects to be mapped
	 * @param mapToClass Class to which map
	 * @param <T>
	 * @return mapped collection
	 */
	public <T> List<T> map(Collection<?> objects, Class<T> mapToClass);

	/**
	 * Maps object to object of other type
	 *
	 * @param object Object to be mapped
	 * @param mapToClass Class to which map
	 * @param <T>
	 * @return mapped object
	 */
	public  <T> T map(Object object, Class<T> mapToClass);
}
