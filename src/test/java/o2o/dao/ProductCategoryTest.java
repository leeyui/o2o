package o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import o2o.BaseTest;
import o2o.entity.ProductCategory;

public class ProductCategoryTest extends BaseTest
{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	@Ignore
	public void testQueryProductCategory()
	{
		Long shopId = 58l;
		List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory productCategory : productCategories)
		{
			System.out.println(productCategory.getProductCategoryName() + productCategory.getPriority());
		}
	}
	@Test
	@Ignore
	public void testAInsertProductCategory() throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setProductCategoryDesc("测试商品类别");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setLastEditTime(new Date());
		productCategory.setShopId(58L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setProductCategoryDesc("测试商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setLastEditTime(new Date());
		productCategory2.setShopId(61L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao
				.batchInsertProductCategory(productCategoryList);
		assertEquals(2, effectedNum);
	}
	@Test
	public void testCDeleteProductCategory() throws Exception {
		long shopId = 58l;
		List<ProductCategory> productCategoryList = productCategoryDao
				.queryProductCategoryList(shopId);
		int effectedNum = productCategoryDao.deleteProductCategory(
				productCategoryList.get(0).getProductCategoryId(), shopId);
		assertEquals(1, effectedNum);
		effectedNum = productCategoryDao.deleteProductCategory(
				productCategoryList.get(1).getProductCategoryId(), shopId);
		assertEquals(1, effectedNum);
	}
	
}
