package com.hotent.platform.service.system;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysDataSourceDao;
import com.hotent.platform.model.system.SysDataSource;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-21 10:50:18
 * </pre>
 */
@Service
public class SysDataSourceService extends BaseService<SysDataSource> {
	protected static final Logger LOGGER = LoggerFactory.getLogger(SysDataSourceService.class);
	@Resource
	private SysDataSourceDao dao;

	public SysDataSourceService() {
		
	}
	
	public SysDataSource getByAlias(String alias){
		return dao.getUnique("getByAlias", alias);
	}
	
	@Override
	protected IEntityDao<SysDataSource, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 验证连接。
	 * @param sysDataSource
	 * @return
	 */
	public boolean checkConnection(SysDataSource sysDataSource) {
		return checkConnection(getDsFromSysSource(sysDataSource), sysDataSource.getCloseMethod());
	}
	
	private boolean checkConnection(DataSource dataSource, String closeMethod) {
		boolean b = false;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			b = true;
		} catch (Exception e) {
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
	 * 
	 * 利用Java反射机制把dataSource成javax.sql.DataSource对象
	 * 
	 * @param sysDataSource
	 * @param dataSourcePool
	 * @return javax.sql.DataSource
	 */
	public DataSource getDsFromSysSource(SysDataSource sysDataSource) {

		try {
			// 获取对象
			Class<?> _class = null;
			_class = Class.forName(sysDataSource.getClassPath());
			javax.sql.DataSource sqldataSource = null;
			sqldataSource = (javax.sql.DataSource) _class.newInstance();// 初始化对象

			// 开始set它的属性
			String settingJson = sysDataSource.getSettingJson();
			JSONArray ja = JSONArray.fromObject(settingJson);

			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				Object value = BeanUtils.convertByActType(jo.getString("type"), jo.getString("value"));
				BeanUtils.setProperty(sqldataSource, jo.getString("name"), value);
			}

			// 如果有初始化方法，需要调用，必须是没参数的
			String initMethodStr = sysDataSource.getInitMethod();
			if (!StringUtil.isEmpty(initMethodStr)) {
				Method method = _class.getMethod(initMethodStr);
				method.invoke(sqldataSource);
			}

			return sqldataSource;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}

		return null;
	}


	

}
