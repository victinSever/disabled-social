import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userInfo: JSON.parse(localStorage.getItem('userInfo')) || {},
        menuList: JSON.parse(localStorage.getItem('menuList')) || [],
        breadcrumb: JSON.parse(localStorage.getItem('breadcrumb')) || [{
            path: '',
            label: '控制面板'
          },{
            path: '/dashboard/workplace',
            label: '工作台'
          }],
        logs: []
    },
    mutations: {
        // 存储管理员信息
        setUserInfo(state, userInfo) {
            localStorage.setItem('userInfo', JSON.stringify(userInfo)); //将传递的数据先保存到localStorage中
            state.userInfo = userInfo; // 之后才是修改state中的状态
        },
        // 存储菜单栏信息
        setMenuList(state, menuList) {
            localStorage.setItem('menuList', JSON.stringify(menuList)); 
            state.menuList = menuList;
        },
        // 存储面包屑信息数组
        setBreadcrumb(state, breadcrumb) {
            localStorage.setItem('breadcrumb', JSON.stringify(breadcrumb)); 
            state.breadcrumb = breadcrumb;
        },
        addLogs(state, data) {
            state.logs.push(data);
        },

    },
    actions: {},
    modules: {}
})