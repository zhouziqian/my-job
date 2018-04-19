/**
 * 
 */
package com.zhouqi.dto;


import com.zhouqi.entity.CfgTask;

/**
 * 
 * <p>
 * Title: CfgTaskDTO.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:康成投资(中国)有线公司
 * </p>
 *
 * @author zhouqi
 * @date 2018年3月12日 上午11:08:37
 *
 */
public class CfgTaskDTO extends CfgTask {
	private String why;
	private String isStart;

	/**
	 * @return the why
	 */
	public String getWhy() {
		return why;
	}

	/**
	 * @param why
	 *            the why to set
	 */
	public void setWhy(String why) {
		this.why = why;
	}

	/**
	 * @return the isStart
	 */
	public String getIsStart() {
		return isStart;
	}

	/**
	 * @param isStart
	 *            the isStart to set
	 */
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

}
