<template>
	<view>
		<!-- 搜索框 -->
		<view class="search">
			<uni-search-bar v-model="searchValue" @input="input" placeholder="搜索用户名称" clearable></uni-search-bar>
		</view>
		
		<!-- 列表 -->
		<view class="list">
			<view class="item" v-for="(item, i) in list" :key="i">
				<view class="left">
					<image :src="item.headPicPath" mode=""></image>
					<view class="info">
						<h2 class="name">{{item.nickName}}</h2>
						<p class="intro">对方关注了你</p>
					</view>
				</view>
				<view class="right">
					<text v-if="item.status === 1" @click="cancelAttention(item, i)">取消关注</text>
					<text v-else style="background-color: darkorange;" @click="addAttention(item, i)">回关</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import my from '@/apis/my.js'
	export default {
		name:"cencern",
		data() {
			return {
				searchValue: '',
				list: [],
				page: {
					userId: 5,
					start: 0,
					limit: 5,
				}
			};
		},
		mounted(){
			this.getList()
		},
		methods: {
			async getList(){
				const { data: res} = await my.searchAttention(this.page)
				if(res.resultCode === 200){
					this.list = res.data
				}
				console.log(res);
			},
			async cancelAttention(item, i){
				const { data: res} = await my.cancelAttention({
					userId: item.userId,
					follower: page.userId
				})
				if(res.resultCode === 200){
					uni.$showMsg('取消成功！')
					this.$set(this.list[i], 'status', 1)
				}
			},
			async addAttention(item, i){
				const { data: res} = await my.addAttention({
					userId: item.userId,
					follower: page.userId
				})
				if(res.resultCode === 200){
					uni.$showMsg('回关成功！')
					this.$set(this.list[i], 'status', 2)
				}
				console.log(res);
			}
		}
	}
</script>

<style lang="scss" scoped>
.item{
	height: 120rpx;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 20rpx 0;
	
	.left{
		display: flex;
		align-items: center;
		
		image{
			height: 100rpx;
			width: 100rpx;
			border-radius: 50%;
			margin-right: 20rpx;
			border: 2rpx solid #eee;
		}
		
		.info{
			width: 300rpx;
			
			h2{
				font-size: 14px;
			}
			
			p{
				font-size: 12px;
				overflow: hidden;
				text-overflow: ellipsis;
				white-space: nowrap;
			}
		}
		
		
	}
	.right{
		
		text{
			padding: 10rpx 30rpx;
			border-radius: 10rpx;
			background-color: darkorange;	
			color: #fff;
		}
	}
}
</style>