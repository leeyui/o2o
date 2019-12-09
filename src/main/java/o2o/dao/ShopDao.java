package o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import o2o.entity.Shop;

public interface ShopDao
{
	/**
	 * ��ҳ��ѯ����,�������������:������(ģ��),����״̬,�������,����Id,owner
	 * @param shopCondition 
	 * @param rowIndex �ӵڼ��п�ʼȡ����
	 * @param pageSize ���ص�����
	 * @return
	 */
	public List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
	
	public int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * ��������
	 * @param shop
	 * @return
	 */
	public int insertShop(Shop shop);
	
	/**
	 * ���µ���
	 * @param shop
	 * @return
	 */
	public int updateShop(Shop shop);
	
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Shop queryByShopId(Long id);
}
