package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.AreaDao;
import cz.muni.fi.pa165.entities.Area;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Michael Cada
 */
@Named
public class AreaServiceImpl extends CrudServiceImpl<Area> implements AreaService {

	@Inject
	public AreaServiceImpl(AreaDao areaDao) { super(areaDao); }
}
