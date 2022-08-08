import {
	http
} from '@/common/service/service.js'


const apiService = {

	//获取
	getCarefor(params) {
		return http.get('/social/getFollowsDiaryList', params)
	},
	
	getTotal(){
		return http.get('/social/getFollowPages')
	}

}

export default apiService;
