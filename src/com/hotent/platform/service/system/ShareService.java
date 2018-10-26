package com.hotent.platform.service.system;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.hotent.core.util.PinyinUtil;

/**
 * 对象功能:通用service
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2012-09-20 13:57
 */
@Service
public class ShareService 
{
	public ShareService()
	{
	}
	
	
	
	/**
	 * 汉字转为拼音
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getPingyin(String str) throws UnsupportedEncodingException{
		String nodeKey=PinyinUtil.getPinYinHeadCharFilter(str);
		return nodeKey;
	}
}
