import { http } from '@/common/service/service.js'


const apiService = {
    
    //获取图片秀推荐列表
    getRecomment(params) {
    	return http.get('/recomment/getUser',params)
    },
    
    //获取推荐的视频秀
    getUserVedio(params) {
    	return http.get('/recomment/getUserVedio',params)
    },
    
    
    //喜欢用户
    loveUser(params) {
    	return http.post('/recomment/loveUser',params)
    },
    
    //不喜欢用户
    dislikeUser(params) {
    	return http.post('/recomment/dislikeUser',params)
    },
    
    
    //关注用户
    concernUser(params) {
    	return http.post('/recomment/concernUser',params)
    },
}

export default apiService;
