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
				<text :class="isConcern == 2 ? 'active' : ''" @click="gotoFan">粉丝</text>
			</view>
			<view class="right">

			</view>
		</view>
		
		<view class="box">
			<uni-transition mode-class="slide-left" :show="isConcern === 1">
				<concern :list="attentionList"></concern>
			</uni-transition>
			<uni-transition mode-class="slide-right" :show="isConcern === 2">
				<fan :list="fanList"></fan>
			</uni-transition>			
		</view>
	</view>
</template>

<script>
	import concern from "@/components/person-billboard/concern.vue"
	import fan from "@/components/person-billboard/fan.vue"
	import { mapState } from 'vuex'
	import my from '@/apis/my.js'
	export default {
		data() {
			return {
				isConcern: 1,
				searchValue: '',
				attentionList: [],
				fanList: [],
				attentionPage: {
					follower: 5,
					start: 0,
					limit: 10,
				},
				fanPage: {
					userId: 5,
					start: 0,
					limit: 10,
				},
				isLoading: false
			};
		},
		computed: {
			...mapState('user', ['loginUser']),
			...mapState('common', ['baseInfo']),
		},
		components: {
			concern,fan
		},
		mounted(){
			// this.attentionPage.follower = this.loginUser.userId
			this.fanPage.userId = this.loginUser.userId
			this.getAttentionList()
			this.getFanList()
		},
		onLoad(options){	
			if(options.type)
				this.isConcern = parseInt(options.type)
		},
		// 下拉触底
		onReachBottom(){
			// 节流器
			if(!this.isLoading){
				if(this.isConcern == 1){
					this.attentionPage.start++
					this.getAttentionList()
				}else{
					this.fanPage.start++
					this.getFanList()
				}				
			}					
		},
		methods: {
			// 查询关注列表
			async getAttentionList(){
				this.isLoading = true
				const { data: res} = await my.searchFan(this.attentionPage)
				if(res.resultCode === 200){
					this.attentionList = [
						...this.attentionList,
						...res.data
					]
				}
				this.isLoading = false
				console.log(this.attentionList);
			},
			async getFanList(){
				this.isLoading = true
				const { data: res} = await my.searchAttention(this.fanPage)
				if(res.resultCode === 200){
					this.fanList = [
						...this.fanList,
						...res.data
					]
				}
				this.isLoading = false
			},
			
			gotoFan(){
				console.log(this.baseInfo);
				if(this.baseInfo.isVip != 1){
					uni.$showMsg('查看谁喜欢你需要VIP权益哦！')					
				}
				else
					this.isConcern = 2
			},
			gotoBack() {
				uni.switchTab({
					url: '/pages/person/person'
				})
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
				font-size: 32rpx;
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
