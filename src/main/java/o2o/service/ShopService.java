package o2o.service;


import java.io.InputStream;

import o2o.dto.ShopExecution;
import o2o.entity.Shop;

public interface ShopService
{
	/**
	 * 根据查询条件返回商铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	/**
	 * 注册店铺信息,包括图片处理
	 * 
	 * @param shop
	 * @param shopImg
	 * @param fileName
	 * @return
	 */
	 public ShopExecution addShop(Shop shop, InputStream shopImg ,String fileName);
	 /**
	  * 根据店铺id获取店铺信息
	  * 
	  * @param id
	  * @return
	  */
	 Shop getByShopId(Long id);
	 /**
	  * 更新店铺信息，包括对图片的处理
	  * 
	  * @param shop
	  * @param shopImgInputStream
	  * @param fileName
	  * @return
	  */
	 ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName);
}
