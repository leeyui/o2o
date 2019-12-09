package o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import o2o.entity.ProductCategory;

public interface ProductCategoryDao
{
	/**
	 * 通过shop id 查询店铺商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(Long shopId);
	/**
	 * 批量新增商品类别
	 * @param productCategories
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategories);
	/**
	 * 删除指定商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")Long productCategoryId,@Param("shopId") Long shopId);
}
