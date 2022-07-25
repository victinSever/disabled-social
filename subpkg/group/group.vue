<template>
	<view class="">
		<view class="anniu">
			<view class="an">
				<image src="@/static/images/bgc/empty.png" mode="widthFix"></image>
				<text>最新</text>
			</view>
			<view class="an">
				<image src="@/static/images/bgc/empty.png" mode="widthFix"></image>
				<text>我的</text>
			</view>
			<view class="an">
				<image src="@/static/images/bgc/empty.png" mode="widthFix"></image>
				<text>筛选</text>
			</view>
		</view>
		<view class="information" >
			<view class="in" v-for="item in 5" :key="item">
				<view class="touxiang">
					<image src="@/static/images/user.jpg" mode="widthFix"></image>
				</view>
				<view class="xiaoxi">
					<text class="mingzi">全国聋人单身群</text>
					<text class="xinxi">专注于...</text>
				</view>
				<button @click="add">+加群</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		methods:{
			add(){
				uni.navigateTo({
					url: '/subpkg/xiangqing/xiangqing'
				})
			}
		}
	}
</script>

<style>

	.anniu{
		display: flex;
		flex-direction: row;
		background-color: aliceblue;
		margin:10rpx 20rpx;
		border-radius: 20rpx;
	}
	.an{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width:120rpx;
		margin:0 55rpx;
	}
	image{
		width:60rpx;
	}
	.information{
		display: flex;
		flex-direction: column;
		width: 95%;
		background-color: aliceblue;
		margin-left: 20rpx;
		border-radius: 20rpx;
	}
	.in{
		display: flex;
		flex-direction: row;
		align-items: center;
		height:120rpx;
		margin-top: 10rpx;
	}
	.touxiang{
		margin-left: 15rpx;
		height: 100rpx;
		color: aqua;
	}
	.touxiang image{
		width:100rpx;
		border-radius: 50%;
	}
	.xiaoxi{
		margin-left: 15rpx;
		display: flex;
		flex-direction: column;
	}
	.xiaoxi .mingzi{
		font-weight: 800;
		font-size: 34rpx;
	}
	.xiaoxi .xinxi{
		color: darkgrey;
	}
	button{
		height:80rpx;
		border-radius: 40rpx;
		text-align: center;
		line-height: 80rpx;
		border: 2rpx solid red;
		margin-left: 170rpx;
	}
</style>