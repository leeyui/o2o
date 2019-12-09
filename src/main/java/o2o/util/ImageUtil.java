package o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil
{
	public static void main(String[] args) throws IOException
	{
		//获取绝对路径
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(basePath);
		Thumbnails.of(new File("C:\\Users\\asus-pc\\Desktop\\p站\\62628015_p0.jpg"))
		.size(600, 600).watermark(Positions.BOTTOM_RIGHT,
				ImageIO.read(new File("E:/workspace/o2o/src/main/resources/images/item/headtitle/2017061320315746624.jpg")),
				0.8f).outputQuality(0.8f).toFile("E:/workspace/o2o/src/main/resources/images/item/headtitle/new.jpg");;
	}
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final Random random = new Random();
	
	public static String generateThumbnail(InputStream thumbnail,String fileName, String targetAddr)
	{
		String realFileName = getRondomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName+ extension;
		File file = new File(PathUtil.getImgBasePath() + relativeAddr);
		try
		{
			Thumbnails.of(thumbnail).size(600, 600).outputQuality(0.8f).toFile(file);
		} catch (IOException e)
		{
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
		
	}
	
	private static void makeDirPath(String targetAddr)
	{
		String fileRealName = PathUtil.getImgBasePath() + targetAddr;
		File dir = new File(fileRealName);
		if(!dir.exists())
			dir.mkdirs();
	}
	//获取上传文件的后缀名
//	private static String getFileExtension(File thumbnail)
//	{
//		String originalFileName = thumbnail.getAbsolutePath();
//		return originalFileName.substring(originalFileName.lastIndexOf("."));
//	}
	private static String getFileExtension(String fileName)
	{
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	//生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
	public static String getRondomFileName()
	{
		int r = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		String nowDateStr = simpleDateFormat.format(new Date());
		return nowDateStr + r;
	}
	
	/**
	 * storePath是文件的路径或者是目录的路径,
	 * 如果storePath是文件路径,则删除该文件,
	 * 若果storePath是目录路径,则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath)
	{
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists())
		{
			if (fileOrPath.isDirectory())
			{
				File files[] = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++)
				{
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
