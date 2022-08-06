<template>
	<view class="root">
		<mover></mover>
		<view class="fixed">
			<!-- 头部 -->
			<view class="header">
				<view class="header-left">
					<uni-icons type="back" size="25" @click="gotoBack"></uni-icons>
					<text>编辑资料</text>
				</view>
				<view class="header-right" v-if="isPre">
					<text class="btnSend" @click="finish">完成</text>
				</view>
			</view>

			<!-- 选择编辑或者预览 -->
			<view class="banner">
				<view class="preScan"
					:style="isPre ? 'border-bottom: 6rpx solid darkorange;; color: darkorange;' : 'border-bottom: 6rpx solid #eee; color: #000;'"
					@click="isPre = true">
					<text>预览</text>
				</view>
				<view class="edit"
					:style="!isPre ? 'border-bottom: 6rpx solid darkorange;; color: darkorange;' : 'border-bottom: 6rpx solid #eee; color: #000;'"
					@click="isPre = false">
					<text>编辑</text>
				</view>
			</view>

			
		</view>

		<scroll-view scroll-y="true">
			<view class="sedMenu" v-if="!isPre">
				<!-- 菜单 -->
				<view class="menu">
					<view :class="type === 1 ? 'active' : ''" @click="type = 1">
						<text>基本资料</text>
					</view>
					<view :class="type === 2 ? 'active' : ''" @click="type = 2">
						<text>详细资料</text>
					</view>
					<view :class="type === 3 ? 'active' : ''" @click="type = 3">
						<text>择偶要求</text>
					</view>
				</view>
							
			</view>
			<view class="main">
				<personage v-if="isPre" :backShow="false" :personageData="data"></personage>
				<view v-else>								
					<baseCom v-if="type === 1" @changeType="changeType"></baseCom>
					<detail v-else-if="type === 2" @changeType="changeType"></detail>
					<marrary v-else  @saveData="saveData"></marrary>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import baseCom from "@/components/person-information/base/base.vue"
	import detail from "@/components/person-information/detail/detail.vue"
	import marrary from "@/components/person-information/marrary/marrary.vue"
	import personage from '@/components/personage/index.vue'
	import apiService from '@/apis/my.js'
	import { mapState } from 'vuex'
	export default {
		name: "checkinformation",
		data() {
			return {
				// 是否为预览状态
				isPre: true,
				isFinish: false,
				type: 1,//三类资料组件切换
				
				data: {
					imageList: [
						"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fjdimage.300hu.com%2Fvodtransgzp1251412368%2F9031868223359246895%2FcoverBySnapshot%2F1507969776_2560260254.100_0.jpg&refer=http%3A%2F%2Fjdimage.300hu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1662211533&t=99205968ff7ea32f9ef4016b8b42b18b"
					],
				},
				personData: {}
			};
		},
		components: {
			baseCom, detail, marrary, personage
		},
		computed: {
			// 引入个人数据
			...mapState('common',['baseInfo','moreInfo']),
		},
		mounted(){
			this.getAllData()
		},
		methods: {
			// 获取信息
			async getAllData(){
				this.personData = this.moreInfo
			},
			
			gotoPre(data){
				this.isPre = true
			},
			// 进入下一页
			changeType(data){
				this.data = {...data}
				this.type++
				console.log(this.data)
			},
			// 返回
			gotoBack() {
				uni.switchTab({
					url: '/pages/person/person'
				})
			},
			// 保存修改
			finish() {
				let $this = this
				setTimeout(function() {
					this.isFinish = true
					console.log(this.data);
					uni.$showMsg("修改成功！")
				}, 1000)
			},
			// 发请求
			getData(data) {
				this.data = data
				console.log(this.data);
			},
		}
	}
</script>

<style lang="scss" scoped>
	.fixed {
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

			text {
				font-size: 22px;
				font-weight: bold;
				margin-left: 10rpx;
			}

		}

		.header-right {
			display: flex;
			align-items: center;
			border-radius: 50rpx;
			padding: 10rpx 40rpx;
			height: 50rpx;
			background-color: #f9976f;

			.btnSend {
				display: inline-block;
				text-align: center;
				font-size: 16px;
				color: #fff;
			}
		}
	}

	.banner {
		display: flex;
		justify-content: center;
		height: 80rpx;
		box-sizing: border-box;
		font-size: 16px;
		background-color: #fff;
		margin-bottom: 20rpx;

		view {
			width: 80rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			border-bottom: 6rpx solid #eee;
			margin: 0 40rpx;
		}

		.preScan {
			border-bottom: 6rpx solid darkorange;
			color: darkorange;
		}

	}

	

	.sedMenu{
		background-color: #fff;
		padding: 30rpx;
		
		.menu {
		
			display: flex;
			box-sizing: border-box;
			font-size: 14px;
			height: 80rpx;
			background-color: #fff;
		
			view {
				width: 120rpx;
				display: flex;
				align-items: center;
				justify-content: center;
				margin-right: 40rpx;
			}
		
			.active {
				color: darkorange;
			}
		}
		

	}

	.main {
		padding: 0 30rpx;
	}
	
	
</style>
