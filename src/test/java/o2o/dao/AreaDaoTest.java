package o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.BaseTest;
import o2o.entity.Area;

public class AreaDaoTest extends BaseTest
{
	@Autowired
	private AreaDao areadao;
	
	@Test
	public void testQueryArea()
	{
		List<Area> list = areadao.queryArea();
		assertEquals(4, list.size());
	}
}
