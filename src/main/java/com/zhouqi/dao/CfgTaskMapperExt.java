/**
 * 
 */
package com.zhouqi.dao;

import java.util.List;

import com.zhouqi.entity.CfgTaskDetail;
import org.springframework.stereotype.Repository;


/**
 * 
 * <p>Title: CfgTaskMapperExt.java</p>
 * <p>Description:</p>
 * <p>Company:康成投资(中国)有线公司</p>
 *
 * @author zhouqi
 * @date 2018年3月9日 下午1:49:01
 *
 */
@Repository
public interface CfgTaskMapperExt extends CfgTaskMapper{
	List<CfgTaskDetail> selectByGroupName(String groupName);
}
