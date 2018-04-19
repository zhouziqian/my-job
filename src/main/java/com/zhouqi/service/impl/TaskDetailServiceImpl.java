/**
 * 
 */
package com.zhouqi.service.impl;

import java.util.List;

import com.zhouqi.dao.CfgTaskMapperExt;
import com.zhouqi.entity.CfgTaskDetail;
import com.zhouqi.service.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <p>Title: TaskDetailServiceImpl.java</p>
 * <p>Description:</p>
 * <p>Company:康成投资(中国)有线公司</p>
 *
 * @author zhouqi
 * @date 2018年3月9日 上午11:49:53
 *
 */
@Service
public class TaskDetailServiceImpl implements TaskDetailService {

	@Autowired
	private CfgTaskMapperExt cfgTaskMapperExt;

	@Override
	public List<CfgTaskDetail> selectByGroupName(String groupName) {
		return cfgTaskMapperExt.selectByGroupName(groupName);
	}

}
