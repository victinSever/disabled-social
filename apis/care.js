import {
	http
} from '@/common/service/service.js'


const apiService = {

	//获取
	getCarefor(params) {
		return http.get('/social/getFollowsDiaryList', params)
	},

}

export default apiService;
