package o2o.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import o2o.Enum.ProductCategoryStateEnum;
import o2o.dao.ProductCategoryDao;
import o2o.dto.ProductCategoryExcution;
import o2o.entity.ProductCategory;
import o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImp implements ProductCategoryService
{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Override
	public List<ProductCategory> getProductCategory(Long shopId)
	{
		return productCategoryDao.queryProductCategoryList(shopId);
	}
	@Override
	@Transactional
	public ProductCategoryExcution batchAddProductCategory(List<ProductCategory> productCategories)
	{
		if(productCategories != null && productCategories.size() > 0)
		{
		try
		{
			int effectedNum = productCategoryDao.batchInsertProductCategory(productCategories);
			if(effectedNum <= 0)
			{
				throw new RuntimeException("商品类别新增失败");
			} else
			{
				return new ProductCategoryExcution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("batchInsertProductCategory err:" + e.getMessage());
		}
		}else {
			return new ProductCategoryExcution(ProductCategoryStateEnum.EMPTY_LIST);
		}
		
	}
	@Override
	@Transactional
	public ProductCategoryExcution deleteProductCategory(Long productCategoryId, Long shopId)
	{
		//TODO 将此商品类别下的商品的类别id置为空
		try
		{
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum <= 0)
			{
				throw new RuntimeException("删除商品类别失败");
			}else 
			{
				return new ProductCategoryExcution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("deleteProductCategory err:" + e.getMessage());
		}
	}
}
