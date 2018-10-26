package com.hotent.platform.service.ats;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.platform.dao.ats.AtsCardRecordDao;
import com.hotent.platform.model.ats.AtsCardRecord;
import com.hotent.platform.service.ats.impl.AtsTakeCardRecordService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * <pre>
 * 对象功能:打卡记录 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 11:21:21
 * </pre>
 */
@Service
public class AtsCardRecordService extends BaseService<AtsCardRecord> {
	@Resource
	private AtsCardRecordDao dao;
	@Resource
	private AtsTakeCardRecordService atsTakeCardRecordService;

	public AtsCardRecordService() {
	}

	@Override
	protected IEntityDao<AtsCardRecord, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 打卡记录 信息
	 * 
	 * @param atsCardRecord
	 */
	public void save(AtsCardRecord atsCardRecord) {
		Long id = atsCardRecord.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsCardRecord.setId(id);
			this.add(atsCardRecord);
		} else {
			this.update(atsCardRecord);
		}
	}

	public void importText(InputStream is, String startDate, String endDate)
			throws Exception {
		atsTakeCardRecordService.getCardRecordByText(is, startDate, endDate);
	}

	public void importExcel(InputStream is, String startDate, String endDate)
			throws Exception {
		atsTakeCardRecordService.getCardRecordByExcel(is, startDate, endDate);
	}

	public void importAccess(MultipartFile multipartFile, String startDate,
			String endDate) throws Exception {
		String realFilePath = StringUtil.trimSufffix(ServiceUtil.getBasePath()
				.toString(), File.separator)
				+ File.separator
				+ "attachFiles"
				+ File.separator
				+ "importAccess" + File.separator;
		String originalFilename = multipartFile.getOriginalFilename();
		String destPath = realFilePath + originalFilename;

		ZipUtil.createFilePath(destPath, originalFilename);
		File file = new File(destPath);
		if (file.exists())
			file.delete();
		multipartFile.transferTo(file);

		if (BeanUtils.isEmpty(startDate) || BeanUtils.isEmpty(endDate)) {
			atsTakeCardRecordService.getCardRecordByAccess(destPath);
		} else {
			atsTakeCardRecordService.getCardRecordByAccess(destPath, startDate,
					endDate);
		}
	}

	public List<AtsCardRecord> getByCardNumber(String cardNumber) {
		return dao.getByCardNumber(cardNumber);
	}

	public Map<String, Set<Date>> getByCardNumberMap(String cardNumber,
			Date startTime, Date endTime) {
		List<AtsCardRecord> list = dao.getByCardNumberCardDate(cardNumber,
				startTime, endTime);
		Map<String, Set<Date>> map = new HashMap<String, Set<Date>>();
		for (AtsCardRecord atsCardRecord : list) {
			String dateStr = DateFormatUtil.formatDate(atsCardRecord
					.getCardDate());
			Set<Date> list1 = map.get(dateStr);
			if (BeanUtils.isEmpty(list1))
				list1 = new LinkedHashSet<Date>();
			list1.add(atsCardRecord.getCardDate());
			map.put(dateStr, list1);
		}
		return map;
	}

}
