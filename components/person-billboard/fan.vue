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
					<text>回关</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"cencern",
		data() {
			return {
				searchValue: '',
				list: [
					{
						imagePath: '../../static/images/admin/admin4.jpg',
						userName: '亲历'
					},
					{
						imagePath: '../../static/images/admin/admin3.jpg',
						userName: 'admin'
					},
				]
			};
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