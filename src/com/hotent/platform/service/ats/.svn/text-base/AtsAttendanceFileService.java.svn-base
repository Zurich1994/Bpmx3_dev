package com.hotent.platform.service.ats;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.ats.AtsAttenceCalculateDao;
import com.hotent.platform.dao.ats.AtsAttenceGroupDetailDao;
import com.hotent.platform.dao.ats.AtsAttendanceFileDao;
import com.hotent.platform.dao.ats.AtsScheduleShiftDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.ats.AtsAttencePolicy;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * <pre>
 * 对象功能:考勤档案 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-20 09:11:05
 * </pre>
 */
@Service
public class AtsAttendanceFileService extends BaseService<AtsAttendanceFile> {
	@Resource
	private AtsAttendanceFileDao dao;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private AtsAttenceGroupDetailDao atsAttenceGroupDetailDao;
	@Resource
	private AtsAttenceCalculateDao atsAttenceCalculateDao;
	@Resource
	private AtsScheduleShiftDao atsScheduleShiftDao;
	@Resource
	private AtsShiftInfoService atsShiftInfoService;
	@Resource
	private AtsAttencePolicyService atsAttencePolicyService;
	@Resource
	private AtsHolidayPolicyService atsHolidayPolicyService;

	public AtsAttendanceFileService() {
	}

	@Override
	protected IEntityDao<AtsAttendanceFile, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 考勤档案 信息
	 * 
	 * @param atsAttendanceFile
	 */
	public void save(AtsAttendanceFile atsAttendanceFile) {
		Long id = atsAttendanceFile.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsAttendanceFile.setId(id);
			this.add(atsAttendanceFile);
		} else {
			this.update(atsAttendanceFile);
		}
	}

	public List<AtsAttendanceFile> getByAttendPolicy(Long attendPolicy) {
		return dao.getByAttendPolicy(attendPolicy);
	}

	public AtsAttendanceFile getByAccount(String account) {
		return dao.getByAccount(account);
	}

	public List<AtsAttendanceFile> getList(QueryFilter filter) {
		return dao.getList(filter);
	}

	public List<AtsAttendanceFile> getNoneCalList(QueryFilter filter) {
		return dao.getNoneCalList(filter);
	}

	public List<AtsAttendanceFile> getByUserId(Long userId) {
		return dao.getByUserId(userId);
	}

	public List<SysUser> getDisUserList(QueryFilter queryFilter) {
		return sysUserDao.getDisUserList(queryFilter);
	}

	@Override
	public void delByIds(Long[] ids) {
		for (Long id : ids) {
			//删除分组
			atsAttenceGroupDetailDao.delByFileId(id);
			//删除计算
			atsAttenceCalculateDao.delByFileId(id);
			//删除排班
			atsScheduleShiftDao.delByFileId(id);
			this.delById(id);
		}
	}

	/**
	 * 保存新增
	 * 
	 * @param aryId
	 * @param atsAttendanceFile
	 * @param request
	 */
	public void saveAdd(Long[] aryId, AtsAttendanceFile atsAttendanceFile,
			HttpServletRequest request) {
		if (BeanUtils.isNotEmpty(aryId)) {
			for (Long userId : aryId) {
				SysUser sysUser = sysUserDao.getById(userId);
				this.saveAddFile(atsAttendanceFile, sysUser);
			}
		} else {
			List<SysUser> list = sysUserDao.getDisUserList(new QueryFilter(
					request, false));
			for (SysUser sysUser : list) {
				this.saveAddFile(atsAttendanceFile, sysUser);
			}
		}

	}

	private void saveAddFile(AtsAttendanceFile atsAttendanceFile,
			SysUser sysUser) {
		AtsAttendanceFile file = new AtsAttendanceFile();
		BeanUtils.copyNotNullProperties(file, atsAttendanceFile);
		file.setUserId(sysUser.getUserId());
		file.setCardNumber(sysUser.getAccount());
		this.save(file);
	}

	public void importExcel(InputStream inputStream) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				HSSFSheet st = workbook.getSheetAt(sheetIndex);
				// 第一行为标题，不取
				for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
					HSSFRow row = st.getRow(rowIndex);
					if (row == null)
						continue;
					AtsAttendanceFile file = new AtsAttendanceFile();
					for (int columnIndex = 0; columnIndex <= row
							.getLastCellNum(); columnIndex++) {
						HSSFCell cell = row.getCell(columnIndex);
						String o = ExcelUtil.getCellFormatValue(cell);
						if (BeanUtils.isEmpty(o)
								&& (columnIndex == 0 || columnIndex == 2
										|| columnIndex == 3 || columnIndex == 4|| columnIndex == 5)) {
							String  v = "员工编号";
							if(columnIndex == 0){
								v = "员工编号";
							}else if(columnIndex == 2){
								v = "考勤编号";
							}else if(columnIndex == 3){
								v = "考勤制度";
							}else if(columnIndex == 4){
								v = "假期制度";
							}else if(columnIndex == 4){
								v = "默认班次";
							}
							MsgUtil.addMsg(MsgUtil.WARN, "第"+(rowIndex+1) +"行,"+v+"为空值,该行导入失败");
							file = null;
							break;
						}
						
						if (columnIndex == 0) {// 员工编号
							SysUser sysUser = sysUserDao.getByAccount(o);
							if(BeanUtils.isEmpty(sysUser)){
								MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行,找不到该员工编号:"+o+",该行导入失败");
								file =null;
								break;
							}
							file.setUserId(sysUser.getUserId());
						} else if (columnIndex == 2) {// 考勤编号
							file.setCardNumber(o);
						} else if (columnIndex == 3) {//考勤制度
							AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService.getByName(o);
							if (BeanUtils.isEmpty(atsAttencePolicy)) {
								MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行,找不到该考勤制度:"+o+",该行导入失败");
								file = null;
								break;
							}
							file.setAttencePolicy(atsAttencePolicy.getId());
						} else if (columnIndex == 4) {//假期制度
							AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService.getByName(o);
							if (BeanUtils.isEmpty(atsHolidayPolicy)) {
								MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行,找不到该假期制度:"+o+",该行导入失败");
								file = null;
								break;
							}
							file.setHolidayPolicy(atsHolidayPolicy.getId());
						} else if (columnIndex == 5) {//默认班次
							AtsShiftInfo atsShiftInfo = atsShiftInfoService
									.getByShiftName(o);
							if (BeanUtils.isEmpty(atsShiftInfo)) {
								MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行,找不到该班次:"+o+",该行导入失败");
								file = null;
								break;
							}
							file.setDefaultShift(atsShiftInfo.getId());
						}
					}
					if (BeanUtils.isEmpty(file))
						continue;
					Long id = UniqueIdUtil.genId();
					file.setId(id);
					
					//xxx
					MsgUtil.addMsg(MsgUtil.SUCCESS, "第"+(rowIndex+1) +"行，成功导入");
					dao.add(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					inputStream = null;
					e.printStackTrace();
				}
			}

		}
		
	}

	public List<AtsAttendanceFile> getAllList(QueryFilter queryFilter) {
		return dao.getAllList(queryFilter) ;
	}

}
