<template>
	<view class="media-container">
		<view class="main">
			<message-media :data="item" v-for="(item, i) in acitveData"></message-media>
		</view>
	</view>
</template>

<script>
	export default {
		name: "social-concern",
		data() {
			return {
				concern: [{
					imgpath: "static/images/user.jpg",
				}, {
					imgpath: "static/images/user2.jpg",
				}, {
					imgpath: "static/images/content.jpg",
				}, {
					imgpath: "static/images/content2.jpg",
				}],
				acitveData: [{
					userData: {
						username: "雨末微凉",
						imgpath: "static/images/user.jpg",
					},
					message: "虽然手有点问题，但待人我是专心的但待但待人人我是专心的但待人我是专心的但待但待人但待人人我是专心的",
					activeImages: [{
						imgpath: "static/images/content2.jpg",
					}],
					lastTime: 1,
					lastUnit: '分钟',
					distance: 24
				}, {
					userData: {
						username: "XXXX服务中心",
						imgpath: "static/images/user.jpg",
					},
					message: "国家认证中心",
					activeImages: [{
						imgpath: "static/images/content2.jpg",
					}],

				}, {
					userData: {
						username: "雨末微凉",
						imgpath: "static/images/user.jpg",
					},
					message: "哈哈",
					activeImages: [{
						imgpath: "static/images/content.jpg",
					}],
					lastTime: 5,
					lastUnit: '天',
					distance: 24
				}]
			};
		},
		methods: {
			openPopu() {
				this.$emit('openPopu', true)
			},
		}
	}
</script>

<style lang="scss" scoped>
	.media-container {
		padding: 0 10px;

		.main {
			// background-color: red;
			padding-top: 20px;
			padding-bottom: 10px;
			border-top: 1px solid #EBEBEB;
			width: 100%;
			display: flex;
			flex-wrap: wrap;
			justify-content: space-between;
		}
	}
</style>
