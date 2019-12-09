package o2o.Enum;


public enum ShopStateEnum
{
	CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),SUCCESS(1,"操作成功"),PASS(2,"通过认证"),INNER_ERROR(-1001,"操作失败"),
	NULL_SHOPID(-1002,"店铺商店ID为空"),NULL_SHOP(-1003,"店铺信息为空");
	
	private int state;
	
	private String stateInfo;
	
	private ShopStateEnum(int state,String stateInfo)
	{
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState()
	{
		return state;
	}

	public String getStateInfo()
	{
		return stateInfo;
	}
	public static ShopStateEnum stateof(int index)
	{
		for (ShopStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
