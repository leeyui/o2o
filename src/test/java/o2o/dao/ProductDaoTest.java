package o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.entity.Product;
import o2o.entity.ProductCategory;
import o2o.entity.Shop;

import o2o.BaseTest;

public class ProductDaoTest extends BaseTest
{
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(58L);
		Shop shop2 = new Shop();
		shop2.setShopId(59L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(8L);
		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryId(9L);
		ProductCategory pc3 = new ProductCategory();
		pc3.setProductCategoryId(4L);
		Product product1 = new Product();
		product1.setProductName("≤‚ ‘1");
		product1.setProductDesc("≤‚ ‘Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("≤‚ ‘2");
		product2.setProductDesc("≤‚ ‘Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(0);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc2);
		Product product3 = new Product();
		product3.setProductName("≤‚ ‘3");
		product3.setProductDesc("≤‚ ‘Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(0);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(pc3);
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
}
