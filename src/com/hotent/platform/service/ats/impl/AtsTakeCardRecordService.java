package com.hotent.platform.service.ats.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsCardRecordDao;
import com.hotent.platform.model.ats.AtsCardRecord;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.service.ats.IAtsTakeCardRecordService;

@Service
public class AtsTakeCardRecordService implements IAtsTakeCardRecordService {

	@Resource
	AtsCardRecordDao atsCardRecordDao;

	@Override
	public void getCardRecordByAccess(String filePath) throws Exception {
		ResultSet rs = null;
		try {
			Statement st = getAccessConnection(filePath);
			rs = st.executeQuery("SELECT u.BADGENUMBER BADGENUMBER,c.CHECKTIME CHECKTIME FROM USERINFO u ,CHECKINOUT c where u.USERID=c.USERID ");
			getList(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
			// TODO: handle exception
		} finally {
			if (rs != null)
				rs.close();
			FileUtil.deleteFile(filePath);
		}

	}

	@Override
	public void getCardRecordByAccess(String filePath, String startDate,
			String endDate) throws Exception {
		ResultSet rs = null;
		Statement st = null;
		try {
			st = getAccessConnection(filePath);
			rs = st.executeQuery("SELECT u.BADGENUMBER BADGENUMBER,c.CHECKTIME CHECKTIME FROM USERINFO u ,CHECKINOUT c where u.USERID=c.USERID  AND c.CHECKTIME>=  #"
					+ startDate + "# AND  c.CHECKTIME<=  #" + endDate + "# ");
			getList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			FileUtil.deleteFile(filePath);
		}
	}

	private void getList(ResultSet rs) throws Exception {
		while (rs.next()) {
			AtsCardRecord atsCardRecord = new AtsCardRecord();
			Date checkDate = rs.getTimestamp("CHECKTIME");
			String badgeNumber = rs.getString("BADGENUMBER");
			atsCardRecord.setCardDate(checkDate);
			atsCardRecord.setCardNumber(badgeNumber);
			atsCardRecord.setCardSource(AtsConstant.CARD_SOURCE_ACCESS);
			atsCardRecord.setId(UniqueIdUtil.genId());
			atsCardRecordDao.add(atsCardRecord);
		}
	}

	private Statement getAccessConnection(String filePath) throws Exception {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"
				+ filePath);
		return conn.createStatement();
	}

	@Override
	public void getCardRecordByExcel(InputStream inputStream, String startDate,
			String endDate) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				HSSFSheet st = workbook.getSheetAt(sheetIndex);
				// 第一行为标题，不取
				for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
					HSSFRow row = st.getRow(rowIndex);
					if (row == null)
						continue;
					AtsCardRecord atsCardRecord = new AtsCardRecord();
					for (int columnIndex = 0; columnIndex <= row
							.getLastCellNum(); columnIndex++) {
						HSSFCell cell = row.getCell(columnIndex);
						String o = ExcelUtil.getCellFormatValue(cell);
						if (columnIndex == 0) {// 考勤编号
							if (BeanUtils.isEmpty(o))
								break;
							atsCardRecord.setCardNumber(o);
						} else if (columnIndex == 1) {// 打卡日期
							atsCardRecord.setCardDate(DateFormatUtil.parse(o));
						} else if (columnIndex == 2) {// 打卡时间
							Date d = DateUtil.getTime(
									atsCardRecord.getCardDate(),
									DateFormatUtil.parseTime(o));
							atsCardRecord.setCardDate(d);
						} else if (columnIndex == 3) {// 打卡位置
							atsCardRecord.setCardPlace(o);
						}
					}
					if (BeanUtils.isEmpty(atsCardRecord.getCardNumber()))
						continue;

					Long id = UniqueIdUtil.genId();
					atsCardRecord.setId(id);
					atsCardRecord.setCardSource(AtsConstant.CARD_SOURCE_EXCEL);
					atsCardRecordDao.add(atsCardRecord);
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

	@Override
	public void getCardRecordByText(InputStream inputStream, String filePath,
			String endDate) throws Exception {
		try {
			String encoding = "GBK";
			InputStreamReader read = new InputStreamReader(inputStream,
					encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] ary = lineTxt.split(",");
				AtsCardRecord atsCardRecord = new AtsCardRecord();
				String date = ary[1];
				atsCardRecord.setCardDate(DateFormatUtil.parseDateTime(date));
				atsCardRecord.setCardNumber(ary[0]);
				atsCardRecord.setCardSource(AtsConstant.CARD_SOURCE_TEXT);
				atsCardRecord.setId(UniqueIdUtil.genId());
				atsCardRecordDao.add(atsCardRecord);
			}
			read.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) throws Exception {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:ucanaccess://C:/att2000.mdb");
		Statement s = conn.createStatement();
//		String startDate = "2015-02-01";
//		String endDate = "2015-02-28";
		ResultSet rs = s
				.executeQuery("SELECT u.BADGENUMBER BADGENUMBER,c.CHECKTIME CHECKTIME FROM USERINFO u ,CHECKINOUT c where u.USERID=c.USERID ");
		while (rs.next()) {
			Date checktime1 = rs.getTimestamp("CHECKTIME");

			System.out.println(checktime1);
		}

	}

}
