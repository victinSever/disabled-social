<template>
	<view class="root">
		<mover></mover>
		<!-- 头部 -->
		<view class="header">
			<view class="left">
				<image src="@/static/images/home/shang.png" @click="gotoBack"></image>			
			</view>
			<view class="center">
				<text :class="isConcern == 1 ? 'active' : ''" @click="isConcern = 1">关注</text>
				<text :class="isConcern == 2 ? 'active' : ''" @click="isConcern = 2">粉丝</text>
			</view>
			<view class="right">

			</view>
		</view>
		
		<view class="box">
			<uni-transition mode-class="slide-left" :show="isConcern === 1">
				<concern></concern>
			</uni-transition>
			<uni-transition mode-class="slide-right" :show="isConcern === 2">
				<fan></fan>
			</uni-transition>			
		</view>
	</view>
</template>

<script>
	import concern from "@/components/person-billboard/concern.vue"
	import fan from "@/components/person-billboard/fan.vue"
	export default {
		data() {
			return {
				isConcern: 1,
				searchValue: '',
			};
		},
		components: {
			concern,fan
		},
		onLoad(options){		
			if(options.type)
				this.isConcern = options.type
		},
		methods: {
			gotoBack() {
				uni.navigateBack()
			},
			input(res){
				uni.showToast({
					title: '搜索：' + res.value,
					icon: 'contact'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.root {
		padding: 0 30rpx;
	}

	.header {
		height: 80rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		border-bottom: 2rpx solid #eee;
		box-sizing: border-box;
		
		image {
			height: 50rpx;
			width: 50rpx;
			transform: rotate(-90deg);
		}
		
		.center{
			width: 300rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			height: 100%;
			
			text {
				font-size: 16px;
				display: inline-block;
				line-height: 80rpx;
				color: #ddd;
			}
			
			.active{
				border-bottom: 4rpx solid #000;
				color: #000;
			}
		}

	}
</style>
