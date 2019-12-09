package o2o.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import o2o.Enum.ShopStateEnum;
import o2o.dto.ShopExecution;
import o2o.entity.Area;
import o2o.entity.PersonInfo;
import o2o.entity.Shop;
import o2o.entity.ShopCategory;
import o2o.service.AreaService;
import o2o.service.ShopCategoryService;
import o2o.service.ShopService;
import o2o.util.CodeUtil;
import o2o.util.HttpServletRequestUtil;
import o2o.util.ImageUtil;
import o2o.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController
{
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getshopbyid",method= {RequestMethod.GET})
	@ResponseBody
	private Map<String, Object> getShopById(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if (shopId > -1)
		{
			try
			{
				Shop shop = shopService.getByShopId(shopId);
				List<ShopCategory> shopCategories = shopCategoryService.getShopCategoryList(shop.getShopCategory());
				List<Area> areas = areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("shopCategoryList", shopCategories.get(0));
				modelMap.put("areaList", areas);
				modelMap.put("success", true);
			} catch (Exception e)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		} else 
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/getshopinitinfo",method= {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getShopInitInfo()
	{
		Map<String, Object> modelMap = new HashMap<>();
		List<ShopCategory> shopCategories = new ArrayList<>();
		List<Area> areas = new ArrayList<>();
		try
		{
			shopCategories = shopCategoryService.getShopCategoryList(new ShopCategory());
			areas = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategories);
			modelMap.put("areaList", areas);
			modelMap.put("success", true);
		} catch (Exception e)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	
	
	
	@RequestMapping(value="registershop",method= {RequestMethod.POST})
	@ResponseBody
	private Map<String, Object> register(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		if (!CodeUtil.checkVerifyCode(request))
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		//1.接收并转换相应的参数,包括店铺信息、图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shop");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try
		{
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request))
		{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if (null != shop && null != shopImg)
		{
			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwnerId(owner.getUserId());
//			File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRondomFileName());
//			try
//			{
//				shopImgFile.createNewFile();
//			} catch (IOException e1)
//			{
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e1.getMessage());
//				return modelMap;
//			}
//			try
//			{
//				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//			} catch (IOException e)
//			{
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
			ShopExecution se = null;
			try
			{
				se = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState())
				{
					modelMap.put("success", true);
					//用户可操纵的店铺列表
					@SuppressWarnings("unchecked")
					List<Shop> shops = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shops == null || shops.size() == 0)
					{
						shops = new ArrayList<>();
					} 
					shops.add(se.getShop());
					request.getSession().setAttribute("shopList", shops);
					return modelMap;
				} else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
					return modelMap;
				}
			} catch (IOException e)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		//3.返回结果
		
		
	}
//	private static void inputStreamToFile(InputStream ins, File file)
//	{
//		OutputStream os = null;
//		try
//		{
//			os = new FileOutputStream(file);
//			int byteRead = 0;
//			byte[] bytes = new byte[1024];
//			while((byteRead = ins.read(bytes)) != -1)
//			{
//				os.write(bytes, 0, byteRead);
//			}
//		} catch (Exception e)
//		{
//			throw new RuntimeException("调用inputStreamToFile异常:" + e.getMessage());
//		} finally 
//		{
//			if (null != os)
//			{
//				try
//				{
//					os.close();
//				} catch (IOException e)
//				{
//					throw new RuntimeException("关闭OutputStram异常:" + e.getMessage());
//				}
//			}
//			if (null != ins)
//			{
//				try
//				{
//					ins.close();
//				} catch (IOException e)
//				{
//					throw new RuntimeException("关闭InputStram异常:" + e.getMessage());
//				}
//			}
//		}
//	}
	@RequestMapping(value="/modifyshop",method= {RequestMethod.POST})
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		if (!CodeUtil.checkVerifyCode(request))
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		//1.接收并转换相应的参数,包括店铺信息、图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shop");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try
		{
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request))
		{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		//2.修改店铺信息
		if (null != shop && null != shop.getShopId())
		{
			ShopExecution se = null;
			try
			{
				if (shopImg == null)
				{
					se = shopService.modifyShop(shop, null, null);
				} else
				{
					se = shopService.modifyShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState())
				{
					modelMap.put("success", true);
					return modelMap;
				} else
				{
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
					return modelMap;
				}
			} catch (IOException e)
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
				return modelMap;
			}
		} else
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		//3.返回结果
	}
	@RequestMapping(value="/getshoplist",method= {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getShopList(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		PersonInfo user = new PersonInfo();
		user.setUserId(12L);
		request.getSession().setAttribute("user", user);
		user = (PersonInfo) request.getSession().getAttribute("user");
		List<Shop> shops = new ArrayList<>();
		try
		{
			Shop shopCondition = new Shop();
			shopCondition.setOwnerId(user.getUserId());
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
			modelMap.put("shopList", shopExecution.getShops());
			modelMap.put("user", user);
			modelMap.put("success", true);
			return modelMap;
		} catch (Exception e)
		{
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
	}
	@RequestMapping(value="/getshopmanagementinfo",method= {RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getShopmanagementInfo(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId <= 0)
		{
			Object currentShopObj = request.getSession().getAttribute("currentShop");
			if(null == currentShopObj)
			{
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shop/shoplist");
			} else
			{
				Shop currentShop = (Shop) currentShopObj;
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else 
		{
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
			modelMap.put("redirect", false);
		}
		return modelMap;
	}
}
