const state = {
	userInfo: uni.getStorageSync('userInfo') ? uni.getStorageSync('userInfo') : {},
	tokenInfo: uni.getStorageSync('tokenInfo') ? uni.getStorageSync('tokenInfo') : {},
	showLoginTip: false,
	isFirstAdver: true
}

const actions = {

	
}

const mutations = {



}

const getters = {

}

export default {
	state,
	mutations,
	actions,
	getters
}