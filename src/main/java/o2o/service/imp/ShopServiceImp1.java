package o2o.service.imp;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import o2o.Enum.ShopStateEnum;
import o2o.dao.ShopDao;
import o2o.dto.ShopExecution;
import o2o.entity.Shop;
import o2o.service.ShopService;
import o2o.util.ImageUtil;
import o2o.util.PageCalculator;
import o2o.util.PathUtil;

@Service
public class ShopServiceImp1 implements ShopService
{
	@Autowired
	private ShopDao shopdao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInpuStream,String fileName)
	{
		if (null == shop)
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		try
		{
			//给店铺初始化
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectedNum = shopdao.insertShop(shop);
			if (effectedNum <= 0)
				throw new RuntimeException("创建店铺失败");
			else 
			{
				if(null != shopImgInpuStream)
				{
					addShopImg(shop,shopImgInpuStream,fileName);
					effectedNum = shopdao.updateShop(shop);
					if(0 >= effectedNum)
						throw new RuntimeException("创建图片地址失败");
				}
			}
		} catch (Exception e)
		{
			throw new RuntimeException("addShop Err" + e.getMessage());
		}

		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

//	private void addShopImg(Shop shop, File shopImg)
//	{
//		String dest= PathUtil.getShopImgPath(shop.getShopId());
//		String ShopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
//		shop.setShopImg(ShopImgAddr);
//	}
	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName)
	{
		String dest= PathUtil.getShopImgPath(shop.getShopId());
		String ShopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
		shop.setShopImg(ShopImgAddr);
	}

	@Override
	public Shop getByShopId(Long id)
	{
		return shopdao.queryByShopId(id);
	}

	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName)
	{
		if (shop == null || shop.getShopId() == null)
		{
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			try
			{
				
				//1.判断是否需要处理图片
				if (shopImgInputStream != null && fileName != null && !fileName.equals(""))
				{
					Shop tempShop =shopdao.queryByShopId(shop.getShopId());
					if(null != tempShop)
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					addShopImg(shop, shopImgInputStream, fileName);
				} 
				//2.更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopdao.updateShop(shop);
				if (effectedNum <= 0)
				{
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else
				{
					shop = shopdao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS,shop);
				}
			} catch (Exception e)
			{
				throw new RuntimeException("更新异常");
			}
		}
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize)
	{
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shops = shopdao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopdao.queryShopCount(shopCondition);
		ShopExecution shopExecution = new ShopExecution();
		if(shops != null)
		{
			shopExecution.setShops(shops);
			shopExecution.setCount(count);
		} else
		{
			shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return shopExecution;
	}

}
