package o2o.Enum;


public enum ShopStateEnum
{
	CHECK(0,"�����"),OFFLINE(-1,"�Ƿ�����"),SUCCESS(1,"�����ɹ�"),PASS(2,"ͨ����֤"),INNER_ERROR(-1001,"����ʧ��"),
	NULL_SHOPID(-1002,"�����̵�IDΪ��"),NULL_SHOP(-1003,"������ϢΪ��");
	
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
