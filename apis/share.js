import {
	http
} from '@/common/service/service.js'

let url="/api/square"

const apiService = {

	// 发送动态
	postPerson(data, params) {
		return http.post(`${url}/social/active/publishActive`, data, params)
	},

}

export default apiService;
