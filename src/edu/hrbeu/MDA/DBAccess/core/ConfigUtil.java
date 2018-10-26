package edu.hrbeu.MDA.DBAccess.core;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 配置文件读取器。
 * 配置文件类在classes目录下的conf/viewconfig.xml文件。
 * @author hotent
 *
 */
public class ConfigUtil {
	
	private Document doc=null;
	private static ConfigUtil config=null;
	
	private static Lock lock = new ReentrantLock();

	private ConfigUtil()
	{
		InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream("conf/viewconfig.xml");
		doc=Dom4jUtil.loadXml(is);
	}
	
	/**
	 * 单例模式，获取类的示例。
	 * @return
	 */
	public static ConfigUtil getInstance()
	{
		if(config==null)
		{
			lock.lock();
			try{
				if(config==null)
					config=new ConfigUtil();
			}
			finally{
				lock.unlock();
			}
		}
		return config;
	}
	
	/**
	 * 根据分类和实际的视图id取得视图页面
	 * @param category
	 * @param id
	 * @return
	 */
	public String getValue(String category,String id)
	{
		String template="category[@id='%s']/view[@name='%s']";
		String filter=String.format(template, category,id);
		Element root= doc.getRootElement();
		Element el=(Element)root.selectSingleNode(filter);
		if(el!=null)
			return el.attributeValue("value");
		return "";
	}
	
	/**
	 * 静态方法读取配置。
	 * @param category
	 * @param id
	 * @return
	 */
	public static String getVal(String category,String id)
	{
		return ConfigUtil.getInstance().getValue(category, id);
	}
	

	
	
	
}
