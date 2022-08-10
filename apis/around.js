import {
	http
} from '@/common/service/service.js'

let url = "/api/square"
const apiService = {

	//获取
	getRecomment(params) {
		return http.get(`${url}/social/active/getNearActiveList`, params)
	},

	addLike(params) {
		return http.post(`${url}/recomment/likeDiary`, null, params)
	},

	getTotal() {
		return http.get(`${url}/social/getNearTotal`)
	},

	insertReport(params) {
		return http.post(`${url}/report/insertReport`, null, params)
	},




}

export default apiService;
