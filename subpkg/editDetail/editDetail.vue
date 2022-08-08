<template>
	<view class="root">
		<mover></mover>
		<view class="header">
			<view class="header-left" @click="gotoBack">
				<uni-icons type="back" size="20" @click="gotoBack"></uni-icons>
				<text>取消</text>
			</view>
			<view class="header-right">
			</view>
		</view>
		<view class="box">
			<view v-if="type == 1">
				<view class="add">
					<input class="input" type="text" placeholder="添加你的爱好" v-model="keyword">
					<button class="btn" @click="add">添加</button>
				</view>
				<h2 class="title">你的爱好</h2>
				<view class="result">{{result ? result : "暂无爱好"}}</view>
			</view>
			<view v-else>
				<view class="add">
					<input class="input" type="text" placeholder="添加你的标签" v-model="keyword">
					<button class="btn" @click="add">添加</button>
				</view>
				<h2 class="title">你的标签</h2>
				<view class="result">{{result ? result : "暂无标签"}}</view>
			</view>
		</view>
		<view class="footer">
			<button @click="clear">清除</button>
			<button @click="finish">完成</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				type: 1, //hobby或者tag
				keyword: '',
				result: '',
			};
		},
		onLoad(value){
			if(value.type){
				this.type = value.type
			}
			console.log(this.type);
		},
		methods: {
			add(){
				if(!this.result){
					this.result = this.keyword
				}else{
					this.result += ' ' + this.keyword
				}
				this.keyword = ''
			},
			clear(){
				this.result = ''
			},
			gotoBack(){
				uni.navigateBack()
			},
			finish(){
				this.gotoBack()
			}
		}
	}
</script>

<style lang="scss" scoped>
.root{
	padding: 0 30rpx;
	
	.header {
	
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: space-between;
		background-color: #fff;
	
	
		.header-left {
			display: flex;
			align-items: center;
			height: 100%;
	
			text {
				font-size: 18px;
				font-weight: bold;
				margin-left: 10rpx;
			}
	
		}
	
		.header-right {

		}
	}
	
	button{
		height: 60rpx;
		line-height: 60rpx;
		background: linear-gradient(#ffaa7f, #ff557f);
		color: #fff;
		font-size: 14px;
	}
	
	.box{
		margin-top: 30rpx;
	}
	
	.add{
		display: flex;
		align-items: center;
		height: 100rpx;
		justify-content: space-between;
		
		.input{
			border: 2rpx solid #ddd;
			border-radius: 20rpx;
			height: 80rpx;
			padding: 0 20rpx;
		}
			
	}
	
	
	
	.title{
		margin: 30rpx 0;
		font-size: 18px;
	}
	
	.result{
		border: 8rpx solid #ff557f;
		min-height: 80rpx;
		border-radius: 30rpx;
		line-height: 50rpx;
		padding: 30rpx;
	}
	
	.footer{
		margin-top: 50rpx;
		display: flex;
		justify-content: space-between;
	}
}
</style>
