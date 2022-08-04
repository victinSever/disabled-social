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
					<image :src="item.imagePath" mode=""></image>
					<view class="info">
						<h2 class="name">{{item.userName}}</h2>
						<p class="intro">对方关注了你</p>
					</view>
				</view>
				<view class="right">
					<text v-if="item.isAttention" @click="cancelAttention(item, i)">取消关注</text>
					<text v-else style="background-color: darkorange;" @click="concernUser(item, i)">回关</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import apiService from '@/apis/message.js'
	export default {
		name:"cencern",
		data() {
			return {
				searchValue: '',
				list: [
					{
						imagePath: '../../static/images/admin/admin4.jpg',
						userName: '亲历',
						isAttention: false,
					},
					{
						imagePath: '../../static/images/admin/admin3.jpg',
						userName: 'admin',
						isAttention: false,
					},
				],
				page: {
					userId: 3,
					start: 1,
					limit: 5,
				}
			};
		},
		mounted(){
			this.getList()
		},
		methods: {
			async getList(){
				const { data: res} = await apiService.searchAttention(this.page)
				if(res.resultCode === 200){
					
				}
				console.log(res);
			},
			async cancelAttention(item, i){
				const { data: res} = await apiService.cancelAttention({
					userId: 1,
					follower: 5
				})
				if(res.resultCode === 200){
					if(res.message == 'SUCCESS'){
						uni.$showMsg('取消成功！')
						this.$set(this.list[i], 'isAttention', false)
					}
				}
			},
			async concernUser(item, i){
				const { data: res} = await apiService.concernUser({
					userId: 1,
					follower: 5
				})
				if(res.resultCode === 200){
					if(res.message == 'SUCCESS'){
						uni.$showMsg('关注成功！')
						this.$set(this.list[i], 'isAttention', true)
					}
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
		}
		
		.info{
			width: 400rpx;
			
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