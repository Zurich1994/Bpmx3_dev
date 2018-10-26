package com.hotent.core.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * http操作类
 *
 */
public class HttpUtil {
	
	/**
	 * 发送数据到指定的URL并读取返回结果。
	 * @param url
	 * @param data
	 * @return
	 */
	public static String sendData(String url,String data){
		URL uRL;
		URLConnection conn;
		OutputStreamWriter outputStreamWriter = null;
		BufferedReader bufferedReader = null;
		try {
			uRL = new URL(url);
			conn = uRL.openConnection();
			conn.setDoOutput(true);
			outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
			outputStreamWriter.write(data);
			outputStreamWriter.flush();

			// Get the response
			bufferedReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer response = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line);
			}

			outputStreamWriter.close();
			bufferedReader.close();
			
			return response.toString();
		}
		 catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * 根据url取得数据，支持gzip类网站
	 * @param url
	 * @param charset
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static String getContentByUrl(String url,String charset) throws ParseException, IOException
	{
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);
		
		
		if(StringUtil.isEmpty(charset)){
			String defaultCharset="iso-8859-1";
			Header contentTypeHeader=response.getFirstHeader("Content-Type");
			String contentType=contentTypeHeader.getValue().toLowerCase();
			if(contentType.indexOf("gbk")>-1 || contentType.indexOf("gb2312") >-1 || contentType.indexOf("gb18030")>-1){
				defaultCharset="gb18030";
			}
			else if(contentType.indexOf("utf-8")>-1){
				defaultCharset="utf-8";
			}
			else if(contentType.indexOf("big5")>-1){
				defaultCharset="big5";
			}
			charset=defaultCharset;
		}
		Header contentEncoding=response.getFirstHeader("Content-Encoding");
		StatusLine line=response.getStatusLine();
		if(line.getStatusCode()==200)
		{
			HttpEntity entity = response.getEntity();
			InputStream is;
			if(contentEncoding!=null && contentEncoding.getValue().toLowerCase().equals("gzip"))
			{
				 is=new GZIPInputStream( entity.getContent());
			}
			else
			{
				is=entity.getContent();
			}
			String str=FileUtil.inputStream2String(is, charset);
			is.close();
			return str;
			 
		}
		return "";
	}
	
	
	public static String getContentByUrl(String url) throws ParseException, IOException
	{
		return getContentByUrl(url,"");
	}
	
	/**
	 * 保存远程文件到本地
	 * @param remoteFile 远程文件
	 * @param localFile  本地文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public static void saveRemoteFile(String remoteFile,String localFile) throws ParseException, IOException
	{
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(remoteFile);
		HttpResponse response = httpclient.execute(httpget);
		Header contentEncoding=response.getFirstHeader("Content-Encoding");
		StatusLine line=response.getStatusLine();
		if(line.getStatusCode()==200)
		{
			HttpEntity entity = response.getEntity();
			InputStream is;
			if(contentEncoding!=null && contentEncoding.getValue().toLowerCase().equals("gzip")){
				 is=new GZIPInputStream( entity.getContent());
			}
			else{
				is=entity.getContent();
			}
			FileUtil.createFolder(localFile, true);
			FileOutputStream fs = new FileOutputStream(localFile); 
			
			int bytesum = 0; 
			int byteread = 0; 
			byte[] buffer = new byte[30000]; 
			while ((byteread = is.read(buffer)) != -1){ 
				bytesum += byteread; // 字节数 文件大小 
				fs.write(buffer, 0, byteread); 
			} 
			is.close();
			fs.close();
		}
		
	}
	



}
