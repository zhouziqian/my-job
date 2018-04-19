package com.zhouqi.schedule.service.impl;

import com.zhouqi.schedule.dao.QrtzTriggersMapperExt;
import com.zhouqi.schedule.service.IQrtzTriggersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QrtzTriggersServiceImpl implements IQrtzTriggersService {

    @Autowired
    private QrtzTriggersMapperExt qrtzTriggersMapperExt;

    @Override
    public List<String> querySchedNameByTriggerName(String triggerName) {
        return qrtzTriggersMapperExt.selectByTriggerName(triggerName);
    }
}
