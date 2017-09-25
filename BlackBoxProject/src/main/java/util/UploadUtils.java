package util;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadUtils.class);

	/**
	 * ���� ���ε�
	 * @param uploadPath
	 * @param originalName
	 * @param fileData
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)
			throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;

		String savedPath = calcPath(uploadPath);
		
		logger.info(savedPath);
		
		File target = new File(uploadPath + savedPath, savedName);

		FileCopyUtils.copy(fileData, target);

		String formatName = originalName.substring(originalName.lastIndexOf('.')+1);

		String uploadedFileName = null;

		if(MediaUtils.getMediaType(formatName.toUpperCase()) != null){
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else{
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}

		return uploadedFileName;
	}
	public static String uploadCourseFile(String uploadPath, String originalName, byte[] fileData)
			throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = originalName;

		String savedPath = calcCoursePath(uploadPath, originalName);
		
		logger.info(savedPath);
		
		File target = new File(uploadPath + savedPath, savedName);

		FileCopyUtils.copy(fileData, target);

		String formatName = originalName.substring(originalName.lastIndexOf('.')+1);

		String uploadedFileName = null;

		if(MediaUtils.getMediaType(formatName.toUpperCase()) != null){
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else{
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		System.out.println(uploadedFileName);
		return uploadedFileName;
	}

	private static String makeIcon(String uploadPath, String path, String fileName)
			throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
	private static String calcCoursePath(String uploadPath, String originName){
		
		String savedPath = calcPath(uploadPath);
		String courseCodeName = originName.substring(0,originName.indexOf("_"));
		savedPath = savedPath + File.separator + courseCodeName;
		mkDir(uploadPath, savedPath);
		return savedPath;
	}

	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").
				format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").
				format(cal.get(Calendar.DATE));
		
		mkDir(uploadPath, yearPath, monthPath, datePath);

		return datePath;

	}

	private static void mkDir(String uploadPath, String... paths){

		if(new File(paths[paths.length-1]).exists()){
			return;
		}

		for(String path : paths){
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists()){
				dirPath.mkdir();
			}
		}

	}

	private static String makeThumbnail(String uploadPath, String path, String fileName)
			throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);

		String thumbnailName = uploadPath + path + File.separator +"s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
