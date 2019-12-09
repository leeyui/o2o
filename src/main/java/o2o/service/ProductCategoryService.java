package o2o.service;

import java.util.List;

import o2o.dto.ProductCategoryExcution;
import o2o.entity.ProductCategory;

public interface ProductCategoryService
{
	public List<ProductCategory> getProductCategory(Long shopId);
	
	public ProductCategoryExcution batchAddProductCategory(List<ProductCategory> productCategories);
	
	public ProductCategoryExcution deleteProductCategory(Long productCategoryId ,Long shopId);
}
