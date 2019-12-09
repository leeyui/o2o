package o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.entity.Area;
import o2o.entity.Shop;
import o2o.entity.ShopCategory;

import o2o.BaseTest;

public class ShopDaoTest extends BaseTest
{
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void insertshoptest() throws Exception
	{
		Shop shop = new Shop();
		shop.setOwnerId(12L);
		Area area = new Area();
		area.setAreaId(7L);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(33L);
		shop.setShopName("mytest1");
		shop.setShopDesc("mytest1");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		shop.setArea(area); 
		shop.setShopCategory(sc);
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	@Test
	@Ignore
	public void testQueryByShopId() throws Exception {
		long shopId = 36;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getArea().getAreaId());
	}
	
	@Test
	@Ignore
	public void testDUpdateShop() {
		long shopId = 58l;
		Shop shop = shopDao.queryByShopId(shopId);
		Area area = new Area();
		area.setAreaId(7L);
		shop.setArea(area);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(33L);
		shop.setShopCategory(shopCategory);
		shop.setShopName("四季花");
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	@Test
	public void testQueryshopList() {
		Shop shop = new Shop();
		shop.setShopName("修改");
		List<Shop> shops = shopDao.queryShopList(shop, 0, 5);
		System.out.println(shops);
	}

}
