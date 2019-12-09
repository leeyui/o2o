package o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.BaseTest;
import o2o.dto.ShopExecution;
import o2o.entity.Area;
import o2o.entity.Shop;
import o2o.entity.ShopCategory;

public class ShopServiceTest extends BaseTest
{
	@Autowired
	private ShopService service;
	
	@Test
	@Ignore
	public void testModifyShop() throws Exception
	{
		Shop shop = service.getByShopId(58L);
		shop.setShopName("修改后的店铺名称");
		File file = new File("C:\\Users\\asus-pc\\Desktop\\image\\bee.png");
		InputStream is = new FileInputStream(file);
		ShopExecution se = service.modifyShop(shop, is, file.getName());
		System.out.println(se.getShop().getShopImg());
	}
	@Test
	@Ignore
	public void testAddShop() throws Exception {
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
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		
		shop.setArea(area); 
		shop.setShopCategory(sc);
		File file = new File("C:\\Users\\asus-pc\\Desktop\\p站\\62628015_p0.jpg");
		ShopExecution n = service.addShop(shop, new FileInputStream(file),file.getName());
		assertEquals(0, n.getState());
	} 
		
}
