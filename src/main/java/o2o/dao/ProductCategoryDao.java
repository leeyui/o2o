package o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import o2o.entity.ProductCategory;

public interface ProductCategoryDao
{
	/**
	 * ͨ��shop id ��ѯ������Ʒ���
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(Long shopId);
	/**
	 * ����������Ʒ���
	 * @param productCategories
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategories);
	/**
	 * ɾ��ָ����Ʒ���
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")Long productCategoryId,@Param("shopId") Long shopId);
}
