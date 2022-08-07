import { http } from '@/common/service/service.js'


const apiService = {
    //推荐用户
    recommendList(params) {
    	return http.post('/chat/recommendList',params)
    },
    
    //获取用户消息列表
    messageList(id) {
    	return http.post(`/chat/lkfriends/${id}`)
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
	
	//消息未读
	alreadyRead(params) {
		return http.post('/chat/alreadyRead',params)
	},
	
	
	//删除聊天人
	deleteuser(fuserid) {
		return http.delete(`/chat/deleteuser/${fuserid}`)
	},
	
	
	//获取聊天列表
	lkuschatmsg(params) {
		return http.post(`/chat/lkuschatmsg/${params.reviceuserid}/${params.userid}`)
	},
	
    
}

export default apiService;