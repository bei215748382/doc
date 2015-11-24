package com.mlnx.doc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取服务器配置，单例模式设计
 * 
 * @author bwh
 * 
 */
public class PropertiyUtil {

	private static final Logger log = LoggerFactory
			.getLogger(PropertiyUtil.class);

	private static PropertiyUtil instance = null;

	private static final String serviceConfig = "property/config.properties";

	private static final String CONFIG_DEBUG_ENABLE = "config.debug.enable";

	private static final boolean DEFAULT_CONFIG_DEBUG_ENABLE = false;

	private boolean debug;

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new PropertiyUtil();
		}
	}

	public static PropertiyUtil getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public PropertiyUtil() {
		loadProperty(serviceConfig);
	}

	public Properties loadProperty(String fileName) {
		Properties p = new Properties();
		try (InputStream configIn = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(fileName)) {
			p.load(configIn);
		} catch (IOException e) {
			log.info(String.format("load properties %s error:%s", fileName,
					e.getMessage()));
		}
		debug = getBooleanProperty(p, CONFIG_DEBUG_ENABLE,
				DEFAULT_CONFIG_DEBUG_ENABLE);
		return p;
	}

	private static String getStringProperty(Properties properties, String key,
			String defaultValue) {
		String stringValue = properties.getProperty(key);
		return stringValue == null ? defaultValue : stringValue;

	}

	private static long getLongProperty(Properties properties, String key,
			long defaultValue) {

		String stringValue = properties.getProperty(key);
		long result;
		try {
			result = Long.parseLong(stringValue);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}

	private static boolean getBooleanProperty(Properties properties,
			String key, boolean defaultValue) {

		String stringValue = properties.getProperty(key);
		return stringValue == null ? defaultValue : Boolean
				.parseBoolean(stringValue);
	}

	private static int getIntProperty(Properties properties, String key,
			int defaultValue) {

		String stringValue = properties.getProperty(key);
		int result;
		try {
			result = Integer.parseInt(stringValue);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}

	private static int[] getIntArrayProperty(Properties properties, String key,
			int[] defaultValue) {

		String stringValue = properties.getProperty(key);
		if (stringValue == null) {
			return defaultValue;
		}

		String[] components = stringValue.split(",");
		int[] result = new int[components.length];
		try {
			for (int i = 0; i < result.length; i++) {
				result[i] = Integer.parseInt(components[i].trim());
			}
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}

	private static String toString(int[] intArray) {

		if (intArray.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(intArray[0]);
		for (int i : intArray) {
			sb.append(',').append(i);
		}
		return sb.toString();
	}

	public Properties toProperties() {

		Properties config = new Properties();
		return config;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	//测试
	public static void main(String[] args) {
		PropertiyUtil pu = PropertiyUtil.getInstance();
		System.out.println(pu.isDebug());
	}
}
