/**
 * 
 */
package com.zhouqi.enums;

/**
 * 
 * <p>
 * Title: Schedule.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:康成投资(中国)有线公司
 * </p>
 *
 * @author zhouqi
 * @date 2018年3月9日 上午9:32:48
 *
 */
public interface Constants {
	enum STATE {
		/** normal */
		N, /** abort */
		A, /** error */
		E, /** running */
		R
	}

	public static interface Schedule {
		/** task log key */
		String KEY_TASK_LOG = "key.task.log";
		/** task cfg key */
		String KEY_TASK = "key.task";

		String KEY_TASK_INSTANCE_ID = "key.task.instance.id";

		String KEY_TASK_TYPE_CLASSNAME = "className";

		String KEY_TASK_TYPE_METHOD = "methodName";

		String KEY_TASK_TYPE_GROUP = "groupName";
		/**
		 * In seconds
		 */
		String PROP_KEY_TASK_SCAN_INTERVAL = "task.scan.interval.second";
		String PROP_KEY_TASK_SCAN_GROUP = "task.group.name";

		public static enum JobType {
			M, T, C, G
		}

		public static enum TriggerType {
			S, C
		}

		public static enum TaskState {
			/** suspend */
			S, /** normal */
			N
		}
	}

	/* 日志操作类型静态数据 */
	public static interface OperationType {

		String BIZ_OPERATION_TYPE_STR = "BIZ_OPERATION_TYPE";
		public static int INSERT = 1;
		public static int UPDATE = 2;
		public static int DELETE = 3;
		// 刷新缓存
		public static int CACHE_REFRESH = 11;
		// 登出
		public static int LOGIN_OUT = 12;
		// 登录
		public static int LOGIN_IN = 13;

		// 删除用户
		public static int USER_DLLETE_ = 14;
		// 新增用户
		public static int USER_INSERT = 15;
		// 修改用户
		public static int USER_UPDATE = 16;

		// 新增菜单
		public static int ORG_INSERT = 17;
		// 删除菜单
		public static int ORG_DLLETE = 18;
		// 修改菜单
		public static int ORG_UPDATE = 19;

		// 新增角色
		public static int ROLE_INSERT = 20;
		// 删除角色
		public static int ROLE_DLLETE = 21;
		// 修改角色
		public static int ROLE_UPDATE = 22;

		// 新增功能集
		public static int FUNCTIONSET_INSERT = 23;
		// 删除功能集
		public static int FUNCTIONSET_DLLETE = 24;
		// 修改功能集
		public static int FUNCTIONSET_UPDATE = 25;

		// 新增菜单
		public static int FUNCTION_INSERT = 26;
		// 删除菜单
		public static int FUNCTION_DLLETE = 27;
		// 修改菜单
		public static int FUNCTION_UPDATE = 28;

		// 新增菜单按钮
		public static int OPERATION_INSERT = 29;
		// 删除菜单
		public static int OPERATION_DLLETE = 30;
		// 修改菜单
		public static int OPERATION_UPDATE = 31;

	}

	public static interface Cache {
		// 同步方法
		public static final String KEY_TMS_SYNC_JOB = "tms_sync_job";
		public static final String KEY_TMS_SYNC_LOCAL = "tms_sync_local";
	}
}
