package o2o.service;


import java.io.InputStream;

import o2o.dto.ShopExecution;
import o2o.entity.Shop;

public interface ShopService
{
	/**
	 * ���ݲ�ѯ�������������б�
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
	/**
	 * ע�������Ϣ,����ͼƬ����
	 * 
	 * @param shop
	 * @param shopImg
	 * @param fileName
	 * @return
	 */
	 public ShopExecution addShop(Shop shop, InputStream shopImg ,String fileName);
	 /**
	  * ���ݵ���id��ȡ������Ϣ
	  * 
	  * @param id
	  * @return
	  */
	 Shop getByShopId(Long id);
	 /**
	  * ���µ�����Ϣ��������ͼƬ�Ĵ���
	  * 
	  * @param shop
	  * @param shopImgInputStream
	  * @param fileName
	  * @return
	  */
	 ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName);
}
