package o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.BaseTest;
import o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest
{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Test
	public void testQueryShopCategory()
	{
		List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2, shopCategories.size());
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setParentId(34l);
		shopCategories = shopCategoryDao.queryShopCategory(shopCategory);
		assertEquals(1, shopCategories.size());
		
	}
}
