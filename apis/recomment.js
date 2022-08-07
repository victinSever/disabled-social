import { http } from '@/common/service/service.js'


const apiService = {
    
    //获取图片秀推荐列表
    getRecomment(params) {
    	return http.get('/recomment/SearchPictureShowByAdvise',params)
    },
    
    //获取推荐的视频秀
    getUserVedio(params) {
    	return http.get('/recomment/SearchVideoShowByAdvise',params)
    },
	
	
	//收藏图片以及视频
	collect(params) {
		return http.post('/recomment/collect',null,params)
	},
   
    
    //喜欢用户
    loveUser(params) {
    	return http.post('/recomment/loveUser',params)
    },
    
    //不喜欢用户
    dislikeUser(params) {
    	return http.post('/recomment/dislikeUser',params)
    },
       
	//取消不喜欢用户
	cancelDislikeUser(params) {
		return http.delete('/recomment/cancel-dislikeUser',params)
	},
	   
    //关注用户
    concernUser(params) {
    	return http.post('/mine/concernUser',null,params)
    },
	
	//取消关注
	cancelConcernUser(params) {
		return http.delete('/mine/cancelConcernUser',params)
	},
}

export default apiService;
