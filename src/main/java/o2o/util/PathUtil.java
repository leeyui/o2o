package o2o.util;


public class PathUtil
{
	private static String separator = System.getProperty("file.separator");
	//返回项目文件的根路径
	public static String getImgBasePath()
	{
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().contains("win"))
			basePath = "E:/o2oImage";
		else
			basePath = "/home/iamge";
		basePath = basePath.replace("/", separator);
		return basePath;
	}
	//根据项目需求返回子文件路径
	public static String getShopImgPath(long shopId)
	{
		String imagePath = "/upload/item/shop" + shopId + "/";
		return imagePath.replace("/", separator);
	}
}
