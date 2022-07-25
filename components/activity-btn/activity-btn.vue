<template>
	<view class="root">
		<view class="box" v-if="activeBtn.type === 'button'">
			<img :src="button.img" alt="">
			<text>{{button.text}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		name:"activity-btn",
		props: ['activeBtn'],
		data() {
			return {
				button: {
					img: 'static/images/otherIcon/redbag.png',
					text: '惊喜多多'
				},
				bgcColor:{
					light: 'green'
				}
			};
		}
	}
</script>

<style lang="scss" scoped>
	
.root{
	position: fixed;
	right: 20rpx;
	top: 400rpx;
	width: 200rpx;
	height: 200rpx;
	border-radius: 20rpx;
	background-color: rgba(102, 101, 70, 0.1);
	box-shadow: 1px 1px 1px #eee;
}

.box{
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
	
	img{
		height: 80rpx;
		width: 80rpx;		
	}
	
	text{
		margin-top: 20rpx;
		font-size: 20px;
		font-weight: bold;
		color: darkorange;
	}
}
</style>