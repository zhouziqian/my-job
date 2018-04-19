/**
 * 
 */
package com.zhouqi.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private AppplicationContextUtil() {
	}

	public static ApplicationContext getContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}
}
