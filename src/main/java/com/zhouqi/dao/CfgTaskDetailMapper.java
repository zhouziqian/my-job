package com.zhouqi.dao;


import com.zhouqi.entity.CfgTaskDetail;

public interface CfgTaskDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    int insert(CfgTaskDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    int insertSelective(CfgTaskDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    CfgTaskDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CfgTaskDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CfgTaskDetail record);
}