package com.zhouqi.controller;

import com.zhouqi.dto.CfgTaskDTO;
import com.zhouqi.entity.CfgTask;
import com.zhouqi.enums.SysLogType;
import com.zhouqi.schedule.SchedulerManager;
import com.zhouqi.schedule.service.IQrtzTriggersService;
import com.zhouqi.service.TaskService;
import com.zhouqi.utils.JsonUtil;
import com.zhouqi.enums.Constants.Schedule.TaskState;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author zhouqi
 * @date 2018/4/12 11:32
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    private final static Logger logger = Logger.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private IQrtzTriggersService qrtzTriggersService;

    @RequestMapping(value = "addTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String save(String params) {
        Map<String, Object> result = new HashMap<>();
        String message = null;
        CfgTask cfgTask = JsonUtil.toBean(params, CfgTask.class);
        cfgTask.setTaskState(TaskState.S.name());
        if (cfgTask != null && null != cfgTask.getCfgTaskId()) {
            CfgTask oldTask = taskService.selectByPrimaryKey(cfgTask.getCfgTaskId());
            if (null != oldTask && "N".equals(oldTask.getTaskState())) {
                result.put("msg", "运行状态不能修改,请先暂停");
                result.put("success", false);
                return JsonUtil.toString(result);
            }
            message = "定时任务更新成功";
            taskService.updateByPrimaryKeySelective(cfgTask);
        } else {
            message = "定时任务添加成功";
            taskService.insertSelective(cfgTask);
        }
        result.put("msg", message);
        result.put("success", true);
        return JsonUtil.toString(result);
    }

    @RequestMapping(value = "startOrStopTask", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String startOrStopTask(String params) {
        Map<String, Object> result = new HashMap<>();
        CfgTaskDTO cfgTaskDTO = JsonUtil.toBean(params, CfgTaskDTO.class);
        if (null != cfgTaskDTO.getCfgTaskId()) {
            TaskState taskState = null;
            if (StringUtils.isNotBlank(cfgTaskDTO.getIsStart()) && "N".equals(cfgTaskDTO.getIsStart())) {
                taskState = TaskState.N;
            } else if (StringUtils.isNotBlank(cfgTaskDTO.getIsStart()) && "S".equals(cfgTaskDTO.getIsStart())) {
                taskState = TaskState.S;
                if (StringUtils.isBlank(cfgTaskDTO.getWhy()) || cfgTaskDTO.getWhy().length() < 5) {
                    result.put("success", false);
                    result.put("msg", "请输入停止原因");
                    return JsonUtil.toString(result);
                }
            }
            CfgTask cfgTask = taskService.selectByPrimaryKey(cfgTaskDTO.getCfgTaskId());
            if (cfgTask == null) {
                result.put("success", false);
                result.put("msg", "任务不存在");
                return JsonUtil.toString(result);
            }
            if (cfgTask.getTaskState().equals(taskState.name())) {
                result.put("success", false);
                result.put("msg", "任务当前已是" + (taskState.equals(TaskState.N) ? "运行" : "停止") + "状态,请先刷新");
                return JsonUtil.toString(result);
            }
            boolean isSuccess = false;
            try {
                if (taskState == TaskState.N) {
                    cfgTask.setTaskState("N");
                    // 校验job是否在执行
                    Map<String, Object> checkResult = checkStatus(cfgTask.getCfgTaskId().toString());
                    if ((boolean) checkResult.get("success")) {
                        SchedulerManager.schedulerManager().schedule(cfgTask);
                        logger.info(SysLogType.UPDATE.getType() + "启动定时任务：" + cfgTask.getTaskName());
                    } else {
                        return JsonUtil.toString(checkResult);
                    }
                } else {
                    SchedulerManager.schedulerManager().unschedule(cfgTask);
                    logger.info(SysLogType.UPDATE.getType() + "停止定时任务：" + cfgTask.getTaskName());
                }
                isSuccess = taskService.modifyTaskState(Long.valueOf(cfgTask.getCfgTaskId()), taskState);
            } catch (Exception e) {
                logger.error("定时任务" + cfgTask.getTaskName() + " " + (taskState.equals(TaskState.N) ? "启动" : "停止") + "异常" + e);
            }
            try {
                if (isSuccess && taskState == TaskState.S) {
                    StringBuffer sb = new StringBuffer();
                    String machineInfo = null;
                    try {
                        machineInfo = InetAddress.getLocalHost().getHostName();
                    } catch (UnknownHostException e) {
                    }
                    String jobName = cfgTask.getTaskName();
                    sb.append("停止job:").append(jobName).append("(").append(cfgTask.getCfgTaskId()).append("),");
                    sb.append("类别:");
                    sb.append("无(").append(cfgTask.getCfgTaskTypeCode()).append("),");
                    sb.append("机器名:").append(machineInfo).append(",");
                    sb.append("原因:").append(cfgTaskDTO.getWhy()).append(",");
                    sb.append("时间:").append(new Date());
                    logger.info(sb.toString());
                    result.put("msg", result.get("msg") + machineInfo);
                }
            } catch (Exception w) {
                w.printStackTrace();
            }
            String str = (taskState.equals(TaskState.N) ? "启动" : "停止");
            result.put("msg", isSuccess ? str + "定时任务管理更新成功" : str + "定时任务管理更新失败");
            result.put("success", isSuccess);
        }

        return JsonUtil.toString(result);
    }

    private Map<String, Object> checkStatus(String id) {
        Map<String, Object> reslut = new HashMap<>();
        try {
            List<String> list = qrtzTriggersService.querySchedNameByTriggerName(id);
            if (list != null && list.size() > 1) {
                String m = "";
                int t = 0;
                for (String s : list) {
                    s = s.substring(s.lastIndexOf("-") + 1, s.length());
                    if (t == 0) {
                        m = s;
                    } else {
                        m = m + "、" + s;
                    }
                    t++;
                }
                reslut.put("msg", "当前已在" + m + "中运行，请先停止");
                reslut.put("success", false);
            } else if (list != null && list.size() == 1) {// 只有一个环境时校验是不是本身，是本身就允许操作
                String m = list.get(0).substring(list.get(0).lastIndexOf("-") + 1, list.get(0).length());
                String serverName = null;
                try {
                    serverName = InetAddress.getLocalHost().getHostName();
                    if (serverName.startsWith("stage-tms-job")) {
                        serverName = "PERVIEW";
                    } else if (serverName.contains("beta")) {
                        serverName = "BETA";
                    } else if (serverName.contains("idc")) {
                        serverName = "ONLINE";
                    } else {
                        serverName = "DEV";
                    }
                    if (m.toUpperCase().equals(serverName)) {
                        reslut.put("msg", "当前已在" + m + "中运行");
                        reslut.put("success", false);
                    } else {
                        reslut.put("msg", "当前已在" + m + "中运行，请先停止");
                        reslut.put("success", false);
                    }
                } catch (UnknownHostException e) {
                    logger.error("定时任务获取当前操作环境异常" + id);
                    reslut.put("msg", "定时任务获取当前操作环境异常");
                    reslut.put("success", false);
                }
            } else {
                reslut.put("msg", "当前未运行");
                reslut.put("success", true);
            }
        } catch (Exception e) {
            logger.error("异常" + e);
            reslut.put("msg", "校验运行状态失败");
            reslut.put("success", false);
        }
        return reslut;
    }
}
