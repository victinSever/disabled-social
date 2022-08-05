import { http } from '@/common/service/service.js'


const apiService = {
    
    //每日签到
    signIn(params) {
    	return http.put('/user/signIn', params)
    },
    
    // 获取个人信息
    getInfo(params) {
    	return http.post('/user/getInfo', null, params)
    },
    
    // 购买VIP
    openVip(params) {
    	return http.post('/user/openVip', null,  params)
    },
    
    // 续费
    renewalVip(params) {
    	return http.post('/user/renewalVip', null,  params)
    },
    
    // 获取主页个人基本信息
    getBaseData(params) {
    	return http.post('/user/getBaseData', null, params)
    },
    
    /**************************************
    	关注模块
    *************************************/
    
    // 获取关注者列表
    searchFan(params) {
    	return http.post('/fan/searchFan', null, params)
    },
    
    // 取消关注
    cancelAttention(params) {
    	return http.post('/fan/cancelAttention', null, params)
    },
    
    // 关注指定用户
    concernUser(params) {
    	return http.post('/recomment/concernUser', null, params)
    },
    
    // 获取粉丝列表
    searchAttention(params) {
    	return http.post('/fan/searchAttention', null, params)
    },
}

export default apiService;
