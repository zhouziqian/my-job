/**
 * 
 */
package com.zhouqi.schedule.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PropertiesUtil {
	private final static Logger logger = Logger.getLogger(PropertiesUtil.class);

	PropertiesUtil() {

	}

	public static Properties getProperties(String path) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			path = StringUtils.substringBeforeLast(path, ".").replace('.', '/') + "." + StringUtils.substringAfterLast(path, ".");
			logger.info(">>>>>>>>>>>> path <<<<<<<<<<<< " + path);
			if (path.startsWith("file:")) {
				Resource resource = new FileSystemResource(StringUtils.substringAfter(path, ":"));
				in = resource.getInputStream();
			} else {
				in = Resources.getAsStream(path);
			}

			if (in != null) {
				prop.load(in);
			}
			else {
				throw new Exception("Resource not exists");
			}
			return prop;
		} catch (Exception e) {
			String msg = e.getMessage();
			logger.error(msg);
			throw new RuntimeException(msg);
		}
	}


	public static String toXML(Properties props) {
		StringBuffer sb = new StringBuffer();
		for (Object key : props.keySet()) {
			Object value = props.get(key);
			sb.append(String.format("<property name=\"%s\" value=\"%s\"/>\n", key, value));
		}
		return sb.toString();
	}

	public static String toXMLProp(Properties props) {
		StringBuffer sb = new StringBuffer();
		for (Object key : props.keySet()) {
			Object value = props.get(key);
			sb.append(String.format("<prop key=\"%s\">%s</prop>\n", key, value));
		}
		return sb.toString();
	}

	public static String toXML(String cfgPath) {
		return toXML(getProperties(cfgPath));
	}

	public static void main(String[] args) {
		String resource = "app/persistence/datasource/dbcp/datasource.properties";
		// System.out.println(getProperties("com/erin/common/text/i18n/MessageBundle.properties_"));
		// System.out.println(getResourceBundlePropertiesOperation(resource).getAsInt("struts.multipart.maxSize"));
		// System.out.println(getPropertiesOperation(resource).getAsInt("struts.multipart.maxSize"));
		// System.out.println(toXML(getProperties(resource)));
		System.out.println(toXMLProp(getProperties(resource)));
	}
}
