import Vue from 'vue'

const webSocket = {

	open(userId) {
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
			uni.$emit("websocketData",message)
		})
		// 打开信道
		uni.connectSocket({
			url: Vue.prototype.webSocketApi + userId
		});
	},

	sendData(data) {
		uni.sendSocketMessage({
			data,
			success(res) {
				console.log('发送成功', res)
			},
			fail: e => {
				console.log('发送失败', e)
			}
		})
	},

	close() {

	},



}

export default webSocket;
