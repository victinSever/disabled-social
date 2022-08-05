
// #ifndef VUE3
import Vue from 'vue'
import App from './App'

// // main.js
// import tabbar from '@/components/tabbar/tabbar.vue'
// Vue.component('tabbar', tabbar) //挂载

//封装弹框的方法
uni.$showMsg = function(title = '功能未完善！', duration = 1500) {
	uni.showToast({
		title, duration, icon: 'none'
	})
}

Vue.config.productionTip = false
Vue.prototype.vuex_uploadAction = "http://222.177.66.230:9898/upload"
App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif