/**
 * 
 */
package com.zhouqi.schedule.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IQrtzTriggersService {
	List<String> querySchedNameByTriggerName(String triggerName);
}
