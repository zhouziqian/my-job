/**
 * 
 */
package com.zhouqi.service.impl;

import com.zhouqi.dao.CfgTaskLogMapper;
import com.zhouqi.entity.CfgTaskLog;
import com.zhouqi.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * <p>Title: TaskLogServiceImpl.java</p>
 * <p>Description:</p>
 * <p>Company:康成投资(中国)有线公司</p>
 *
 * @author zhouqi
 * @date 2018年3月9日 上午11:09:52
 *
 */
@Service
public class TaskLogServiceImpl implements TaskLogService {

	@Autowired
	private CfgTaskLogMapper cfgTaskLogMapper;

	/* (non-Javadoc)
	 * @see com.feiniu.tms.b2b.job.service.TaskLogService#insert(com.feiniu.tms.b2b.core.pojo.CfgTaskLog)
	 */
	@Override
	public void insertSelective(CfgTaskLog cfgTaskLog) {
		cfgTaskLogMapper.insertSelective(cfgTaskLog);
	}
	
	public void updateByPrimaryKeySelective(CfgTaskLog cfgTaskLog){
		cfgTaskLogMapper.updateByPrimaryKeySelective(cfgTaskLog);
	}

}
