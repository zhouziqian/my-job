/**
 * 
 */
package com.zhouqi.service;

import com.zhouqi.entity.CfgTaskDetail;

import java.util.List;


/**
 * 
 * <p>Title: TaskDetailService.java</p>
 * <p>Description:</p>
 * <p>Company:康成投资(中国)有线公司</p>
 *
 * @author zhouqi
 * @date 2018年3月9日 上午11:49:39
 *
 */
public interface TaskDetailService {
	List<CfgTaskDetail> selectByGroupName(String groupName);
}
