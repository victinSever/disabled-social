import {
	http
} from '@/common/service/service.js'

let url="/api/square"
const apiService = {

	//获取附近列表
	getCommentList(params) {
		return http.get(`${url}/social/active/getCommentList`, params)
	},

	// 获取评论
	getComments(params) {
		return http.get(`${url}/social/active/getReplyList`, params)
	},

	// 点赞
	addLike(params) {
		return http.post('/recomment/likeComment', null, params)
	},

	// 回复点赞
	addLikeComment(params) {
		return http.post('/recomment/likeReply', null, params)
	},
	
	// 获取一条动态信息
	getOneInfo(params){
		return http.get(`${url}/social/active/getOneDiary`,params)
	}

}

export default apiService;
