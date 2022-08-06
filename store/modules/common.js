
export default {
    namespaced: true,
    state: {
		// baseInfo: JSON.parse(localStorage.getItem('baseInfo')) || {},
		// moreInfo: JSON.parse(localStorage.getItem('moreInfo')) || {}
		baseInfo: {},
		moreInfo: {}
	},
    mutations: {
		// 存储用户基础信息
		setBaseInfo(state, data) {
			JSON.stringify(localStorage.setItem('baseInfo', data))
			state.userInfo = data
		},
		// 存储用户具体信息
		setMoreInfo(state, data) {
			JSON.stringify(localStorage.setItem('moreInfo', data))
			state.moreInfo = data
		}
	},
    actions: {
		
    }
}
