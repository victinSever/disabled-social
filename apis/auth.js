import { http } from '@/common/service/service.js'
import configService from '@/common/service/config.service.js';
const apiService = {
	
    login(params) {
    	return http.post('/user/login',null,params)
    },
}

export default apiService;
