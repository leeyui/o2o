package o2o.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import o2o.dao.AreaDao;
import o2o.entity.Area;
import o2o.service.AreaService;

@Service
public class AreaServiceImp implements AreaService
{
	@Autowired
	private AreaDao areadao;
	@Override
	public List<Area> getAreaList()
	{
		return areadao.queryArea();
	}

}
