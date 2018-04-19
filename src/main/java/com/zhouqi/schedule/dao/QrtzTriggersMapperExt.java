/**
 *
 */
package com.zhouqi.schedule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface QrtzTriggersMapperExt{
    List<String> selectByTriggerName(String triggerName);
}
