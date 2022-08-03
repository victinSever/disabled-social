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
