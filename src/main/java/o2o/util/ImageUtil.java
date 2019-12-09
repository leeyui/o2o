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
		//��ȡ����·��
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(basePath);
		Thumbnails.of(new File("C:\\Users\\asus-pc\\Desktop\\pվ\\62628015_p0.jpg"))
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
			throw new RuntimeException("��������ͼʧ�ܣ�" + e.toString());
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
	//��ȡ�ϴ��ļ��ĺ�׺��
//	private static String getFileExtension(File thumbnail)
//	{
//		String originalFileName = thumbnail.getAbsolutePath();
//		return originalFileName.substring(originalFileName.lastIndexOf("."));
//	}
	private static String getFileExtension(String fileName)
	{
		
		return fileName.substring(fileName.lastIndexOf("."));
	}
	//��������ļ�������ǰ������ʱ����+��λ�������Ϊ����ʵ����Ŀ�з�ֹ�ļ�ͬ�������еĴ���
	public static String getRondomFileName()
	{
		int r = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		String nowDateStr = simpleDateFormat.format(new Date());
		return nowDateStr + r;
	}
	
	/**
	 * storePath���ļ���·��������Ŀ¼��·��,
	 * ���storePath���ļ�·��,��ɾ�����ļ�,
	 * ����storePath��Ŀ¼·��,��ɾ����Ŀ¼�µ������ļ�
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
