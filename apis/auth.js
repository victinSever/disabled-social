import { http } from '@/common/service/service.js'
import configService from '@/common/service/config.service.js';
const apiService = {
	
    login(params) {
    	return http.post('user/join',params,null,{
			header:{
				 'Content-Type': 'application/x-www-form-urlencoded'
			}
		})
    },
}

export default apiService;
