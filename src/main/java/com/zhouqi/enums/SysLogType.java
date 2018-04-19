package com.zhouqi.enums;

import org.apache.commons.lang3.StringUtils;

public enum SysLogType {
    INSERT("INSERT", Constants.OperationType.INSERT), UPDATE("UPDATE", Constants.OperationType.UPDATE), DELETE("DELETE",
            Constants.OperationType.DELETE), NONE("NONE", 0);

    SysLogType(String type, Integer typeVal) {
        this.type = type;
        this.typeVal = typeVal;
    }

    private String type;
    private Integer typeVal;

    public static SysLogType getType(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            for (SysLogType t : SysLogType.values()) {
                if (msg.startsWith(t.getType()))
                    return t;
            }
        }
        return NONE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeVal() {
        return typeVal;
    }

    public void setTypeVal(Integer typeVal) {
        this.typeVal = typeVal;
    }
}
