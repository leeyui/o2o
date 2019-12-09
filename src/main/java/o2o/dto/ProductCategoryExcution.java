package o2o.dto;

import java.util.List;

import o2o.Enum.ProductCategoryStateEnum;
import o2o.entity.ProductCategory;

public class ProductCategoryExcution
{
	private int state;
	
	private String stateInfo;
	
	private ProductCategory productCategory;
	
	private List<ProductCategory> productCategoryList;
	private int count;

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
	public ProductCategory getProductCategory()
	{
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory)
	{
		this.productCategory = productCategory;
	}
	public List<ProductCategory> getProductCategoryList()
	{
		return productCategoryList;
	}
	public void setProductCategoryList(List<ProductCategory> productCategoryList)
	{
		this.productCategoryList = productCategoryList;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
	public ProductCategoryExcution()
	{
		super();
	}
	public ProductCategoryExcution(ProductCategoryStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	public ProductCategoryExcution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	public ProductCategoryExcution(ProductCategoryStateEnum stateEnum,ProductCategory productCategory)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategory = productCategory;
	}
	
}
