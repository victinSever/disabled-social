import { http } from '@/common/service/service.js'


const apiService = {
    //推荐用户
    recommendList(params) {
    	return http.post('/chat/recommendList',params)
    },
    
    //获取用户消息列表
    messageList(params) {
    	return http.get('/message/messageList',params)
    },
    
    //获取单个用户的消息
    lookPersonMessage(params) {
    	return http.post('/chat/lookPersonMessage',params)
    },
    
    //发送消息
    sendMessage(params) {
    	return http.post('/messaeg/sendMessage',params)
    },
    
    //查询群聊List
    getGroup(params) {
    	return http.get('/message/getGroup',params)
    },
    
}

export default apiService;