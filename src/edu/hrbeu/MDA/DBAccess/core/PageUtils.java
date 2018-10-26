package edu.hrbeu.MDA.DBAccess.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 页码计算实用函数
 * @author hotent
 *
 */
public class PageUtils
{
	private PageUtils()
	{
	}

	/**
	 * 取得首页记录数
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public static int getFirstNumber(int currentPage, int pageSize)
	{
		if (pageSize <= 0)
			throw new IllegalArgumentException("[pageSize] must great than zero");
		return (currentPage - 1) * pageSize;
	}
	
	public static int getLastNumber(int currentPage,int pageSize,int totalCount)
	{
		int last=currentPage * pageSize;
		if(last > totalCount) return totalCount;
		return last;
	}

	/**
	 * 取得数字导航，例如 2,3,4,6
	 * @param currentPage 
	 * @param totalPage
	 * @param count
	 * @return
	 */
	public static List<Integer> getPageNumbers(int currentPage, int totalPage, int count)
	{
		int avg = count / 2;
		int startPage = currentPage - avg;
		if (startPage <= 0){
			startPage = 1;
		}
		int endPage = startPage + count - 1;
		if (endPage > totalPage){
			endPage = totalPage;
		}
		if (endPage - startPage < count){
			startPage = endPage - count;
			if (startPage <= 0){
				startPage = 1;
			}
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = startPage; i <= endPage; i++){
			result.add(new Integer(i));
		}
		return result;
	}

	/**
	 * 取得尾页页码
	 * @param totalCount  总的记录数
	 * @param pageSize	页大小
	 * @return
	 */
	public static int getTotalPage(int totalCount , int pageSize)
	{
		int result = totalCount  % pageSize == 0 ? totalCount  / pageSize : totalCount  / pageSize + 1;
		if (result <= 1)
			result = 1;
		return result;
	}

	/**
	 * 取得实际的页码
	 * @param currentPage	当前页
	 * @param pageSize		页大小
	 * @param totalCount	总记录数
	 * @return
	 */
	public static int getPageNumber(int currentPage, int pageSize, int totalCount)
	{
		if (currentPage <= 1)
		{
			return 1;
		}
		if (Integer.MAX_VALUE == currentPage || currentPage > getTotalPage(totalCount, pageSize))
		{ // last page
			return getTotalPage(totalCount, pageSize);
		}
		return currentPage;
	}
	
	/**
	 * LZC
	 */
	
	/**
	 * 获取分页的html
	 * @param pageBean
	 * @param url
	 * @param tableIdCode
	 * @param showExplain
	 * @param showPageSize
	 * @return
	 * @throws Exception
	 */
	public static String getPageHtml(PageBean pageBean, String url,
			String tableIdCode, boolean showExplain, boolean showPageSize)
			throws Exception {
		FreemarkEngine freemarkEngine = (FreemarkEngine) AppUtil
				.getBean("freemarkEngine");
		// 分页国际化
		//PageLanguage pageLanguage = PageLanguage.getPageLanguage(pageBean);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pageBean", pageBean);

		model.put("tableIdCode", tableIdCode);
		model.put("showExplain", showExplain);
		model.put("showPageSize", showPageSize);
		model.put("baseHref", url);
		//model.put("pageLanguage", pageLanguage);
		return freemarkEngine.mergeTemplateIntoString("page.ftl", model);
	}
	
	
}
