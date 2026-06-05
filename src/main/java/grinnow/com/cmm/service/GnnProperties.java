package grinnow.com.cmm.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import grinnow.com.cmm.helper.ResourceCloseHelper;
import grinnow.com.cmm.util.StringUtil;
import grinnow.com.cmm.util.WebUtil;

public class GnnProperties {

	private static final Logger LOGGER = LoggerFactory.getLogger(GnnProperties.class);

	final static  String FILE_SEPARATOR = System.getProperty("file.separator");

	public static final String RELATIVE_PATH_PREFIX = GnnProperties.class.getResource("") == null ? "" : GnnProperties.class.getResource("").getPath().substring(0, GnnProperties.class.getResource("").getPath().lastIndexOf("com"));

	public static final String GLOBALS_PROPERTIES_FILE = RELATIVE_PATH_PREFIX + "properties" + FILE_SEPARATOR + "globals.properties";

	public static String getPathProperty(String keyName) {
		String value = "";
		
		LOGGER.debug("getPathProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);
		
		FileInputStream fis = null;
		try {
			Properties props = new Properties();
			
			fis = new FileInputStream(WebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
			props.load(new BufferedInputStream(fis));
			
			value = props.getProperty(keyName);
			value = (value == null) ? "" : value.trim();//KISA 보안약점 조치 (2018-10-29, 윤창원)
			value = RELATIVE_PATH_PREFIX + "properties" + System.getProperty("file.separator") + value;
		} catch (FileNotFoundException fne) {
			LOGGER.debug("Property file not found.", fne);
			throw new RuntimeException("Property file not found", fne);
		} catch (IOException ioe) {
			LOGGER.debug("Property file IO exception", ioe);
			throw new RuntimeException("Property file IO exception", ioe);
		} finally {
			ResourceCloseHelper.close(fis);
		}
		
		return value;
	}

	public static String getProperty(String keyName) {
		String value = "";

		LOGGER.debug("===>>> getProperty"+GnnProperties.class.getProtectionDomain().getCodeSource() == null ? "" : StringUtil.isNullToString(GnnProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
		LOGGER.debug("getProperty : {} = {}", GLOBALS_PROPERTIES_FILE, keyName);
		
		FileInputStream fis = null;
		try {
			Properties props = new Properties();
			
			fis = new FileInputStream(WebUtil.filePathBlackList(GLOBALS_PROPERTIES_FILE));
			
			props.load(new BufferedInputStream(fis));
			if (props.getProperty(keyName) == null) {
				return "";
			}
			value = props.getProperty(keyName).trim();
		} catch (FileNotFoundException fne) {
			LOGGER.debug("Property file not found.", fne);
			throw new RuntimeException("Property file not found", fne);
		} catch (IOException ioe) {
			LOGGER.debug("Property file IO exception", ioe);
			throw new RuntimeException("Property file IO exception", ioe);
		} finally {
			ResourceCloseHelper.close(fis);
		}
		return value;
	}

	public static String getPathProperty(String fileName, String key) {
		FileInputStream fis = null;
		try {
			Properties props = new Properties();
			
			fis = new FileInputStream(WebUtil.filePathBlackList(fileName));
			props.load(new BufferedInputStream(fis));
			fis.close();

			String value = props.getProperty(key);
			value = RELATIVE_PATH_PREFIX + "properties" + System.getProperty("file.separator") + value;
			
			return value;
		} catch (FileNotFoundException fne) {
			LOGGER.debug("Property file not found.", fne);
			throw new RuntimeException("Property file not found", fne);
		} catch (IOException ioe) {
			LOGGER.debug("Property file IO exception", ioe);
			throw new RuntimeException("Property file IO exception", ioe);
		} finally {
			ResourceCloseHelper.close(fis);
		}
	}

	public static String getProperty(String fileName, String key) {
		FileInputStream fis = null;
		try {
			Properties props = new Properties();
			
			fis = new FileInputStream(WebUtil.filePathBlackList(fileName));
			props.load(new BufferedInputStream(fis));
			fis.close();

			String value = props.getProperty(key);
			
			return value;
		} catch (FileNotFoundException fne) {
			LOGGER.debug("Property file not found.", fne);
			throw new RuntimeException("Property file not found", fne);
		} catch (IOException ioe) {
			LOGGER.debug("Property file IO exception", ioe);
			throw new RuntimeException("Property file IO exception", ioe);
		} finally {
			ResourceCloseHelper.close(fis);
		}
	}

	public static ArrayList<Map<String, String>> loadPropertyFile(String property) {

		// key - value 형태로 된 배열 결과
		ArrayList<Map<String, String>> keyList = new ArrayList<Map<String, String>>();

		String src = property.replace('\\', File.separatorChar).replace('/', File.separatorChar);
		FileInputStream fis = null;
		try {

			File srcFile = new File(WebUtil.filePathBlackList(src));
			if (srcFile.exists()) {

				Properties props = new Properties();
				fis = new FileInputStream(src);
				props.load(new BufferedInputStream(fis));
				fis.close();

				Enumeration<?> plist = props.propertyNames();
				if (plist != null) {
					while (plist.hasMoreElements()) {
						Map<String, String> map = new HashMap<String, String>();
						String key = (String) plist.nextElement();
						map.put(key, props.getProperty(key));
						keyList.add(map);
					}
				}
			}
		} catch (IOException ex) {
			LOGGER.debug("IO Exception", ex);
			throw new RuntimeException(ex);
		} finally {
			ResourceCloseHelper.close(fis);
		}
		return keyList;
	}
}
