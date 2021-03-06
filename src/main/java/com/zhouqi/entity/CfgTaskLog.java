package com.zhouqi.entity;

import java.io.Serializable;
import java.util.Date;

public class CfgTaskLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.TASK_ID
     *
     * @mbggenerated
     */
    private Long taskId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.CFG_TASK_ID
     *
     * @mbggenerated
     */
    private Long cfgTaskId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.START_DATE
     *
     * @mbggenerated
     */
    private Date startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.FINISH_DATE
     *
     * @mbggenerated
     */
    private Date finishDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.STATE
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.CONTENT
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cfg_task_log.CFG_TASK_DETAIL_ID
     *
     * @mbggenerated
     */
    private Long cfgTaskDetailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cfg_task_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.TASK_ID
     *
     * @return the value of cfg_task_log.TASK_ID
     *
     * @mbggenerated
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.TASK_ID
     *
     * @param taskId the value for cfg_task_log.TASK_ID
     *
     * @mbggenerated
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.CFG_TASK_ID
     *
     * @return the value of cfg_task_log.CFG_TASK_ID
     *
     * @mbggenerated
     */
    public Long getCfgTaskId() {
        return cfgTaskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.CFG_TASK_ID
     *
     * @param cfgTaskId the value for cfg_task_log.CFG_TASK_ID
     *
     * @mbggenerated
     */
    public void setCfgTaskId(Long cfgTaskId) {
        this.cfgTaskId = cfgTaskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.START_DATE
     *
     * @return the value of cfg_task_log.START_DATE
     *
     * @mbggenerated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.START_DATE
     *
     * @param startDate the value for cfg_task_log.START_DATE
     *
     * @mbggenerated
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.FINISH_DATE
     *
     * @return the value of cfg_task_log.FINISH_DATE
     *
     * @mbggenerated
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.FINISH_DATE
     *
     * @param finishDate the value for cfg_task_log.FINISH_DATE
     *
     * @mbggenerated
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.STATE
     *
     * @return the value of cfg_task_log.STATE
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.STATE
     *
     * @param state the value for cfg_task_log.STATE
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.CONTENT
     *
     * @return the value of cfg_task_log.CONTENT
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.CONTENT
     *
     * @param content the value for cfg_task_log.CONTENT
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cfg_task_log.CFG_TASK_DETAIL_ID
     *
     * @return the value of cfg_task_log.CFG_TASK_DETAIL_ID
     *
     * @mbggenerated
     */
    public Long getCfgTaskDetailId() {
        return cfgTaskDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cfg_task_log.CFG_TASK_DETAIL_ID
     *
     * @param cfgTaskDetailId the value for cfg_task_log.CFG_TASK_DETAIL_ID
     *
     * @mbggenerated
     */
    public void setCfgTaskDetailId(Long cfgTaskDetailId) {
        this.cfgTaskDetailId = cfgTaskDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_log
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CfgTaskLog other = (CfgTaskLog) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getCfgTaskId() == null ? other.getCfgTaskId() == null : this.getCfgTaskId().equals(other.getCfgTaskId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getFinishDate() == null ? other.getFinishDate() == null : this.getFinishDate().equals(other.getFinishDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCfgTaskDetailId() == null ? other.getCfgTaskDetailId() == null : this.getCfgTaskDetailId().equals(other.getCfgTaskDetailId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_log
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getCfgTaskId() == null) ? 0 : getCfgTaskId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getFinishDate() == null) ? 0 : getFinishDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCfgTaskDetailId() == null) ? 0 : getCfgTaskDetailId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_log
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", cfgTaskId=").append(cfgTaskId);
        sb.append(", startDate=").append(startDate);
        sb.append(", finishDate=").append(finishDate);
        sb.append(", state=").append(state);
        sb.append(", content=").append(content);
        sb.append(", cfgTaskDetailId=").append(cfgTaskDetailId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}