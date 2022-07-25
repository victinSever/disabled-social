<template>
	<view>
		<mover></mover>
		<view class="search-option">
			<view class="search-input">
				<uni-search-bar class="search" radius="15" bgColor="#EEEEEE" placeholder="自动显示隐藏" clearButton="auto"
					cancelButton="none" @input="search"  />
			</view>

			<view class="search-choose">
				<view class="choose-title">
					<span>根据类型筛选</span>
				</view>
				<view class="choose-list">
					<view class="choose-item" v-for="(item, i) in tags" :key="i">
						<picker @change="bindPickerChange($event, i)" :value="item.index" :range="item.array">
							<span class="item-title">
								{{item.title}}：
							</span>
							<span class="item-text">{{item.array[item.index]}}</span>
							<span class="item-icon"><uni-icons type="bottom" size="20" color="#777"></uni-icons></span>
						</picker>
					</view>
				</view>
			</view>
			
			<!-- 列表 -->
			<view class="table-list">
				<view class="table-item">
					
				</view>
			</view>
			<!-- 推荐区 -->
			<view class="body-recomment">
				<view class="recomment-list">
					<view class="recomment-item" v-for="(item, i) in recommentList" :key="i">
						<view class="item-img">
							<img :src="item.imagePath" alt="">
							<span class="img-prove">已认证</span>
						</view>
						<view class="item-info">
							<p>
								<span class="info-age">{{item.age}}岁</span>·
								<span class="info-high">{{item.high}}cm</span>·
								<span class="info-rank">{{item.rank}}</span>
							</p>
							<p>
								<span class="info-disType">{{item.disType}}</span>·
								<span class="info-disLevel">{{item.disLevel}}</span>
							</p>
							<p class="info-imp">
								<span class="info-address">
									<uni-icons type="location" size="15" color="red"></uni-icons>
									<span>{{item.position}}</span>
								</span>
								<span class="info-opration">{{item.operation}}</span>
							</p>
						</view>
					</view>
				</view>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				index: 0,
				tags: [
					{
						title: '残疾类型',
						array: ['智力', '肢体', '瘫痪', '精神'],
						index: 0,
					},
					{
						title: '残疾等级',
						array: ['初级', '中度', '重度'],
						index: 0,
					},
					{
						title: '性别',
						array: ['男', '女'],
						index: 0,
					},
					{
						title: '其他',
						array: ['213', '123', '13', '312'],
						index: 0,
					},
				],
				// 推荐嘉宾
				recommentList: [{
					imagePath: '/static/images/user.jpg',
					age: '18岁',
					high: '178cm',
					rank: '大专',
					disType: '精神残疾',
					disLevel: '二级',
					position: '南岸区',
					operation: '自由职业者'
				},{
					imagePath: '/static/images/user2.jpg',
					age: '18岁',
					high: '178cm',
					rank: '大专',
					disType: '精神残疾',
					disLevel: '二级',
					position: '南岸区',
					operation: '自由职业者'
				},{
					imagePath: '/static/images/home/img1.png',
					age: '18岁',
					high: '178cm',
					rank: '大专',
					disType: '精神残疾',
					disLevel: '二级',
					position: '南岸区',
					operation: '自由职业者'
				},{
					imagePath: '/static/images/content2.jpg',
					age: '18岁',
					high: '178cm',
					rank: '大专',
					disType: '精神残疾',
					disLevel: '二级',
					position: '南岸区',
					operation: '自由职业者'
				}],
			};
		},
		methods: {
			search(e) {
				console.log(2);
			}, 
			bindPickerChange(e,i) {
				this.tags[i].index = e.detail.value
			},
		}
	}
</script>

<style lang="scss" scoped>
	.search-option {
		padding: 0 20rpx;

		.search {}


		.search-choose {
			width: 100%;
			
			
			.choose-title{
				height: 60rpx;
				line-height: 60rpx;
			}
			
			.choose-list{
				display: flex;
				flex-wrap: wrap;
				justify-content: space-between;
				
				.choose-item {
					width: 40%;
					height: 80rpx;
					line-height: 80rpx;
					// display: flex;
					// align-items: center;
					
					.item-title{
						color: #777;
					}
					
				}
			}

			
		}
	}

	.body-recomment{	
		margin-top: 40rpx;
		
		.recomment-list{
			display: flex;
			flex-wrap: wrap;
			justify-content: space-around;
			
			.recomment-item{
				width: calc((100% - 40rpx - 20rpx)/2);
				margin-bottom: 40rpx;
				border-radius: 10rpx;
				box-shadow: 1px 1px 1px rgba(0,0,0,0.1), 
				1px -1px 1px rgba(0,0,0,0.1),
				-1px -1px 1px rgba(0,0,0,0.1);
				
				.item-img{
					position: relative;
					
					img{
						border-radius: 10rpx 10rpx 0 0;
						width: 100%;
						height: 60%;
					}
					
					.img-prove{
						position: absolute;
						top: 0;
						left: 0;
						height: 40rpx;
						line-height: 40rpx;
						font-size: 12px;
						border-radius: 10rpx 20rpx 20rpx 0;
						padding: 0 15rpx 0 10rpx;
						background-color: #00e100;
						color: #fff;
						letter-spacing: 2rpx;
					}
				}
				
				.item-info{
					p{
						text-align: center;
						line-height: 50rpx;
						font-size: 12px;
						color: #777;
						
						span{
							margin: 0 6rpx;
						}
					}
					
					.info-imp{
						color: red;
						font-size: 10px;
						margin-bottom: 20rpx;
						
						&>span{
							border-radius: 20rpx;
							height: 50rpx;
							padding: 5rpx 10rpx;
							background-color: rgba(255, 0, 0, 0.1);
						}
					}
				}
			}
			
		}
	
	}
</style>
