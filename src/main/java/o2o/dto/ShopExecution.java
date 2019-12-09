package o2o.dto;

import java.util.List;

import o2o.Enum.ShopStateEnum;
import o2o.entity.Shop;

public class ShopExecution
{
	//结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	//店铺数量
	private int count;
	
	//操作的店铺(增删改店铺)
	private Shop shop;
	
	//获取的店铺列表(查询时使用)
	private List<Shop> shops;

	
	public ShopExecution()
	{
		super();
	}

	//操作店铺失败时使用的构造器
	public ShopExecution(ShopStateEnum stateEnum)
	{
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//操作店铺成功时使用的构造器
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shops)
	{
		super();
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shops = shops;
	}
	
	//操作店铺成功时使用的构造器
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
