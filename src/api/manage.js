import fetch from './fetch'

/***********************************************************************************
 * 登录注册
 */

 export const login = data => fetch('/system/login', data, 'post')


 export const register = data => fetch('/system/register', data, 'post')


/*******************************************************************************
 *                                  业务API
 * @param {*} data 
 * @returns 
 */

/**
 * 广告模块
 * @param {*} data 
 * @returns 
 */
export const getAdvertisingList = data => fetch('/business/getAdvertisingList', data, 'get') //获取广告列表

export const addAdvertising = data => fetch('/business/addAdvertising', data, 'post') //添加单个广告

export const deleteAdvertsing = data => fetch('/business/deleteAdvertsing', data, 'delete') //删除单个广告

/**
 * 广告位模块
 * @param {*} data 
 * @returns 
 */
export const getAdvertiseplaceList = data => fetch('/business/getAdvertiseplaceList', data, 'get') //获取广告位列表

/**
 * 活动模块
 * @param {*} data 
 * @returns 
 */
export const getActivityList = data => fetch('/business/getActivityList', data, 'get') //获取活动列表

export const deleteActivity = data => fetch('/business/deleteActivity', data, 'delete') //删除单个活动

/**
 * 用户模块
 * @param {*} data 
 * @returns 
 */
export const getUserList = data => fetch('/user/getUserList', data, 'get') //获取用户列表

export const deleteUser = data => fetch('/user/deleteUser', data, 'delete') //删除用户列表

/**
 * 日志模块
 * @param {*} data 
 * @returns 
 */
 export const getLoginLogs = data => fetch('/logs/getLoginLogs', data, 'get') //获取登录日志列表




 /***********************************************************************************
 * 系统
 */
  export const changeInfo = data => fetch('/system/changeInfo', data, 'put') //修改管理员信息

  export const getAdministrator = data => fetch('/system/getAdministrator', data, 'get') //获取管理员信息


/***********************************************************************************
 * 审核模块
 */
export const getAuditLogs = data => fetch('/audit/getAuditLogs', data, 'get') //获取审核日志

export const getActiveList = data => fetch('/audit/getNotVideoDiaryList', data, 'get') //获取动态列表

export const deleteActive = data => fetch('/audit/getAuditDiaryResult', data, 'put') //删除动态

export const getVedioList = data => fetch('/audit/getVideoDiaryList', data, 'get') //获取动态列表


/***********************************************************************************
 * 工作台
 */

 export const getVersions = data => fetch('/panel/getVersions', data, 'get') //获取动态列表

 export const getAllAdministrator = data => fetch('/logs/getAllAdministrator', data, 'get') //获取所有的管理员的信息
