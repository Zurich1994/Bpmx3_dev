package com.hotent.core.page;


import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PageNav
{
	private String strUrl;
	private PageBean _pv;
	private String _strPara="";
	private static long i = 0L;
	private HttpServletRequest _request;

	private int count = 8;
	

	/**
	 * 翻页导航，不打印页面导航
	 * 使用 getPage方法取得导航代码
	 * @param request
	 * @param page
	 * @throws UnsupportedEncodingException
	 */
	public PageNav(HttpServletRequest request, PageBean pageBean)
	{
		_request = request;
		_pv = pageBean;
		strUrl = request.getRequestURL().toString();
		_strPara = getPara(request);
		
	}



	/**
	 * 取得映射的参数
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String getPara(HttpServletRequest request)
	{
		String params = "";
		Enumeration e = request.getParameterNames();
		String key = "";
		String value = "";
		while (e.hasMoreElements())
		{
			key = (String) e.nextElement();
			if (!key.equals(_pv.getPageName()))
			{
				value = request.getParameter(key);
				if (value != null && !value.equals(""))
				{
					params += "&" + key + "=" + getValueByParameter(key);
				}
			}
		}
		return params;
	}

	/**
	 * 根据参数名称取得值
	 * @param parameter
	 * @return
	 */
	private String getValueByParameter(String parameter)
	{
		String[] values = _request.getParameterValues(parameter);
		String value = "";
		if (values.length == 1)
		{
			value = _request.getParameter(parameter);
			if (value == null)
				return "";
			value = value.trim();
		}
		else
		{
			int k = values.length;
			for (int i = 0; i < values.length; i++)
			{
				if (i == k - 1)
					value += values[i].trim();
				else
					value += values[i].trim() + ",";
			}
		}
		return value;
	}


	/**
	 * 取得数字分页
	 * 如果不直接打印到页面
	 * 样式定义如下：
	 * <div class='pageNumDiv>
	 * <span class='spanCurPage'><a href=''></a>
	 * <span class='spanPage'><a href=''></a>
	 * </div>
	 */
	public String getNumber()
	{
		int currentPage = _pv.getCurrentPage();
		List<Integer> list = PageUtils.getPageNumbers(_pv.getCurrentPage(), _pv.getTotalPage(), this.count);
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pageNav'>");
		String url = strUrl + "?page=%1$s" + _strPara;
		String curTmplate = "<span class='spanCurPage'><a href='" + url + "'>%1$s</a></span>";
		String tmplate = "<span class='spanPage'><a href='" + url + "'>%1$s</a></span>";
		for (Integer it : list)
		{
			if (currentPage == it.intValue())
				sb.append(String.format(curTmplate, it.intValue()));
			else
				sb.append(String.format(tmplate, it.intValue()));
		}
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * 取得分页控件代码。
	 * @param allRecord
	 * @param pagesInfo
	 * @param pageNav
	 * @param jumpPage
	 * @throws Exception
	 */
	public String getPage(boolean allRecord, boolean pagesInfo, boolean pageNav, boolean jumpPage) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pageNav'>");
		if (allRecord)
			sb.append(showTotalRecord());
		if (pagesInfo)
			sb.append(showPageInfo());
		if (pageNav)
			sb.append(showPageNav());
		if (jumpPage)
			sb.append(showJumpPage());
		sb.append("</div>");
		return sb.toString();
	}

	/**
	 * 取得分页控件代码。
	 * @return
	 * @throws Exception
	 */
	public String getPage() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pageNav'>");
		sb.append(showTotalRecord());
		sb.append(showPageInfo());
		sb.append(showPageNav());
		sb.append(showJumpPage());
		sb.append("</div>");
		return sb.toString();
	}
	
	public String getShortPage() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='pageNav'>");
	 
		sb.append(showPageNav());
		 
		sb.append("</div>");
		return sb.toString();
	}

	private String showJumpPage() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		i++;
		if (i > 100L)
			i = 0L;
		sb.append("<span class='jumpPage'>");
		sb.append(" 转到第<input type=\"text\" name=\"page" + i + "\" size=\"1\" class=\"input\" />页 <input type=\"button\" class=\"btnjump\" value=\"跳转\" onclick=\"goToPage" + i + "()\" /> ");
		sb.append("<script language=\"javascript\">");
		sb.append("function goToPage" + i + "(){");
		sb.append("value=document.all.page" + i + ".value;");
		sb.append("if(value.indexOf(\".\")==-1 && value.indexOf(\"-\")==-1 && value!==\"\" && !isNaN(value) && value<" + (_pv.getTotalPage() + 1) + "){");
		sb.append("location.assign('" + strUrl + "?page='" + "+value+'" + _strPara + "')");
		sb.append("}");
		sb.append("}");
		sb.append("</script>");
		sb.append("</span>");
		return sb.toString();
	}

	private String showPageInfo() throws Exception
	{
		return "<span class='pageInfo'> 第" + _pv.getCurrentPage() + "/" + _pv.getTotalPage() + "页 </span>";
	}

	private String showPageNav() throws Exception
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<span class='spanNav'>");
		if (_pv.getTotalPage() > 1)
			sb.append("<a href=\"" + strUrl + "?page=1" + _strPara + "\">第一页</a>");
		else
			sb.append("<a disabled='true' >第一页</a>");
		if (_pv.isHasPreviousPage())
			sb.append("<a href=\"" + strUrl + "?page=" + _pv.getPreviousPage() + _strPara + "\">上一页</a>");
		else
			sb.append("<a disabled='true' >上一页</a>");
		if (_pv.isHasNextPage())
		{
			sb.append("<a href=\"" + strUrl + "?page=" + _pv.getNextPage() + _strPara + "\">下一页</a>");
		}
		else
		{
			sb.append("<a disabled='true' >下一页</a>");
		}
		if (_pv.getTotalPage() > 1)
			sb.append("<a href=\"" + strUrl + "?page=" + _pv.getTotalPage() + _strPara + "\">最末页</a>");
		else
			sb.append("<a disabled='true' >最末页</a>");
		sb.append("</span>");
		return sb.toString();
	}

	private String showTotalRecord() throws Exception
	{
		return "<span class='totalRecord'>共" + _pv.getTotalCount() + "条记录 </span>";
	}
}
