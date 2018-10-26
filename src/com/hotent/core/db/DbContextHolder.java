package  com.hotent.core.db;

/**
 * spring数据源，设置当前数据源。<br>
 * 
 * 设置方法：<br/>
 * <pre>
 * 		DbContextHolder.setDbType("1");
 *		ApplicationContext c=new ClassPathXmlApplicationContext(locations);
 *		RoleService service= c.getBean(RoleService.class);
 *		service.save();
 * </pre>
 */
public class DbContextHolder
{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 设置当前数据库。
	 * @param dbType
	 */
	public static void setDbType(String dbType)
	{
		contextHolder.set(dbType);
	}

	/**
	 * 取得当前数据源。
	 * @return
	 */
	public static String getDbType()
	{
		String str = (String) contextHolder.get();
		if (null == str || "".equals(str))
			str = "1";
		return str;
	}
	
	/**
	 * 清除上下文数据
	 */
	public static void clearDbType()
	{
		contextHolder.remove();
	}
	
	
}
