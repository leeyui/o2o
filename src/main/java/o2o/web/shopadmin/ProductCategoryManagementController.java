package o2o.web.shopadmin;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import o2o.Enum.ProductCategoryStateEnum;
import o2o.dto.ProductCategoryExcution;
import o2o.entity.ProductCategory;
import o2o.entity.Shop;
import o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController
{
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value="/getproductcategorylist", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getProductCategoryList(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		List<ProductCategory> list = null;
		if(currentShop != null && currentShop.getShopId() > 0)
		{
			list = productCategoryService.getProductCategory(currentShop.getShopId());
			modelMap.put("success", true);
			modelMap.put("list", list);
		} else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", ProductCategoryStateEnum.INNER_ERROR.getStateInfo());
		}
		return modelMap;
	}
	@RequestMapping(value="/addproductcategorys", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategories,
			HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		for (ProductCategory productCategory : productCategories)
		{
			productCategory.setShopId(currentShop.getShopId());
		}
		if(productCategories != null && productCategories.size() > 0)
		{
			try
			{
				ProductCategoryExcution pe = productCategoryService.batchAddProductCategory(productCategories);
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else 
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
	
	@RequestMapping(value="removeproductcategory", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeProductCategory(Long productCategoryId,HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		if(productCategoryId != null&& productCategoryId >0) {
			try
			{
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExcution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if(pe.getState() == ProductCategoryStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", pe.getStateInfo());
				}
			} catch (Exception e)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
}
