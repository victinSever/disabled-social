<template>
	<view class="root">
		<mover></mover>
		<view class="fixed">
			<!-- 头部 -->
			<view class="header">
				<view class="header-left">
					<uni-icons type="back" size="25" @click="gotoBack"></uni-icons>
					<span>编辑资料</span>
				</view>
				<view class="header-right">
					<span class="btnSend" @click="finish">完成</span>
				</view>
			</view>
			
			<!-- 选择编辑或者预览 -->
			<view class="banner">
				<view class="preScan" 
				:style="isPre ? 'border-bottom: 6rpx solid darkorange;; color: darkorange;' : 'border-bottom: 6rpx solid #eee; color: #000;'"
				@click="isPre = true">
					<text>预览</text>
				</view>
				<view class="edit":style="!isPre ? 'border-bottom: 6rpx solid darkorange;; color: darkorange;' : 'border-bottom: 6rpx solid #eee; color: #000;'"
				@click="isPre = false">
					<text>编辑</text>
				</view>
			</view>
		</view>
		
		<scroll-view scroll-y="true" >
			<view class="main">
				<person-template v-if="isPre"></person-template>
				<person-edit :isFinish="isFinish" @getData="getData" v-else></person-edit>
			</view>	
		</scroll-view>	
	</view>
</template>

<script>
	export default {
		name: "checkinformation",
		data() {
			return {
				// 是否为预览状态
				isPre: true,
				isFinish: false,
				personData: {}
			};
		},
		methods:{
			gotoBack(){
				uni.navigateBack()
			},
			finish(){
				let $this = this
				setTimeout(function(){
					this.isFinish = true
					console.log(this.personData);
					uni.$showMsg("修改成功！")
				},1000)							
			},
			getData(data){
				this.personData = data
				console.log(this.personData);
			},
		}
	}
</script>

<style lang="scss" scoped>
	
	.fixed{
		position: sticky;
		top: 70rpx;
		z-index: 5;
	}
	.header {
		
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: space-between;
		background-color: #fff;
		padding: 0 30rpx;
		

		.header-left {
			display: flex;
			align-items: center;
			height: 100%;
			
			span {
				font-size: 22px;
				font-weight: bold;
				margin-left: 10rpx;
			}

		}

		.header-right {
			display: flex;
			align-items: center;
			border-radius: 20rpx;
			padding: 10rpx 20rpx;	
			background-color: darkorange;

			.btnSend {
				display: inline-block;
				text-align: center;
				font-size: 16px;			
				color: #fff;				
			}
		}
	}

	.banner{
		display: flex;
		height: 100rpx;
		box-sizing: border-box;
		font-size: 16px;
		margin-bottom: 40rpx;
		background-color: #fff;
		
		view{
			width: 50%;
			display: flex;
			align-items: center;
			justify-content: center;
			border-bottom: 6rpx solid #eee;
		}
		
		.preScan{
			border-bottom: 6rpx solid darkorange;
			color: darkorange;
		}
		
		.edit{
			
		}
	}

	.main{
		padding: 0 30rpx;
	}
</style>
