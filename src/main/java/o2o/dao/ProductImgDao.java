package o2o.dao;

import java.util.List;

import o2o.entity.ProductImg;

public interface ProductImgDao
{
	/**
	 * 批量插入商品图片
	 * @param productImgs
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgs);
}
