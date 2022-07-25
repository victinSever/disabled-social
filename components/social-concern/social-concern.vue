<template>
	<view>
		<view class="concern-header">
			<span v-for="(item, i) in concern" :key="i">
				<img :src="item.imgpath" alt="">
			</span>
		</view>
				
		<view class="main">
			<message-box :data="item" v-for="(item, i) in acitveData" :key="i" @openPopu="openPopu"></message-box>
		</view>
	</view>
</template>

<script>
	export default {
		name:"social-concern",
		data() {
			return {
				concern:[
					{
						imgpath: "static/images/user.jpg",
					},{
						imgpath: "static/images/user2.jpg",
					},{
						imgpath: "static/images/content.jpg",
					},{
						imgpath: "static/images/content2.jpg",
					}
				],
				acitveData: [{
					userData: {
						username: "雨末微凉",
						imgpath: "static/images/user.jpg",
					},					
					message: "虽然手有点问题，但待人我是专心的",
					activeImages: [{
						imgpath: "static/images/content2.jpg",
					}],
					lastTime: 1,
					lastUnit: '分钟',
					distance: 24
				},{
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
			openPopu(){
				this.$emit('openPopu', true)
			},
		}
	}
</script>

<style lang="scss" scoped>
	.concern-header {
		height: 50px;
		padding-left: 10px;
		padding-bottom: 20px;
		background-color: #fff;
		border-bottom: 6rpx solid #eee;
		display: flex;
		align-items: center;
		
		span{
			height: 40px;
			
			img{
				height: 40px;
				width: 40px;
				border-radius: 50%;
				margin-right: 15px;
			}
		}
	}
	
	.main{
		margin-top: 40rpx;
	}
</style>