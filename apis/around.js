import {
	http
} from '@/common/service/service.js'


const apiService = {

	//获取
	getRecomment(params) {
		return http.get('/social/active/getNearActiveList', params)
	},

	addLike(params) {
		return http.post('/recomment/likeDiary', null, params)
	},

	getTotal() {
		return http.get('/social/getNearTotal')
	}






}

export default apiService;
