package o2o.dto;

import java.util.List;

import o2o.Enum.ShopStateEnum;
import o2o.entity.Shop;

public class ShopExecution
{
	//���״̬
	private int state;
	
	//״̬��ʶ
	private String stateInfo;
	
	//��������
	private int count;
	
	//�����ĵ���(��ɾ�ĵ���)
	private Shop shop;
	
	//��ȡ�ĵ����б�(��ѯʱʹ��)
	private List<Shop> shops;

	
	public ShopExecution()
	{
		super();
	}

	//��������ʧ��ʱʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum)
	{
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//�������̳ɹ�ʱʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shops)
	{
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shops = shops;
	}
	
	//�������̳ɹ�ʱʹ�õĹ�����
	public ShopExecution(ShopStateEnum stateEnum, Shop shop)
	{
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public String getStateInfo()
	{
		return stateInfo;
	}

	public void setStateInfo(String stateInfo)
	{
		this.stateInfo = stateInfo;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public Shop getShop()
	{
		return shop;
	}

	public void setShop(Shop shop)
	{
		this.shop = shop;
	}

	public List<Shop> getShops()
	{
		return shops;
	}

	public void setShops(List<Shop> shops)
	{
		this.shops = shops;
	}
}
