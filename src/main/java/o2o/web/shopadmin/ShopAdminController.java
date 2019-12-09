package o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method= {RequestMethod.GET})
public class ShopAdminController
{
	@RequestMapping(value="shopoperation")
	public String shopOperation()
	{
		return "shop/index";
	}
	@RequestMapping(value="getshop")
	public String shopList()
	{
		return "shop/shoplist";
	}
	@RequestMapping(value="shopmanagement")
	public String shopManagement()
	{
		return "shop/shopmanage";
	}
	@RequestMapping(value="productcategorymanage")
	public String productManagement()
	{
		return "shop/productcategorymanagement";
	}
}
