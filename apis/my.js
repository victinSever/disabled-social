import { http } from '@/common/service/service.js'


const apiService = {
    
    //每日签到
    signIn(params) {
    	return http.put('/user/signIn', null, params)
    },
    
    // 获取个人信息
    getInfo(params) {
    	return http.post('/user/getInfo', null, params)
    },
	
	//修改头像
	changeUserImage(params) {
		return http.post('/user/updateHeadPicPath', null, params)
	},
	
	//修改昵称
	changeNickName(params) {
		return http.post('/user/updateNickName', null, params)
	},
	
	/**************************************
		特权模块
	*************************************/
	
	// 获取套餐信息
	getVipPackageList(params) {
		return http.get('/vipPackage/list',  params)
	},
	
	// 获取权限信息
	getVipPermissionList(params) {
		return http.post('/vipPermission/userId', null,  params)
	},
    
    // 购买VIP
    openVip(params) {
    	return http.post('/user/openVip', null,  params)
    },
    
    // 续费
    renewalVip(params) {
    	return http.post('/user/renewalVip', null,  params)
    },
    
    // 获取主页个人基本信息
    getBaseData(params) {
    	return http.post('/user/getBaseData', null, params)
    },	
    
    /**************************************
    	关注模块
    *************************************/
    
    // 获取关注者列表
    searchFan(params) {
    	return http.post('/fan/searchFan', null, params)
    },
    
    // 取消关注
    cancelAttention(params) {
    	return http.post('/fan/cancelAttention', null, params)
    },
    
    // 关注指定用户
    addAttention(params) {
    	return http.post('/fan/addAttention', null, params)
    },
    
    // 获取粉丝列表
    searchAttention(params) {
    	return http.post('/fan/searchAttention', null, params)
    },
	
	/**************************************
		修改资料
	*************************************/
	
	// 获取一个用户所有信息
	getAllData(params) {
		return http.post('/user/getAllData',null, params)
	},
	
	// 获取基本信息
	getPersonBasicInfo(params) {
		return http.get('/personBasicInfo/personId', params)
	},
	
	// 获取详细信息
	getPersonDetailInfo(params) {
		return http.get('/personDetailInfo/personId', params)
	},
	
	// 获取择偶信息
	getRequirements(params) {
		return http.get('/requirements/personId', params)
	},
	
	// 修改基本信息
	changePersonBasicInfo(params) {
		return http.put('/personBasicInfo/update', params, null, {
			header: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		})
	},
	
	// 修改详细信息
	changePersonDetailInfo(params) {
		return http.put('/personDetailInfo/update', params, null, {
			header: {
				"Content-Type": "application/x-www-form-urlencoded"
			}
		})
	},
	
	// 修改择偶信息
	changeRequirements(params) {
		return http.put('/requirements/update', params, null, {
			header: {
				"Content-Type": "application/x-www-form-urlencoded",
			}
		})
	},
	
	/**************************************
		相册
	*************************************/
	
	// 根据图片id查找图片信息
	searchPictureById(params) {
		return http.post('/pictureAlbum/id', null, params)
	},
	
	// 根据用户id查找相册
	searchAlbumListByUserId(params) {
		return http.post('/pictureAlbum/userId', null, params)
	},
	
	// 新增照片
	addPicture(params) {
		return http.post('/pictureAlbum/insertInfo', params, null, {
			header: {
				"Content-Type": "application/x-www-form-urlencoded",
			}
		})
	},
	
	// 删除照片
	deletePicture(params) {
		return http.delete('/pictureAlbum/delete', null, params)
	},
	
	// 更新图片
	changePicture(params) {
		return http.put('/pictureAlbum/update', params, null, {
			header: {
				"Content-Type": "application/x-www-form-urlencoded",
			}
		})
	},
	
	// 获取我的动态列表
	getMyDiary(params) {
		return http.get('/mine/getMyDiary', params)
	},
	
}

export default apiService;
