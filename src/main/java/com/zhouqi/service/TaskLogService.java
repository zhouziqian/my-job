/**
 * 
 */
package com.zhouqi.service;


import com.zhouqi.entity.CfgTaskLog;

/**
 * 
 * <p>Title: TaskLogService.java</p>
 * <p>Description:</p>
 * <p>Company:康成投资(中国)有线公司</p>
 *
 * @author zhouqi
 * @date 2018年3月9日 上午11:08:57
 *
 */
public interface TaskLogService {
	void insertSelective(CfgTaskLog cfgTaskLog);
	
	void updateByPrimaryKeySelective(CfgTaskLog cfgTaskLog);
}
