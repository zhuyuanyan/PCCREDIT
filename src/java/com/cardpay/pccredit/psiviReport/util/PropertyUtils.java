package com.cardpay.pccredit.psiviReport.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class PropertyUtils {
	private static InputStream in = null;
	private static Properties props = new Properties();
	
	/**
	 * 构造函数
	 * @throws IOException
	 */
	public PropertyUtils() throws IOException{
		InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream("psvic.properties");
		in = new BufferedInputStream(inputStream);
		props.load(in);
	}
	
	/**
	 * 重新读取
	 * @throws IOException
	 */
	public static void reLoad() throws IOException{
		in = new BufferedInputStream(new FileInputStream("psvic.properties"));
		props.load(in);
	}
	
	/**
	 * 读取key的对应值
	 * @param key
	 * @return
	 */
	public static String getPropertyByKey(String key) {
		try {
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
