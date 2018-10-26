package com.hotent.core.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 重写CommonsMultipartResolver类。<br>
 * 用于捕获SizeLimitExceededException错误。<br>
 * 使用 request.getAttribute("SizeLimitExceededException")获取 。<br>
 * 示例：
 * String hasError=(String)request.getAttribute(CustomerMultipartResolver.SizeLimitExceededException);
 * if(StringUtil.isNotEmpty(hasError)){
 * }
 * @author hotent
 *
 */
public class CustomerMultipartResolver extends CommonsMultipartResolver {
	
	public static String SizeLimitExceededException="SizeLimitExceededException";
	/**
	 * 重parseRequest方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		try {
			List fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			request.setAttribute(SizeLimitExceededException, "true");
			return parseFileItems(new ArrayList(), encoding);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}

}
