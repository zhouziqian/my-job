package com.zhouqi.dao;


import com.zhouqi.entity.CfgTask;

public interface CfgTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long cfgTaskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    int insert(CfgTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    int insertSelective(CfgTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    CfgTask selectByPrimaryKey(Long cfgTaskId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CfgTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cfg_task
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CfgTask record);
}