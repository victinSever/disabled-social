import {
	http
} from '@/common/service/service.js'


const apiService = {

	//获取
	postPerson(params) {
		console.log(1123);
		return http.post('/social/active/publishActive', null, params)
	},

}

export default apiService;
