/**
 * 对象功能:附件 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-26 18:19:16
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysFile;

@Repository
public class SysFileDao extends BaseDao<SysFile> {
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return SysFile.class;
	}
	
	public List<SysFile> getFileAttch(QueryFilter fileter){
		return getBySqlKey("getAllPersonalFile", fileter);
	}
	/**
	 * 根据文件路径获取附件
	 * @param filePath
	 * @return
	 */
	public SysFile getByFilePath(String filePath) {
		
		return this.getUnique("getByFilePath", filePath);
	}
}