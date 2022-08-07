
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import store from './store'
import tip from './common/util/tip.js'
//封装弹框的方法
uni.$showMsg = function(title = '功能未完善！', duration = 1500) {
	uni.showToast({
		title, duration, icon: 'none'
	})
}
// tip
Vue.prototype.$tip = tip;


Vue.config.productionTip = false
Vue.prototype.vuex_uploadAction = "http://222.177.66.230:9898/upload";
Vue.prototype.webSocketApi = "ws://222.177.66.230:8081/websocket/";
App.mpType = 'app'

const app = new Vue({
	store,
    ...App,
	beforeCreate() {
		Vue.prototype.$bus = this
	}
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
