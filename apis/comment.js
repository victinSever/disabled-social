import {
	http
} from '@/common/service/service.js'


const apiService = {

	//获取
	getCommentList(params) {
		return http.get('/social/active/getCommentList', params)
	},
	
	getComments(params){
		return http.get('/social/active/getReplyList',params)
	}

}

export default apiService;