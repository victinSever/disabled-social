<script>
	import Vue from 'vue'
	export default {
		onLaunch: function() {
			uni.getSystemInfo({
				success: function(e) {
					Vue.prototype.StatusBar = e.statusBarHeight;
					if (e.platform == 'android') {
						Vue.prototype.CustomBar = e.statusBarHeight + 50;
					} else {
						Vue.prototype.CustomBar = e.statusBarHeight + 45;
					};
				}
			})
			//打开时的动作
			uni.onSocketOpen(() => {
				console.log('WebSocket 已连接')
				this.socketStatus = 'connected'
				uni.showToast({
					title: '实时推送已连接',
					icon: 'none'
				})
			})
			//断开时的动作
			uni.onSocketClose(() => {
				this.socketStatus = 'closed'
				console.log('WebSocket 已断开')
				uni.showToast({
					title: '实时推送已断开连接',
					icon: 'none'
				})
			})
			//报错时的动作
			uni.onSocketError(error => {
				console.error('socket error:', error)
			})
			// 监听服务器推送的消息
			uni.onSocketMessage((message) => {
				console.log(11111111, message)
			
			})
			// 打开信道
			uni.connectSocket({
				url: 'ws://222.177.66.230:8081/websocket/222'
			});
		},
		onShow: function() {},
		onHide: function() {}
		}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import url("@/static/icon/iconfont/iconfont.css");
	@import url("@/static/icon/iconfont2/iconfont.css");
	@import '@/uni_modules/uni-scss/index.scss';
	@import '@/static/customicons.css';
	// 设置整个项目的背景色
</style>
