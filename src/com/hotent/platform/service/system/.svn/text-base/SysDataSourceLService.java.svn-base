package com.hotent.platform.service.system;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysDataSourceLDao;
import com.hotent.platform.model.system.SysDataSourceL;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-21 10:50:18
 * </pre>
 */
@Service
public class SysDataSourceLService extends BaseService<SysDataSourceL> {
	@Resource
	private SysDataSourceLDao dao;

	public SysDataSourceLService() {
	}
	
	public SysDataSourceL getByAlias(String alias){
		return dao.getUnique("getByAlias", alias);
	}
	
	@Override
	protected IEntityDao<SysDataSourceL, Long> getEntityDao() {
		return dao;
	}

	public boolean checkConnection(SysDataSourceL sysDataSourceL) {
		return checkConnection(reflect(sysDataSourceL), sysDataSourceL.getCloseMethod());
	}
	
	private boolean checkConnection(DataSource dataSource, String closeMethod) {
		boolean b = false;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

			// 调用关闭
			if (!StringUtil.isEmpty(closeMethod)) {
				String cp = closeMethod.split("\\|")[0];
				String mn = closeMethod.split("\\|")[1];

				try {
					Class<?> _class = Class.forName(cp);

					Method method = _class.getMethod(mn, null);
					method.invoke(null, null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return b;
	}
	/**
	 * 把一个sysDataSourceL映射出一个datasource
	 * @param sysDataSourceL
	 * @return 
	 * DataSource
	 * @exception 
	 * @since  1.0.0
	 */
	public DataSource reflect(SysDataSourceL sysDataSourceL) {

		try {
			// 获取对象
			Class<?> _class = null;
			_class = Class.forName(sysDataSourceL.getClassPath());
			javax.sql.DataSource sqldataSource = null;
			sqldataSource = (javax.sql.DataSource) _class.newInstance();// 初始化对象

			// 开始set它的属性
			String settingJson = sysDataSourceL.getSettingJson();
			JSONArray ja = JSONArray.fromObject(settingJson);

			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = ja.getJSONObject(i);

				// Class<?> c = Class.forName(jo.getString("type"));
				List<Object> list = tranBasicDataTypeClassAndValue(jo.getString("type"), jo.getString("value"));

				setter(sqldataSource, jo.getString("name"), list.get(1), (Class<?>) list.get(0));
			}

			// 如果有初始化方法，需要调用，必须是没参数的
			String initMethodStr = sysDataSourceL.getInitMethod();
			if (!StringUtil.isEmpty(initMethodStr)) {
				Method method = _class.getMethod(initMethodStr);
				method.invoke(sqldataSource);
			}

			return sqldataSource;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * java反射机制调用obj的setter方法
	 * 
	 * @param obj
	 *            ：对象
	 * @param att
	 *            ：属性名
	 * @param value
	 *            ：值
	 * @param type
	 *            ：值类型 void
	 * @exception
	 * @since 1.0.0
	 */
	private void setter(Object obj, String att, Object value, Class<?> type) {
		try {
			att = att.substring(0, 1).toUpperCase() + att.substring(1);
			// System.out.println(att);
			Method method = obj.getClass().getMethod("set" + att, type);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 翻译类名为class对象和根据把value翻译成指定类的值，className是常用的数据类型
	 * 
	 * @param className
	 *            :类名
	 * @param value
	 *            ：数值
	 * @return List<Object> ：list[0]是返回的class，list[1]是value
	 * @exception
	 * @since 1.0.0
	 */
	private List<Object> tranBasicDataTypeClassAndValue(String className, String value) {
		List<Object> list = new ArrayList<Object>();

		Object o = null;
		Class<?> c = null;
		if (className.equals("int")) {
			c = int.class;
			o = Integer.parseInt(value);
		} else if (className.equals("short")) {
			c = short.class;
			o = Short.parseShort(value);
		} else if (className.equals("long")) {
			c = long.class;
			o = Long.parseLong(value);
		} else if (className.equals("float")) {
			c = float.class;
			o = Float.parseFloat(value);
		} else if (className.equals("double")) {
			c = double.class;
			o = Double.parseDouble(value);
		} else if (className.equals("boolean")) {
			c = boolean.class;
			o = Boolean.parseBoolean(value);
		} else if (className.equals("java.lang.String")) {
			c = String.class;
			o = value;
		}

		list.add(c);
		list.add(o);

		return list;
	}

}
