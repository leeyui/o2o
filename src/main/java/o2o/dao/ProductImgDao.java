package o2o.dao;

import java.util.List;

import o2o.entity.ProductImg;

public interface ProductImgDao
{
	/**
	 * ����������ƷͼƬ
	 * @param productImgs
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgs);
}
