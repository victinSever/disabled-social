import {
	http
} from '@/common/service/service.js'

let url="/api/square"
const apiService = {

	// 发送动态
	getList(params) {
		return http.get(`${url}/recomment/searchPictureShowByKeywords`, params)
	},

}

export default apiService;
