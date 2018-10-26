package com.hotent.platform.service.ats;

import java.io.InputStream;

/**
 * 取卡数据接口
 * 
 * @author hugh zhuang
 * 
 */
public interface IAtsTakeCardRecordService {

	/**
	 * 取卡数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCardRecordByAccess(String filePath) throws Exception;

	/**
	 * 根据开始和结束取卡数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCardRecordByAccess(String filePath, String startDate,
			String endDate) throws Exception;

	/**
	 * 取卡数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCardRecordByExcel(InputStream inputStream, String startDate,
			String endDate);

	/**
	 * 取卡数据
	 * 
	 * @param endDate
	 * @param inputStream
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getCardRecordByText(InputStream inputStream, String filePath,
			String endDate) throws Exception;

}
