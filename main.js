
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import store from './store'

//封装弹框的方法
uni.$showMsg = function(title = '功能未完善！', duration = 1500) {
	uni.showToast({
		title, duration, icon: 'none'
	})
}

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
	store,
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