package com.hotent.platform.dao.file;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.file.FileManage;

/**
 * <pre>
 * 对象功能:文件管理 DAO类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Repository
public class FileManageDao extends BaseDao<FileManage> {

	@Override
	public Class getEntityClass() {
		return FileManage.class;
	}

	/**
	 * 
	 * @param lAryId
	 * @return
	 */
	public List<FileManage> getAllByIds(Long[] lAryId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileIds", lAryId);
		return (List<FileManage>) this.getBySqlKey("getAllByIds", params);
	}

	public void savePostPermis(Long id, Long fileId, String postId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("fileId", fileId);
		params.put("postId", postId);
		this.getBySqlKey("savePost",params);
	}

	public void saveRolePermis(Long id, Long fileId, String roleId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("fileId", fileId);
		params.put("roleId", roleId);
		this.getBySqlKey("saveRole",params);
	}

	public void saveUserPermis(Long id, Long fileId, String userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		params.put("fileId", fileId);
		params.put("userId", userId);
		this.getBySqlKey("saveUser",params);
	}
	
	public void delPostById(Long fileId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("fileId", fileId);
		this.delBySqlKey("delPostById", params);
	}
	public void delRoleById(Long fileId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("fileId", fileId);
		this.delBySqlKey("delRoleById", params);
	}
	public void delUserById(Long fileId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("fileId", fileId);
		this.delBySqlKey("delUserById", params);
	}

}
