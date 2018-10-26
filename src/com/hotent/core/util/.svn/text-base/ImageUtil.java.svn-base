package com.hotent.core.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

//import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理工具类
 * 
 * @author hotent
 * 
 */
public class ImageUtil {
	public static Log log = LogFactory.getLog(ImageUtil.class);

	/**
	 * 根据二进制数组创建图片
	 * @param imagedata
	 * @return
	 */
	public static Image loadImage(byte[] imagedata) {
		Image image = Toolkit.getDefaultToolkit().createImage(imagedata);
		return image;
	}

	/**
	 * 根据文件名加载图片
	 * @param filename
	 * @return
	 */
	public static Image loadImage(String filename) {
		return Toolkit.getDefaultToolkit().getImage(filename);
	}

	public static BufferedImage loadImage(File file) {
		BufferedImage bufferedImage = null;
		try {
			
			bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return bufferedImage;
	}

	public static ImageReader getImageReader(InputStream is,String formatName) throws IOException {
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName(formatName);
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		reader.setInput(iis, true);
		return reader;
	}
	
	public static ImageReader getImageReader(File file) {
		String formatName = getFileSuffix(file);
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName(formatName);
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		reader.setInput(iis, true);
		return reader;
	}

	/**
	 * 取得文件后缀
	 * @param file
	 * @return
	 */
	private static String getFileSuffix(File file) {
		String fileName = file.getName();
		int index = fileName.indexOf(".");
		String formatName = fileName.substring(index + 1);
		return formatName;
	}

	/**
	 * 剪切图片
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param file 输入图片文件
	 * @param output 输出图片文件
	 */
	public static void cutImage(int x, int y, int width, int height,
			File file,File output) {
		String formatName = getFileSuffix(file);
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName(formatName);
		ImageReader reader = (ImageReader) readers.next();
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle sourceRegion = new Rectangle(x, y, width, height);
		param.setSourceRegion(sourceRegion);
		try {
			BufferedImage bufferedImage = reader.read(0, param);
			ImageIO.write(bufferedImage, ImageUtil.getFileSuffix(file), output);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static InputStream cutImage(int x, int y, int width, int height,
			ImageReader reader) {
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle sourceRegion = new Rectangle(x, y, width, height);
		param.setSourceRegion(sourceRegion);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedImage bufferedImage = reader.read(0, param);
			ImageIO.write(bufferedImage, "png",baos);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			return bais;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public static ImageIcon getImageIcon(File file){
		String filename = file.getAbsolutePath();
		return new ImageIcon(filename);
	}
	
	/**
	 * 创建水印效果。
	 * @param srcFile 原始图片文件
	 * @param waterFile 附加图片文件
	 * @param compositeFile 合成后的图片文件
	 * @throws IOException 
	 */
	public static void createWaterMark(File srcFile,File waterFile,File compositeFile) throws IOException{
		Image theImg = new ImageIcon(srcFile.getAbsolutePath()).getImage();
		Image waterImg = new ImageIcon(waterFile.getAbsolutePath()).getImage();
		int width = theImg.getWidth(null);
		int height = theImg.getHeight(null);
		int w = waterImg.getWidth(null);
		int h = waterImg.getHeight(null);
		
		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.setColor(Color.WHITE);//设置笔刷白色
		g.fillRect(0,0,width,height);//填充整个屏幕 
		g.setColor(Color.BLACK); //设置笔刷
		
		g.drawImage(theImg, 0, 0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
		width = width-w;
		height = height-h;
		g.drawImage(waterImg, width, height, null);
		g.dispose();
		FileOutputStream fos = null;
		ImageOutputStream ios=null;
		JPEGImageWriter imageWriter=null;
		try {
			fos = new FileOutputStream(compositeFile);
			imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpeg").next();
			
			ios = ImageIO.createImageOutputStream(fos);
			imageWriter.setOutput(ios);
			
			//and metadata
		    IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(bimage), null);
			
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
	        jpegParams.setCompressionQuality(1f);
	        
	        imageWriter.write(imageMetaData, new IIOImage(bimage, null, null), null);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ios.close();
			fos.close();
			imageWriter.dispose();
		}
	}
	
	
	
	//通过流返回一个画了矩形边框的图片流对象
	public static InputStream createRectangle(InputStream inputStream, int x, int y,int w,int h) {
			BufferedImage bimage = null;
			try {
				bimage = ImageIO.read(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			Graphics2D g = bimage.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
			g.setColor(Color.RED);

			//画一矩形边框
			g.drawRect(x, y, w, h);
			g.dispose();
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bimage, "PNG",baos);
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				return bais;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
	
	//在一个指定的图片上画一个矩形边框
//	public static void createRectangle(File srcFile,int x,int y){
//		File compositeFile = new File("e:\\temp.JPG");
//		Image theImg = new ImageIcon(srcFile.getAbsolutePath()).getImage();
//		int width = theImg.getWidth(null);
//		int height = theImg.getHeight(null);
//		BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g = bimage.createGraphics();
//		g.setBackground(Color.WHITE);
//		g.drawImage(theImg, 0, 0, null);
//		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
//		g.setColor(Color.GREEN);
//		int w = 91;
//		int h = 54;
//		//画一矩形边框
//		g.drawRect(x, y, w, h);
//		g.dispose();
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(compositeFile);
//			
//			
//			
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
//			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
//			param.setQuality(80f, true);
//			encoder.encode(bimage);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//					throw new RuntimeException(e);
//				}
//        	}
//		}
//	}
	
	
	public static void main(String[] args) throws Exception {
//		File srcFile = new File("e:\\leave.png");
//		int x = 103;
//		int y = 252;
//		createRectangle(srcFile, x, y);
		testCompositeFile();
		
	}

	public static void testCompositeFile() throws IOException {
		File srcFile = new File("e:\\origin.jpg");
		File waterFile = new File("e:\\logo.jpg");
		File compositeFile = new File("e:\\temp.jpg");
		createWaterMark(srcFile, waterFile,compositeFile);
	}

	public static void tsetReaderImageIconTime() {
		String dir = "F:\\picture\\";
		long start = System.currentTimeMillis();
		File[] files = new File(dir).listFiles();
		for (File item : files) {
			ImageIcon imageIcon = getImageIcon(item);
			int width = imageIcon.getIconWidth();
			int height = imageIcon.getIconHeight();
			log.info("图片的宽度：" + width);
			log.info("图片的高度：" + height);
		}
		long end = System.currentTimeMillis();
		log.info("所花时间：" + (end - start) / 1000 + "秒");
	}

	public static void testCutImage() throws IOException {
		File file = new File("e:\\vehicle_examine_info.png");
		File output = new File("e:\\vehicle_examine_info.png");
		ImageReader reader = ImageUtil.getImageReader(file);
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex);
		ImageUtil.cutImage(0, 0, width, height,
				file,output);
		
	}

	public static void testReaderImageTime() throws IOException {
		String dir = "F:\\picture\\";
		long start = System.currentTimeMillis();
		File[] files = new File(dir).listFiles();
		for (File item : files) {
			ImageReader reader = ImageUtil.getImageReader(item);
			int imageIndex = 0;
			int width = reader.getWidth(imageIndex);
			int height = reader.getHeight(imageIndex);
			log.info("图片的宽度：" + width);
			log.info("图片的高度：" + height);
		}
		long end = System.currentTimeMillis();
		log.info("所花时间：" + (end - start) / 1000 + "秒");
	}

	
	
	
	/**
	 * 压缩图片方法（用字节输入流）  当percentage为false时图片按使用都直接给定的宽和高，为true 时就按原来宽度的比例缩小图片（高度，传入的参数没有效，但高度会跟着宽度的比例缩小）
	 * 
	 * @param b
	 *            将要压缩的图片字节流
	 * @param newFile
                                                     将要压缩的图片的新路径           
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param quality
	 *            压缩清晰度 建议为1.0
	 * @param percentage
	 *            是否等比压缩 若true宽高比率将将自动调整
	 * @return 如果处理正确返回压缩后的文件名  null则参数可能有误
	 */
	public static String doCompressByByte(byte[] b, String newFile,int width, int height,float quality, boolean percentage){
		if (b.length > 0 && width > 0 ){
			InputStream  in_nocode = new ByteArrayInputStream(b);   
			String newImage = null;
			try{
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(in_nocode);
				
				int new_w = srcFile.getWidth(null); //得到源图宽
				int new_h = srcFile.getHeight(null); //得到源图长

				if (percentage)
				{
					// 为等比缩放计算输出的图片宽度及高度
					double rate = ((double) srcFile.getWidth(null))
							/ (double) width + 0.1;
					/*double rate2 = ((double) srcFile.getHeight(null))
							/ (double) height + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;*/
					new_w = (int) (((double) srcFile.getWidth(null)) / rate);
					new_h = (int) (((double) srcFile.getHeight(null)) / rate);
				}else{
					//当按用户高度生成时，没有高度会返回
					if(height<=0){
						return null;
					}
					
					int num = new_w/40;
					if(width>0){
						new_w = width;
					}else{
						new_w = new_w/40;
						if(new_w<=0){
							new_w = 40;
						}
					}
					if(new_h>0){
						new_h = height;
					}else{
						new_h = (height/num)*30;
						if(new_h<=0){
							new_h = 30;
						}
					}
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

				/* 压缩后的文件全路径 */
				newImage = newFile;

				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);

				out.close();
				srcFile.flush();

			} catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			return newImage;
		} else{
			return null;
		}		
	}
	
	
	/**
	 * 压缩图片方法（用文件全路径）
	 * 
	 * @param oldFile
	 *            将要压缩的图片路径
	 * @param newFile
	 *            压缩图片后路径            
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param quality
	 *            压缩清晰度 建议为1.0
	 * @param percentage
	 *            是否等比压缩 若true宽高比率将将自动调整
	 * @return 如果处理正确返回压缩后的文件名  null则参数可能有误
	 */
	public static String doCompressByPath(String oldFile,String newFile, int width, int height,float quality, boolean percentage){
		if (oldFile != null && width > 0 && height > 0){
			String newImage = null;
			try{
				File file = new File(oldFile);
				// 文件不存在
				if (!file.exists()){
					return null;
				}
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(file);
				int new_w = width;
				int new_h = height;
				if (percentage){
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null))
							/ (double) width + 0.1;
					double rate2 = ((double) srcFile.getHeight(null))
							/ (double) height + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					new_w = (int) (((double) srcFile.getWidth(null)) / rate);
					new_h = (int) (((double) srcFile.getHeight(null)) / rate);
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

				/* 压缩后的文件全路径 */
				newImage = newFile;

				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);

				out.close();
				srcFile.flush();

			} catch (FileNotFoundException e){
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
			return newImage;
		} else{
			return null;
		}
	}

	
	
	  /** *//**
     * 把字节数组保存为一个文件
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

	
}
