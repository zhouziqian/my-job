package com.zhouqi.schedule.job.impl.method;

import com.zhouqi.schedule.job.impl.AbstractJob;
import org.apache.commons.lang3.ClassUtils;
import org.quartz.JobExecutionContext;

import java.lang.reflect.Method;
import java.util.Map;

public class MethodInvokeJobImpl extends AbstractJob {
    private String className;
    private String methodName;

    @Override
    public String executeInternal(JobExecutionContext context) throws Exception {
        Class<?> clz = ClassUtils.getClass(getClassName());
        Object instance = clz.newInstance();
        Method m = null;
        try {
            m = clz.getMethod(getMethodName(), Map.class);
            if (!m.isAccessible())
                m.setAccessible(true);
            return m.invoke(instance, context.getMergedJobDataMap()) + "";
        } catch (Exception e) {
        }

        if (m == null)
            try {
                m = clz.getMethod(getMethodName());
                if (!m.isAccessible())
                    m.setAccessible(true);
                return m.invoke(instance) + "";
            } catch (Exception e) {
            }
        System.out.println("scheduler.job.type.method.not.support" + getMethodName() + getClassName());
        throw new Exception();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
