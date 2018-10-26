package com.hotent.core.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;

/**
 * 功能： 产生验证码。<br>
 * 从session中获取验证码。<br>
 * 获取验证码方法如下：<br>
 * String code=session.getAttribute(ValidCode.SessionName_Randcode);<br>
 * 需要在web.xml中进行配置。<br>
 * 配置方法：<br>
 * <pre>
 *&lt;servlet>
 *		&lt;servlet-name>ValidCode&lt;/servlet-name>
 *		&lt;servlet-class>com.hotent.core.web.ValidCode&lt;/servlet-class>
 *&lt;/servlet>
 *&lt;servlet-mapping>
 *		&lt;servlet-name>ValidCode&lt;/servlet-name>
 *		&lt;url-pattern>/servlet/ValidCode&lt;/url-pattern>
 *&lt;/servlet-mapping>
 * </pre>
 */
public class ValidCode extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String SessionName_Randcode = "randcode";

	
	public ValidCode()
	{
		super();
	}

	public void destroy()
	{
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(155, 254));
		// g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 220));
		for (int i = 0; i < 155; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++)
		{
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION
		request.getSession().setAttribute(SessionName_Randcode, sRand);
		// 图象生效
		g.dispose();
		// 输出图象到页面
		
		

		ServletOutputStream os = response.getOutputStream();
		//ImageIO.write(image, "JPEG", os);
		try {
			Jimi.putImage("image/jpeg", image, os);
		} catch (JimiException e) {
			e.printStackTrace();
		} 
		os.flush();
        os.close();
        os = null;
        response.flushBuffer();
	}

	private Color getRandColor(int fc, int bc)
	{
		// 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}


	public void init() throws ServletException
	{
		// Put your code here
	}
}
