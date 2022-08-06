import my from "@/apis/my"

export default {
    namespaced: true,
    state: {
        userDetail:{},//个人信息
    },
    mutations: {},
    actions: {
        //更新用户信息
        UpdateUserAllData({commit}, data) {
            return new Promise((resolve, reject) => {
                my.getAllData(data).then(response => {
					state.userDetail= response.data;
                 }).catch(error => {
                })
            })
        },
    }
}
