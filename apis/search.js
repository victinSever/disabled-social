import {
	http
} from '@/common/service/service.js'


const apiService = {

	// 发送动态
	getList(params) {
		return http.get('/recomment/searchPictureShowByKeywords', params)
	},

}

export default apiService;
