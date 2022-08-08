import {
	http
} from '@/common/service/service.js'


const apiService = {

	// 发送动态
	postPerson(data, params) {
		console.log(1123);
		return http.post('/social/active/publishActive', data, params)
	},

}

export default apiService;
